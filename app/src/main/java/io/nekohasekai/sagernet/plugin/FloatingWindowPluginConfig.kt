package io.nekohasekai.sagernet.plugin

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import io.nekohasekai.sagernet.ktx.Logs

object FloatingWindowPluginConfig {

    private const val PREF_NAME = "floating_window_plugin_config"
    private const val KEY_PLUGIN_PACKAGE = "plugin_package"
    private const val KEY_ICON_RES_NAME = "icon_res_name"
    private const val KEY_DRAGGABLE = "draggable"

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setIconFromPlugin(context: Context, pluginPackage: String, iconResName: String) {
        prefs(context).edit()
            .putString(KEY_PLUGIN_PACKAGE, pluginPackage)
            .putString(KEY_ICON_RES_NAME, iconResName)
            .apply()
    }

    fun clearIcon(context: Context) {
        prefs(context).edit()
            .remove(KEY_PLUGIN_PACKAGE)
            .remove(KEY_ICON_RES_NAME)
            .apply()
    }

    fun setDraggable(context: Context, draggable: Boolean) {
        prefs(context).edit()
            .putBoolean(KEY_DRAGGABLE, draggable)
            .apply()
    }

    fun hasPluginIcon(context: Context): Boolean {
        val p = prefs(context)
        val pkg = p.getString(KEY_PLUGIN_PACKAGE, null)
        val name = p.getString(KEY_ICON_RES_NAME, null)
        return !pkg.isNullOrEmpty() && !name.isNullOrEmpty()
    }

    fun isDraggable(context: Context): Boolean {
        return prefs(context).getBoolean(KEY_DRAGGABLE, true)
    }

    fun loadPluginIconDrawable(context: Context): Drawable? {
        val p = prefs(context)
        val pluginPackage = p.getString(KEY_PLUGIN_PACKAGE, null) ?: return null
        val iconResName = p.getString(KEY_ICON_RES_NAME, null) ?: return null

        return try {
            val pluginContext = try {
                context.createPackageContext(pluginPackage, 0)
            } catch (e: Exception) {
                Logs.w(e)
                null
            } ?: return null

            val resId = pluginContext.resources.getIdentifier(
                iconResName,
                "drawable",
                pluginPackage
            )
            if (resId == 0) return null

            AppCompatResources.getDrawable(pluginContext, resId)
        } catch (e: Exception) {
            Logs.w(e)
            null
        }
    }
}
