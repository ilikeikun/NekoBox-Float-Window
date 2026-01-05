package io.nekohasekai.sagernet.ui

import android.content.Context
import android.os.Bundle
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.nekohasekai.sagernet.R

class FloatingWindowSettingsActivity : AppCompatActivity() {

    private val prefs by lazy {
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating_window_settings)

        title = getString(R.string.floating_window_settings_title)

        val sizeSeek = findViewById<SeekBar>(R.id.sizeSeekBar)
        val alphaSeek = findViewById<SeekBar>(R.id.alphaSeekBar)
        val lockSwitch = findViewById<Switch>(R.id.lockScreenSwitch)
        val sizeValue = findViewById<TextView>(R.id.sizeValueText)
        val alphaValue = findViewById<TextView>(R.id.alphaValueText)
        val radioCloud = findViewById<RadioButton>(R.id.toggleProfileCloud)
        val radioOffline = findViewById<RadioButton>(R.id.toggleProfileOffline)

        val size = prefs.getInt(KEY_SIZE, 70)
        val alpha = prefs.getInt(KEY_ALPHA, 80)
        val showOnLock = prefs.getBoolean(KEY_SHOW_ON_LOCK, false)
        val toggleProfileName = prefs.getString(KEY_TOGGLE_PROFILE_NAME, "云里雾里") ?: "云里雾里"

        sizeSeek.progress = size
        alphaSeek.progress = alpha
        lockSwitch.isChecked = showOnLock

        // 初始化单选按钮状态
        when (toggleProfileName) {
            "临时断网" -> {
                radioOffline.isChecked = true
            }
            else -> {
                radioCloud.isChecked = true
            }
        }

        sizeValue.text = "$size%"
        alphaValue.text = "$alpha%"

        sizeSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sizeValue.text = "$progress%"
                prefs.edit().putInt(KEY_SIZE, progress).apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        alphaSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                alphaValue.text = "$progress%"
                prefs.edit().putInt(KEY_ALPHA, progress).apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        lockSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(KEY_SHOW_ON_LOCK, isChecked).apply()
        }

        radioCloud.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                prefs.edit().putString(KEY_TOGGLE_PROFILE_NAME, "云里雾里").apply()
            }
        }

        radioOffline.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                prefs.edit().putString(KEY_TOGGLE_PROFILE_NAME, "临时断网").apply()
            }
        }
    }

    companion object {
        private const val PREFS_NAME = "floating_window_settings"
        private const val KEY_SIZE = "size_percent"
        private const val KEY_ALPHA = "alpha_percent"
        private const val KEY_SHOW_ON_LOCK = "show_on_lock"
        private const val KEY_TOGGLE_PROFILE_NAME = "toggle_profile_name"
    }
}
