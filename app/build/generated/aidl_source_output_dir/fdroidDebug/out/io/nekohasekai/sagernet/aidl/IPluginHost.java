/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Using: C:\\Users\\99313\\AppData\\Local\\Android\\Sdk\\build-tools\\35.0.1\\aidl.exe -pC:\\Users\\99313\\AppData\\Local\\Android\\Sdk\\platforms\\android-35\\framework.aidl -oD:\\NekoBoxForAndroid\\app\\build\\generated\\aidl_source_output_dir\\fdroidDebug\\out -ID:\\NekoBoxForAndroid\\app\\src\\main\\aidl -ID:\\NekoBoxForAndroid\\app\\src\\fdroid\\aidl -ID:\\NekoBoxForAndroid\\app\\src\\debug\\aidl -ID:\\NekoBoxForAndroid\\app\\src\\fdroidDebug\\aidl -IC:\\Users\\99313\\.gradle\\caches\\8.10.2\\transforms\\dcb1f18d0d85488c2870f0ec13379ad4\\transformed\\core-1.13.0\\aidl -IC:\\Users\\99313\\.gradle\\caches\\8.10.2\\transforms\\7dc1e7205b00da345ef0a2c1c8521d32\\transformed\\versionedparcelable-1.1.1\\aidl -dC:\\Users\\99313\\AppData\\Local\\Temp\\aidl17291840654903736814.d D:\\NekoBoxForAndroid\\app\\src\\main\\aidl\\io\\nekohasekai\\sagernet\\aidl\\IPluginHost.aidl
 */
package io.nekohasekai.sagernet.aidl;
public interface IPluginHost extends android.os.IInterface
{
  /** Default implementation for IPluginHost. */
  public static class Default implements io.nekohasekai.sagernet.aidl.IPluginHost
  {
    /** 返回当前插件 Host API 版本，方便插件做兼容判断。 */
    @Override public int getApiVersion() throws android.os.RemoteException
    {
      return 0;
    }
    /**
     * 由插件指定一个自己的包名和 drawable 资源名，用作悬浮窗图标。
     * 例如：pluginPackage = "com.example.myplugin"，iconResName = "ic_floating"。
     */
    @Override public void setFloatingWindowIcon(java.lang.String pluginPackage, java.lang.String iconResName) throws android.os.RemoteException
    {
    }
    /** 清除插件设置的悬浮窗图标，恢复宿主默认图标逻辑。 */
    @Override public void clearFloatingWindowIcon() throws android.os.RemoteException
    {
    }
    /** 控制悬浮窗图标是否允许拖动。 */
    @Override public void setFloatingWindowDraggable(boolean draggable) throws android.os.RemoteException
    {
    }
    /** 获取当前核心服务状态，对应 BaseService.State.ordinal()。 */
    @Override public int getServiceState() throws android.os.RemoteException
    {
      return 0;
    }
    /** 启动核心服务（等价于在主界面点一次“启动”）。 */
    @Override public void startCoreService() throws android.os.RemoteException
    {
    }
    /** 停止核心服务。 */
    @Override public void stopCoreService() throws android.os.RemoteException
    {
    }
    /** 获取当前使用中的 profile ID（DataStore.currentProfile）。 */
    @Override public long getCurrentProfileId() throws android.os.RemoteException
    {
      return 0L;
    }
    /** 设置当前 profile ID（同时更新 selectedProxy & currentProfile）。 */
    @Override public void setCurrentProfileId(long id) throws android.os.RemoteException
    {
    }
    /** 是否启用分应用代理（DataStore.proxyApps）。 */
    @Override public boolean isProxyAppsEnabled() throws android.os.RemoteException
    {
      return false;
    }
    /** 启用 / 关闭分应用代理。 */
    @Override public void setProxyAppsEnabled(boolean enable) throws android.os.RemoteException
    {
    }
    /** 当前是否为“绕行模式”（DataStore.bypass）。 */
    @Override public boolean isBypassMode() throws android.os.RemoteException
    {
      return false;
    }
    /** 设置是否绕行（true = 绕行，false = 全部走代理）。 */
    @Override public void setBypassMode(boolean bypass) throws android.os.RemoteException
    {
    }
    /** 获取当前被代理的应用包名列表（对应 DataStore.individual，每行一个包名）。 */
    @Override public java.lang.String[] getProxiedApps() throws android.os.RemoteException
    {
      return null;
    }
    /** 一次性设置被代理应用包名列表，传入包名数组，将覆盖原有列表。 */
    @Override public void setProxiedApps(java.lang.String[] packages) throws android.os.RemoteException
    {
    }
    /** 由插件指定一个自己的包名和 drawable 资源名，用作主配置页背景。 */
    @Override public void setConfigurationBackground(java.lang.String pluginPackage, java.lang.String backgroundResName) throws android.os.RemoteException
    {
    }
    /** 清除插件设置的主配置页背景，恢复宿主默认样式。 */
    @Override public void clearConfigurationBackground() throws android.os.RemoteException
    {
    }
    /** 插件注册一个回调，用于接收悬浮窗相关事件。 */
    @Override public void registerClient(io.nekohasekai.sagernet.aidl.IPluginClient client) throws android.os.RemoteException
    {
    }
    /** 取消注册之前的回调。 */
    @Override public void unregisterClient(io.nekohasekai.sagernet.aidl.IPluginClient client) throws android.os.RemoteException
    {
    }
    @Override public void setFloatingWindowIconPng(byte[] stoppedPng, byte[] startedPng) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements io.nekohasekai.sagernet.aidl.IPluginHost
  {
    /** Construct the stub at attach it to the interface. */
    @SuppressWarnings("this-escape")
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an io.nekohasekai.sagernet.aidl.IPluginHost interface,
     * generating a proxy if needed.
     */
    public static io.nekohasekai.sagernet.aidl.IPluginHost asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof io.nekohasekai.sagernet.aidl.IPluginHost))) {
        return ((io.nekohasekai.sagernet.aidl.IPluginHost)iin);
      }
      return new io.nekohasekai.sagernet.aidl.IPluginHost.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      if (code >= android.os.IBinder.FIRST_CALL_TRANSACTION && code <= android.os.IBinder.LAST_CALL_TRANSACTION) {
        data.enforceInterface(descriptor);
      }
      if (code == INTERFACE_TRANSACTION) {
        reply.writeString(descriptor);
        return true;
      }
      switch (code)
      {
        case TRANSACTION_getApiVersion:
        {
          int _result = this.getApiVersion();
          reply.writeNoException();
          reply.writeInt(_result);
          break;
        }
        case TRANSACTION_setFloatingWindowIcon:
        {
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _arg1;
          _arg1 = data.readString();
          this.setFloatingWindowIcon(_arg0, _arg1);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_clearFloatingWindowIcon:
        {
          this.clearFloatingWindowIcon();
          reply.writeNoException();
          break;
        }
        case TRANSACTION_setFloatingWindowDraggable:
        {
          boolean _arg0;
          _arg0 = (0!=data.readInt());
          this.setFloatingWindowDraggable(_arg0);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_getServiceState:
        {
          int _result = this.getServiceState();
          reply.writeNoException();
          reply.writeInt(_result);
          break;
        }
        case TRANSACTION_startCoreService:
        {
          this.startCoreService();
          reply.writeNoException();
          break;
        }
        case TRANSACTION_stopCoreService:
        {
          this.stopCoreService();
          reply.writeNoException();
          break;
        }
        case TRANSACTION_getCurrentProfileId:
        {
          long _result = this.getCurrentProfileId();
          reply.writeNoException();
          reply.writeLong(_result);
          break;
        }
        case TRANSACTION_setCurrentProfileId:
        {
          long _arg0;
          _arg0 = data.readLong();
          this.setCurrentProfileId(_arg0);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_isProxyAppsEnabled:
        {
          boolean _result = this.isProxyAppsEnabled();
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          break;
        }
        case TRANSACTION_setProxyAppsEnabled:
        {
          boolean _arg0;
          _arg0 = (0!=data.readInt());
          this.setProxyAppsEnabled(_arg0);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_isBypassMode:
        {
          boolean _result = this.isBypassMode();
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          break;
        }
        case TRANSACTION_setBypassMode:
        {
          boolean _arg0;
          _arg0 = (0!=data.readInt());
          this.setBypassMode(_arg0);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_getProxiedApps:
        {
          java.lang.String[] _result = this.getProxiedApps();
          reply.writeNoException();
          reply.writeStringArray(_result);
          break;
        }
        case TRANSACTION_setProxiedApps:
        {
          java.lang.String[] _arg0;
          _arg0 = data.createStringArray();
          this.setProxiedApps(_arg0);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_setConfigurationBackground:
        {
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _arg1;
          _arg1 = data.readString();
          this.setConfigurationBackground(_arg0, _arg1);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_clearConfigurationBackground:
        {
          this.clearConfigurationBackground();
          reply.writeNoException();
          break;
        }
        case TRANSACTION_registerClient:
        {
          io.nekohasekai.sagernet.aidl.IPluginClient _arg0;
          _arg0 = io.nekohasekai.sagernet.aidl.IPluginClient.Stub.asInterface(data.readStrongBinder());
          this.registerClient(_arg0);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_unregisterClient:
        {
          io.nekohasekai.sagernet.aidl.IPluginClient _arg0;
          _arg0 = io.nekohasekai.sagernet.aidl.IPluginClient.Stub.asInterface(data.readStrongBinder());
          this.unregisterClient(_arg0);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_setFloatingWindowIconPng:
        {
          byte[] _arg0;
          _arg0 = data.createByteArray();
          byte[] _arg1;
          _arg1 = data.createByteArray();
          this.setFloatingWindowIconPng(_arg0, _arg1);
          reply.writeNoException();
          break;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
      return true;
    }
    private static class Proxy implements io.nekohasekai.sagernet.aidl.IPluginHost
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      /** 返回当前插件 Host API 版本，方便插件做兼容判断。 */
      @Override public int getApiVersion() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getApiVersion, _data, _reply, 0);
          _reply.readException();
          _result = _reply.readInt();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
       * 由插件指定一个自己的包名和 drawable 资源名，用作悬浮窗图标。
       * 例如：pluginPackage = "com.example.myplugin"，iconResName = "ic_floating"。
       */
      @Override public void setFloatingWindowIcon(java.lang.String pluginPackage, java.lang.String iconResName) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(pluginPackage);
          _data.writeString(iconResName);
          boolean _status = mRemote.transact(Stub.TRANSACTION_setFloatingWindowIcon, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 清除插件设置的悬浮窗图标，恢复宿主默认图标逻辑。 */
      @Override public void clearFloatingWindowIcon() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_clearFloatingWindowIcon, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 控制悬浮窗图标是否允许拖动。 */
      @Override public void setFloatingWindowDraggable(boolean draggable) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(((draggable)?(1):(0)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_setFloatingWindowDraggable, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 获取当前核心服务状态，对应 BaseService.State.ordinal()。 */
      @Override public int getServiceState() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getServiceState, _data, _reply, 0);
          _reply.readException();
          _result = _reply.readInt();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /** 启动核心服务（等价于在主界面点一次“启动”）。 */
      @Override public void startCoreService() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_startCoreService, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 停止核心服务。 */
      @Override public void stopCoreService() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_stopCoreService, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 获取当前使用中的 profile ID（DataStore.currentProfile）。 */
      @Override public long getCurrentProfileId() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        long _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getCurrentProfileId, _data, _reply, 0);
          _reply.readException();
          _result = _reply.readLong();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /** 设置当前 profile ID（同时更新 selectedProxy & currentProfile）。 */
      @Override public void setCurrentProfileId(long id) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeLong(id);
          boolean _status = mRemote.transact(Stub.TRANSACTION_setCurrentProfileId, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 是否启用分应用代理（DataStore.proxyApps）。 */
      @Override public boolean isProxyAppsEnabled() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_isProxyAppsEnabled, _data, _reply, 0);
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /** 启用 / 关闭分应用代理。 */
      @Override public void setProxyAppsEnabled(boolean enable) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(((enable)?(1):(0)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_setProxyAppsEnabled, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 当前是否为“绕行模式”（DataStore.bypass）。 */
      @Override public boolean isBypassMode() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_isBypassMode, _data, _reply, 0);
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /** 设置是否绕行（true = 绕行，false = 全部走代理）。 */
      @Override public void setBypassMode(boolean bypass) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(((bypass)?(1):(0)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_setBypassMode, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 获取当前被代理的应用包名列表（对应 DataStore.individual，每行一个包名）。 */
      @Override public java.lang.String[] getProxiedApps() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String[] _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getProxiedApps, _data, _reply, 0);
          _reply.readException();
          _result = _reply.createStringArray();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /** 一次性设置被代理应用包名列表，传入包名数组，将覆盖原有列表。 */
      @Override public void setProxiedApps(java.lang.String[] packages) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStringArray(packages);
          boolean _status = mRemote.transact(Stub.TRANSACTION_setProxiedApps, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 由插件指定一个自己的包名和 drawable 资源名，用作主配置页背景。 */
      @Override public void setConfigurationBackground(java.lang.String pluginPackage, java.lang.String backgroundResName) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(pluginPackage);
          _data.writeString(backgroundResName);
          boolean _status = mRemote.transact(Stub.TRANSACTION_setConfigurationBackground, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 清除插件设置的主配置页背景，恢复宿主默认样式。 */
      @Override public void clearConfigurationBackground() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_clearConfigurationBackground, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 插件注册一个回调，用于接收悬浮窗相关事件。 */
      @Override public void registerClient(io.nekohasekai.sagernet.aidl.IPluginClient client) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongInterface(client);
          boolean _status = mRemote.transact(Stub.TRANSACTION_registerClient, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 取消注册之前的回调。 */
      @Override public void unregisterClient(io.nekohasekai.sagernet.aidl.IPluginClient client) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongInterface(client);
          boolean _status = mRemote.transact(Stub.TRANSACTION_unregisterClient, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void setFloatingWindowIconPng(byte[] stoppedPng, byte[] startedPng) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeByteArray(stoppedPng);
          _data.writeByteArray(startedPng);
          boolean _status = mRemote.transact(Stub.TRANSACTION_setFloatingWindowIconPng, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
    }
    static final int TRANSACTION_getApiVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_setFloatingWindowIcon = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_clearFloatingWindowIcon = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_setFloatingWindowDraggable = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_getServiceState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
    static final int TRANSACTION_startCoreService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
    static final int TRANSACTION_stopCoreService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
    static final int TRANSACTION_getCurrentProfileId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
    static final int TRANSACTION_setCurrentProfileId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
    static final int TRANSACTION_isProxyAppsEnabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
    static final int TRANSACTION_setProxyAppsEnabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
    static final int TRANSACTION_isBypassMode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
    static final int TRANSACTION_setBypassMode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
    static final int TRANSACTION_getProxiedApps = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
    static final int TRANSACTION_setProxiedApps = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
    static final int TRANSACTION_setConfigurationBackground = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
    static final int TRANSACTION_clearConfigurationBackground = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
    static final int TRANSACTION_registerClient = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
    static final int TRANSACTION_unregisterClient = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
    static final int TRANSACTION_setFloatingWindowIconPng = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
  }
  /** @hide */
  public static final java.lang.String DESCRIPTOR = "io.nekohasekai.sagernet.aidl.IPluginHost";
  /** 返回当前插件 Host API 版本，方便插件做兼容判断。 */
  public int getApiVersion() throws android.os.RemoteException;
  /**
   * 由插件指定一个自己的包名和 drawable 资源名，用作悬浮窗图标。
   * 例如：pluginPackage = "com.example.myplugin"，iconResName = "ic_floating"。
   */
  public void setFloatingWindowIcon(java.lang.String pluginPackage, java.lang.String iconResName) throws android.os.RemoteException;
  /** 清除插件设置的悬浮窗图标，恢复宿主默认图标逻辑。 */
  public void clearFloatingWindowIcon() throws android.os.RemoteException;
  /** 控制悬浮窗图标是否允许拖动。 */
  public void setFloatingWindowDraggable(boolean draggable) throws android.os.RemoteException;
  /** 获取当前核心服务状态，对应 BaseService.State.ordinal()。 */
  public int getServiceState() throws android.os.RemoteException;
  /** 启动核心服务（等价于在主界面点一次“启动”）。 */
  public void startCoreService() throws android.os.RemoteException;
  /** 停止核心服务。 */
  public void stopCoreService() throws android.os.RemoteException;
  /** 获取当前使用中的 profile ID（DataStore.currentProfile）。 */
  public long getCurrentProfileId() throws android.os.RemoteException;
  /** 设置当前 profile ID（同时更新 selectedProxy & currentProfile）。 */
  public void setCurrentProfileId(long id) throws android.os.RemoteException;
  /** 是否启用分应用代理（DataStore.proxyApps）。 */
  public boolean isProxyAppsEnabled() throws android.os.RemoteException;
  /** 启用 / 关闭分应用代理。 */
  public void setProxyAppsEnabled(boolean enable) throws android.os.RemoteException;
  /** 当前是否为“绕行模式”（DataStore.bypass）。 */
  public boolean isBypassMode() throws android.os.RemoteException;
  /** 设置是否绕行（true = 绕行，false = 全部走代理）。 */
  public void setBypassMode(boolean bypass) throws android.os.RemoteException;
  /** 获取当前被代理的应用包名列表（对应 DataStore.individual，每行一个包名）。 */
  public java.lang.String[] getProxiedApps() throws android.os.RemoteException;
  /** 一次性设置被代理应用包名列表，传入包名数组，将覆盖原有列表。 */
  public void setProxiedApps(java.lang.String[] packages) throws android.os.RemoteException;
  /** 由插件指定一个自己的包名和 drawable 资源名，用作主配置页背景。 */
  public void setConfigurationBackground(java.lang.String pluginPackage, java.lang.String backgroundResName) throws android.os.RemoteException;
  /** 清除插件设置的主配置页背景，恢复宿主默认样式。 */
  public void clearConfigurationBackground() throws android.os.RemoteException;
  /** 插件注册一个回调，用于接收悬浮窗相关事件。 */
  public void registerClient(io.nekohasekai.sagernet.aidl.IPluginClient client) throws android.os.RemoteException;
  /** 取消注册之前的回调。 */
  public void unregisterClient(io.nekohasekai.sagernet.aidl.IPluginClient client) throws android.os.RemoteException;
  public void setFloatingWindowIconPng(byte[] stoppedPng, byte[] startedPng) throws android.os.RemoteException;
}
