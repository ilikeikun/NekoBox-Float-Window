package io.nekohasekai.sagernet.plugin.floatingicon

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.caverock.androidsvg.SVG
import io.nekohasekai.sagernet.aidl.IPluginHost
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.ByteArrayOutputStream

class AppProxyActivity : AppCompatActivity() {

    private var host: IPluginHost? = null

    private lateinit var statusText: TextView
    private lateinit var pickStoppedButton: Button
    private lateinit var pickStartedButton: Button
    private lateinit var applyButton: Button
    private lateinit var restoreButton: Button
    private lateinit var stoppedPreview: ImageView
    private lateinit var startedPreview: ImageView

    private var stoppedPng: ByteArray? = null
    private var startedPng: ByteArray? = null

    private val conn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            host = IPluginHost.Stub.asInterface(service)
            updateStatus()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            host = null
            updateStatus()
        }
    }

    private enum class PickTarget {
        Stopped,
        Started,
    }

    private var pickTarget: PickTarget = PickTarget.Stopped

    private val pickIcon = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
        if (uri == null) return@registerForActivityResult
        val png = try {
            decodeToNormalizedPng(uri)
        } catch (_: Exception) {
            null
        }
        if (png == null) {
            Toast.makeText(this, "文件解析失败", Toast.LENGTH_SHORT).show()
            return@registerForActivityResult
        }

        when (pickTarget) {
            PickTarget.Stopped -> {
                stoppedPng = png
                stoppedPreview.setImageBitmap(BitmapFactory.decodeByteArray(png, 0, png.size))
            }
            PickTarget.Started -> {
                startedPng = png
                startedPreview.setImageBitmap(BitmapFactory.decodeByteArray(png, 0, png.size))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_proxy)

        statusText = findViewById(R.id.statusText)
        pickStoppedButton = findViewById(R.id.pickStoppedButton)
        pickStartedButton = findViewById(R.id.pickStartedButton)
        applyButton = findViewById(R.id.applyButton)
        restoreButton = findViewById(R.id.restoreButton)
        stoppedPreview = findViewById(R.id.stoppedPreview)
        startedPreview = findViewById(R.id.startedPreview)

        pickStoppedButton.setOnClickListener {
            pickTarget = PickTarget.Stopped
            pickIcon.launch(arrayOf("*/*"))
        }
        pickStartedButton.setOnClickListener {
            pickTarget = PickTarget.Started
            pickIcon.launch(arrayOf("*/*"))
        }
        applyButton.setOnClickListener { applyToHost() }
        restoreButton.setOnClickListener { restoreDefault() }

        bindToHost()
        updateStatus()
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            unbindService(conn)
        } catch (_: Exception) {
        }
    }

    private fun resolveHostPackage(): String? {
        val pm = packageManager
        val candidates = listOf(
            "moe.nb4a.debug",
            "moe.nb4a",
            "io.nekohasekai.sagernet.debug",
            "io.nekohasekai.sagernet"
        )
        for (pkg in candidates) {
            try {
                pm.getPackageInfo(pkg, 0)
                return pkg
            } catch (_: Exception) {
            }
        }
        return null
    }

    private fun bindToHost() {
        val hostPackage = resolveHostPackage() ?: return
        val intent = Intent("io.nekohasekai.sagernet.action.PLUGIN_HOST").apply {
            `package` = hostPackage
        }
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
    }

    private fun updateStatus() {
        val h = host
        if (h == null) {
            statusText.text = "未连接到 NekoBox"
            return
        }
        val api = try {
            h.apiVersion
        } catch (_: Exception) {
            -1
        }
        statusText.text = "已连接到 NekoBox (API $api)"
    }

    private fun applyToHost() {
        val h = host ?: run {
            Toast.makeText(this, "未连接到 NekoBox", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val api = h.apiVersion
            if (api < 3) {
                Toast.makeText(this, "NekoBox 版本过低", Toast.LENGTH_SHORT).show()
                return
            }

            h.setFloatingWindowIconPng(stoppedPng, startedPng)
            h.setFloatingWindowDraggable(true)
            Toast.makeText(this, "已应用", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "应用失败: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun restoreDefault() {
        val h = host ?: run {
            Toast.makeText(this, "未连接到 NekoBox", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            h.clearFloatingWindowIcon()
            Toast.makeText(this, "已恢复默认", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "操作失败: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun decodeToNormalizedPng(uri: Uri): ByteArray? {
        val bytes = contentResolver.openInputStream(uri)?.use { it.readBytes() } ?: return null

        val head = try {
            String(bytes, 0, minOf(bytes.size, 512), Charsets.UTF_8)
        } catch (_: Exception) {
            ""
        }

        val isSvg = head.contains("<svg", ignoreCase = true)
        val isVectorXml = head.contains("<vector", ignoreCase = true)

        val bmp = when {
            isSvg -> renderSvgToBitmap(bytes)
            isVectorXml -> renderVectorXmlToBitmap(bytes)
            else -> decodeBitmapToFit(bytes)
        } ?: return null

        val normalized = normalizeToSquare(bmp, 256)
        val out = ByteArrayOutputStream()
        normalized.compress(Bitmap.CompressFormat.PNG, 100, out)
        return out.toByteArray()
    }

    private fun renderSvgToBitmap(svgBytes: ByteArray): Bitmap? {
        val svg = try {
            SVG.getFromInputStream(svgBytes.inputStream())
        } catch (_: Exception) {
            return null
        }

        val docW = svg.documentWidth.takeIf { it > 0 } ?: 256f
        val docH = svg.documentHeight.takeIf { it > 0 } ?: 256f
        val target = 512
        val bmp = Bitmap.createBitmap(target, target, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        val scale = minOf(target / docW, target / docH)
        val dx = (target - docW * scale) / 2f
        val dy = (target - docH * scale) / 2f
        canvas.translate(dx, dy)
        canvas.scale(scale, scale)
        svg.renderToCanvas(canvas)
        return bmp
    }

    private fun renderVectorXmlToBitmap(xmlBytes: ByteArray): Bitmap? {
        return try {
            val factory = XmlPullParserFactory.newInstance()
            val parser: XmlPullParser = factory.newPullParser()
            parser.setInput(xmlBytes.inputStream(), null)
            var type = parser.eventType
            while (type != XmlPullParser.START_TAG && type != XmlPullParser.END_DOCUMENT) {
                type = parser.next()
            }
            if (type != XmlPullParser.START_TAG) return null

            val attrs = android.util.Xml.asAttributeSet(parser)
            val drawable = VectorDrawableCompat.createFromXmlInner(resources, parser, attrs, theme)
                ?: return null
            val bmp = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bmp)
            drawable.setBounds(0, 0, 512, 512)
            drawable.draw(canvas)
            bmp
        } catch (_: Exception) {
            null
        }
    }

    private fun decodeBitmapToFit(bytes: ByteArray): Bitmap? {
        val bounds = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }
        BitmapFactory.decodeByteArray(bytes, 0, bytes.size, bounds)
        val w = bounds.outWidth
        val h = bounds.outHeight
        if (w <= 0 || h <= 0) return null

        val maxSide = maxOf(w, h)
        var sample = 1
        while (maxSide / sample > 1024) {
            sample *= 2
        }

        val opts = BitmapFactory.Options().apply {
            inSampleSize = sample
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size, opts)
    }

    private fun normalizeToSquare(src: Bitmap, size: Int): Bitmap {
        val dst = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(dst)
        val scale = minOf(size.toFloat() / src.width, size.toFloat() / src.height)
        val w = src.width * scale
        val h = src.height * scale
        val dx = (size - w) / 2f
        val dy = (size - h) / 2f
        canvas.translate(dx, dy)
        canvas.scale(scale, scale)
        canvas.drawBitmap(src, 0f, 0f, null)
        return dst
    }
}
