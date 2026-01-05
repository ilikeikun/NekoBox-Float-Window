package io.nekohasekai.sagernet.plugin

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import io.nekohasekai.sagernet.ktx.Logs

object ConfigurationUiPluginConfig {

    private const val PREF_NAME = "configuration_ui_plugin_config"
    private const val KEY_PLUGIN_PACKAGE = "plugin_package"
    private const val KEY_BACKGROUND_RES_NAME = "background_res_name"

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setBackgroundFromPlugin(context: Context, pluginPackage: String, resName: String) {
        prefs(context).edit()
            .putString(KEY_PLUGIN_PACKAGE, pluginPackage)
            .putString(KEY_BACKGROUND_RES_NAME, resName)
            .apply()
    }

    fun clearBackground(context: Context) {
        prefs(context).edit()
            .remove(KEY_PLUGIN_PACKAGE)
            .remove(KEY_BACKGROUND_RES_NAME)
            .apply()
    }

    fun loadBackgroundDrawable(context: Context): Drawable? {
        val p = prefs(context)
        val pluginPackage = p.getString(KEY_PLUGIN_PACKAGE, null) ?: return null
        val resName = p.getString(KEY_BACKGROUND_RES_NAME, null) ?: return null

        return try {
            val pluginContext = try {
                context.createPackageContext(pluginPackage, 0)
            } catch (e: Exception) {
                Logs.w(e)
                null
            } ?: return null

            val resId = pluginContext.resources.getIdentifier(
                resName,
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
