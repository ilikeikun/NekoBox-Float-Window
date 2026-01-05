package io.nekohasekai.sagernet.plugin

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.nekohasekai.sagernet.SagerNet
import io.nekohasekai.sagernet.aidl.IPluginClient
import io.nekohasekai.sagernet.aidl.IPluginHost
import io.nekohasekai.sagernet.database.DataStore
import io.nekohasekai.sagernet.ktx.Logs
import io.nekohasekai.sagernet.ui.FloatingWindowService

/**
 * 向外部插件暴露的 Host 侧服务，允许插件控制悬浮窗的一些行为。
 */
class PluginHostService : Service() {

    companion object {
        private val callbackList = mutableListOf<IPluginClient>()

        private fun snapshotCallbacks(): List<IPluginClient> = synchronized(callbackList) {
            callbackList.toList()
        }

        internal fun notifyFloatingWindowClick() {
            for (cb in snapshotCallbacks()) {
                try {
                    cb.onFloatingWindowClick()
                } catch (e: Exception) {
                    Logs.w(e)
                }
            }
        }

        internal fun notifyFloatingWindowDragEnd(x: Int, y: Int) {
            for (cb in snapshotCallbacks()) {
                try {
                    cb.onFloatingWindowDragEnd(x, y)
                } catch (e: Exception) {
                    Logs.w(e)
                }
            }
        }
    }

    private val binder = object : IPluginHost.Stub() {

        override fun getApiVersion(): Int = 2

        override fun setFloatingWindowIcon(pluginPackage: String?, iconResName: String?) {
            if (pluginPackage.isNullOrEmpty() || iconResName.isNullOrEmpty()) return
            FloatingWindowPluginConfig.setIconFromPlugin(applicationContext, pluginPackage, iconResName)
            FloatingWindowService.applyPluginConfigFromPlugin()
        }

        override fun clearFloatingWindowIcon() {
            FloatingWindowPluginConfig.clearIcon(applicationContext)
            FloatingWindowService.applyPluginConfigFromPlugin()
        }

        override fun setFloatingWindowDraggable(draggable: Boolean) {
            FloatingWindowPluginConfig.setDraggable(applicationContext, draggable)
            FloatingWindowService.applyPluginConfigFromPlugin()
        }

        override fun setConfigurationBackground(pluginPackage: String?, backgroundResName: String?) {
            if (pluginPackage.isNullOrEmpty() || backgroundResName.isNullOrEmpty()) return
            ConfigurationUiPluginConfig.setBackgroundFromPlugin(applicationContext, pluginPackage, backgroundResName)
        }

        override fun clearConfigurationBackground() {
            ConfigurationUiPluginConfig.clearBackground(applicationContext)
        }

        override fun getServiceState(): Int {
            return DataStore.serviceState.ordinal
        }

        override fun startCoreService() {
            SagerNet.startService()
        }

        override fun stopCoreService() {
            SagerNet.stopService()
        }

        override fun getCurrentProfileId(): Long {
            return DataStore.currentProfile
        }

        override fun setCurrentProfileId(id: Long) {
            DataStore.selectedProxy = id
            DataStore.currentProfile = id
        }

        override fun isProxyAppsEnabled(): Boolean {
            return DataStore.proxyApps
        }

        override fun setProxyAppsEnabled(enable: Boolean) {
            DataStore.proxyApps = enable
        }

        override fun isBypassMode(): Boolean {
            return DataStore.bypass
        }

        override fun setBypassMode(bypass: Boolean) {
            DataStore.bypass = bypass
        }

        override fun getProxiedApps(): Array<String> {
            val content = DataStore.individual ?: ""
            val list = content.lineSequence()
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .toList()
            return list.toTypedArray()
        }

        override fun setProxiedApps(packages: Array<out String>?) {
            val value = packages?.joinToString(separator = "\n") { it.trim() } ?: ""
            DataStore.individual = value
        }

        override fun registerClient(client: IPluginClient?) {
            if (client == null) return
            synchronized(callbackList) {
                if (!callbackList.contains(client)) {
                    callbackList.add(client)
                }
            }
        }

        override fun unregisterClient(client: IPluginClient?) {
            if (client == null) return
            synchronized(callbackList) {
                callbackList.remove(client)
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}
