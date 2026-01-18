package io.nekohasekai.sagernet.aidl;

import io.nekohasekai.sagernet.aidl.IPluginClient;

interface IPluginHost {
  /**
   * 返回当前插件 Host API 版本，方便插件做兼容判断。
   */
  int getApiVersion();

  /**
   * 由插件指定一个自己的包名和 drawable 资源名，用作悬浮窗图标。
   * 例如：pluginPackage = "com.example.myplugin"，iconResName = "ic_floating"。
   */
  void setFloatingWindowIcon(String pluginPackage, String iconResName);

  /**
   * 清除插件设置的悬浮窗图标，恢复宿主默认图标逻辑。
   */
  void clearFloatingWindowIcon();

  /**
   * 控制悬浮窗图标是否允许拖动。
   */
  void setFloatingWindowDraggable(boolean draggable);

  /**
   * 获取当前核心服务状态，对应 BaseService.State.ordinal()。
   */
  int getServiceState();

  /**
   * 启动核心服务（等价于在主界面点一次“启动”）。
   */
  void startCoreService();

  /**
   * 停止核心服务。
   */
  void stopCoreService();

  /**
   * 获取当前使用中的 profile ID（DataStore.currentProfile）。
   */
  long getCurrentProfileId();

  /**
   * 设置当前 profile ID（同时更新 selectedProxy & currentProfile）。
   */
  void setCurrentProfileId(long id);

  /**
   * 是否启用分应用代理（DataStore.proxyApps）。
   */
  boolean isProxyAppsEnabled();

  /**
   * 启用 / 关闭分应用代理。
   */
  void setProxyAppsEnabled(boolean enable);

  /**
   * 当前是否为“绕行模式”（DataStore.bypass）。
   */
  boolean isBypassMode();

  /**
   * 设置是否绕行（true = 绕行，false = 全部走代理）。
   */
  void setBypassMode(boolean bypass);

  /**
   * 获取当前被代理的应用包名列表（对应 DataStore.individual，每行一个包名）。
   */
  String[] getProxiedApps();

  /**
   * 一次性设置被代理应用包名列表，传入包名数组，将覆盖原有列表。
   */
  void setProxiedApps(in String[] packages);

  /**
   * 由插件指定一个自己的包名和 drawable 资源名，用作主配置页背景。
   */
  void setConfigurationBackground(String pluginPackage, String backgroundResName);

  /**
   * 清除插件设置的主配置页背景，恢复宿主默认样式。
   */
  void clearConfigurationBackground();

  /**
   * 插件注册一个回调，用于接收悬浮窗相关事件。
   */
  void registerClient(IPluginClient client);

  /**
   * 取消注册之前的回调。
   */
  void unregisterClient(IPluginClient client);

  void setFloatingWindowIconPng(in byte[] stoppedPng, in byte[] startedPng);
}
