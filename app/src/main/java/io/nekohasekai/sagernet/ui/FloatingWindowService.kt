package io.nekohasekai.sagernet.ui

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.content.res.Configuration
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import io.nekohasekai.sagernet.R
import io.nekohasekai.sagernet.SagerNet
import io.nekohasekai.sagernet.database.DataStore
import io.nekohasekai.sagernet.database.SagerDatabase
import io.nekohasekai.sagernet.ktx.Logs
import io.nekohasekai.sagernet.plugin.FloatingWindowPluginConfig
import io.nekohasekai.sagernet.plugin.PluginHostService
import kotlin.math.hypot

class FloatingWindowService : Service() {

    private lateinit var windowManager: WindowManager
    private var rootView: View? = null
    private lateinit var layoutParams: WindowManager.LayoutParams

    private var iconView: ImageView? = null
    private var isDraggable: Boolean = true

    private var lastX = 0
    private var lastY = 0
    private var touchStartRawX = 0f
    private var touchStartRawY = 0f

    private var baseScale = 1f
    private var baseAlpha = 1f

    private var screenWidth = 0
    private var screenHeight = 0

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        isRunning = true

        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val prefs = getSharedPreferences("floating_window_settings", Context.MODE_PRIVATE)
        val sizePercent = prefs.getInt("size_percent", 70).coerceIn(40, 120)
        val alphaPercent = prefs.getInt("alpha_percent", 80).coerceIn(20, 100)
        val showOnLock = prefs.getBoolean("show_on_lock", false)

        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.view_floating_window, null)
        rootView = view

        iconView = view.findViewById(R.id.floatingIcon)

        val type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            @Suppress("DEPRECATION")
            WindowManager.LayoutParams.TYPE_PHONE
        }

        var flags = (WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        if (showOnLock) {
            flags = flags or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
        }

        val metrics = resources.displayMetrics
        screenWidth = metrics.widthPixels
        screenHeight = metrics.heightPixels
        layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            type,
            flags,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = metrics.widthPixels / 2
            y = metrics.heightPixels / 3
        }

        baseScale = sizePercent / 100f
        baseAlpha = alphaPercent / 100f
        iconView?.apply {
            scaleX = baseScale
            scaleY = baseScale
            this.alpha = baseAlpha
        }

        iconView?.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastX = layoutParams.x
                    lastY = layoutParams.y
                    touchStartRawX = event.rawX
                    touchStartRawY = event.rawY
                    iconView?.animate()?.scaleX(baseScale * 1.1f)?.scaleY(baseScale * 1.1f)
                        ?.setDuration(120)?.start()
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    if (!isDraggable) {
                        return@setOnTouchListener true
                    }
                    val dx = (event.rawX - touchStartRawX).toInt()
                    val dy = (event.rawY - touchStartRawY).toInt()
                    layoutParams.x = lastX + dx
                    layoutParams.y = lastY + dy

                    clampToScreen()

                    try {
                        windowManager.updateViewLayout(rootView, layoutParams)
                    } catch (_: IllegalArgumentException) {
                    }
                    true
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    val dx = event.rawX - touchStartRawX
                    val dy = event.rawY - touchStartRawY
                    val distanceSq = dx * dx + dy * dy
                    val slop = 8 * resources.displayMetrics.density
                    if (distanceSq <= slop * slop) {
                        iconView?.performClick()
                    }
                    PluginHostService.notifyFloatingWindowDragEnd(layoutParams.x, layoutParams.y)
                    iconView?.animate()?.scaleX(baseScale)?.scaleY(baseScale)?.setDuration(120)
                        ?.start()
                    true
                }

                else -> false
            }
        }

        iconView?.setOnClickListener {
            toggleSelectedProfile()
            PluginHostService.notifyFloatingWindowClick()
        }

        try {
            windowManager.addView(view, layoutParams)
        } catch (e: Exception) {
            Logs.w(e)
            stopSelf()
            return
        }

        instance = this

        applyPluginConfigInternal()

        iconView?.apply {
            scaleX = baseScale * 0.7f
            scaleY = baseScale * 0.7f
            alpha = 0f
            animate()
                .scaleX(baseScale)
                .scaleY(baseScale)
                .alpha(baseAlpha)
                .setDuration(180)
                .start()
        }
    }

    private fun applyPluginConfigInternal() {
        isDraggable = FloatingWindowPluginConfig.isDraggable(this)

        val icon = iconView ?: return

        val pluginDrawable = FloatingWindowPluginConfig.loadPluginIconDrawable(this)
        if (pluginDrawable != null) {
            icon.setImageDrawable(pluginDrawable)
        } else {
            val startedNow = DataStore.serviceState.started
            icon.setImageResource(
                if (startedNow) R.drawable.ic_service_connected else R.drawable.ic_service_stopped
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        instance = null
        rootView?.let {
            try {
                windowManager.removeView(it)
            } catch (_: Exception) {
            }
        }
        rootView = null
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val icon = iconView ?: return

        val oldWidth = screenWidth
        val oldHeight = screenHeight

        val metrics = resources.displayMetrics
        screenWidth = metrics.widthPixels
        screenHeight = metrics.heightPixels

        val iconWidth = icon.width
        val iconHeight = icon.height
        if (iconWidth == 0 || iconHeight == 0) return

        if (oldWidth > 0) {
            val distLeft = layoutParams.x
            val distRight = oldWidth - (layoutParams.x + iconWidth)
            layoutParams.x = if (distLeft <= distRight) {
                0
            } else {
                screenWidth - iconWidth
            }
        } else {
            layoutParams.x = if (layoutParams.x + iconWidth / 2 < screenWidth / 2) {
                0
            } else {
                screenWidth - iconWidth
            }
        }

        if (oldHeight > 0) {
            val distTop = layoutParams.y
            val distBottom = oldHeight - (layoutParams.y + iconHeight)
            layoutParams.y = if (distTop <= distBottom) {
                0
            } else {
                screenHeight - iconHeight
            }
        } else {
            layoutParams.y = layoutParams.y.coerceIn(0, screenHeight - iconHeight)
        }

        clampToScreen()
        try {
            windowManager.updateViewLayout(rootView, layoutParams)
        } catch (_: IllegalArgumentException) {
        }
    }

    private fun clampToScreen() {
        val icon = iconView ?: return
        val metrics = resources.displayMetrics
        screenWidth = metrics.widthPixels
        screenHeight = metrics.heightPixels

        val iconWidth = icon.width
        val iconHeight = icon.height

        if (iconWidth == 0 || iconHeight == 0) return

        layoutParams.x = layoutParams.x.coerceIn(0, screenWidth - iconWidth)
        layoutParams.y = layoutParams.y.coerceIn(0, screenHeight - iconHeight)
    }

    private fun toggleSelectedProfile() {
        Thread {
            try {
                val prefs = getSharedPreferences("floating_window_settings", Context.MODE_PRIVATE)
                val targetName = prefs.getString("toggle_profile_name", "云里雾里") ?: "云里雾里"
                val groupId = DataStore.selectedGroup
                val proxies = SagerDatabase.proxyDao.getByGroup(groupId)
                val target = proxies.firstOrNull { it.displayName() == targetName } ?: return@Thread

                val targetId = target.id
                val currentId = DataStore.currentProfile
                val started = DataStore.serviceState.started

                val willStop = started && currentId == targetId
                if (willStop) {
                    SagerNet.stopService()
                } else {
                    DataStore.selectedProxy = targetId
                    DataStore.currentProfile = targetId
                    SagerNet.startService()
                }

                if (!FloatingWindowPluginConfig.hasPluginIcon(this@FloatingWindowService)) {
                    val iconRes = if (willStop) {
                        R.drawable.ic_service_stopped
                    } else {
                        R.drawable.ic_service_connected
                    }
                    val icon = iconView
                    if (icon != null) {
                        android.os.Handler(android.os.Looper.getMainLooper()).post {
                            icon.setImageResource(iconRes)
                        }
                    }
                }
            } catch (e: Exception) {
                Logs.w(e)
            }
        }.start()
    }

    companion object {
        @Volatile
        var isRunning: Boolean = false

        @Volatile
        private var instance: FloatingWindowService? = null

        fun start(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:${context.packageName}")
                )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                return
            }
            val intent = Intent(context.applicationContext, FloatingWindowService::class.java)
            context.startService(intent)
        }

        fun stop(context: Context) {
            val intent = Intent(context.applicationContext, FloatingWindowService::class.java)
            context.stopService(intent)
        }

        fun applyPluginConfigFromPlugin() {
            instance?.applyPluginConfigInternal()
        }
    }
}
