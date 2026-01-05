package io.nekohasekai.sagernet.plugin

import io.nekohasekai.sagernet.database.DataStore
import io.nekohasekai.sagernet.ktx.app

object PluginCleanup {
    fun cleanupPluginEffects() {
        DataStore.proxyApps = false
        DataStore.bypass = false
        DataStore.individual = ""
        FloatingWindowPluginConfig.clearIcon(app)
        ConfigurationUiPluginConfig.clearBackground(app)
    }
}
