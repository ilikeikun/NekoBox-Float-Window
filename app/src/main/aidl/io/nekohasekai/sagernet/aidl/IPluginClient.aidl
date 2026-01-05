package io.nekohasekai.sagernet.aidl;

interface IPluginClient {
  /**
   * 悬浮窗图标被点击时回调。
   */
  void onFloatingWindowClick();

  /**
   * 悬浮窗拖动结束时回调，x/y 为当前窗口左上角坐标（像素）。
   */
  void onFloatingWindowDragEnd(int x, int y);
}
