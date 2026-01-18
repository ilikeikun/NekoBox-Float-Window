/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Using: C:\\Users\\99313\\AppData\\Local\\Android\\Sdk\\build-tools\\35.0.1\\aidl.exe -pC:\\Users\\99313\\AppData\\Local\\Android\\Sdk\\platforms\\android-35\\framework.aidl -oD:\\NekoBoxForAndroid\\app\\build\\generated\\aidl_source_output_dir\\fdroidDebug\\out -ID:\\NekoBoxForAndroid\\app\\src\\main\\aidl -ID:\\NekoBoxForAndroid\\app\\src\\fdroid\\aidl -ID:\\NekoBoxForAndroid\\app\\src\\debug\\aidl -ID:\\NekoBoxForAndroid\\app\\src\\fdroidDebug\\aidl -IC:\\Users\\99313\\.gradle\\caches\\8.10.2\\transforms\\dcb1f18d0d85488c2870f0ec13379ad4\\transformed\\core-1.13.0\\aidl -IC:\\Users\\99313\\.gradle\\caches\\8.10.2\\transforms\\7dc1e7205b00da345ef0a2c1c8521d32\\transformed\\versionedparcelable-1.1.1\\aidl -dC:\\Users\\99313\\AppData\\Local\\Temp\\aidl9148191395929663496.d D:\\NekoBoxForAndroid\\app\\src\\main\\aidl\\io\\nekohasekai\\sagernet\\aidl\\IPluginClient.aidl
 */
package io.nekohasekai.sagernet.aidl;
public interface IPluginClient extends android.os.IInterface
{
  /** Default implementation for IPluginClient. */
  public static class Default implements io.nekohasekai.sagernet.aidl.IPluginClient
  {
    /** 悬浮窗图标被点击时回调。 */
    @Override public void onFloatingWindowClick() throws android.os.RemoteException
    {
    }
    /** 悬浮窗拖动结束时回调，x/y 为当前窗口左上角坐标（像素）。 */
    @Override public void onFloatingWindowDragEnd(int x, int y) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements io.nekohasekai.sagernet.aidl.IPluginClient
  {
    /** Construct the stub at attach it to the interface. */
    @SuppressWarnings("this-escape")
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an io.nekohasekai.sagernet.aidl.IPluginClient interface,
     * generating a proxy if needed.
     */
    public static io.nekohasekai.sagernet.aidl.IPluginClient asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof io.nekohasekai.sagernet.aidl.IPluginClient))) {
        return ((io.nekohasekai.sagernet.aidl.IPluginClient)iin);
      }
      return new io.nekohasekai.sagernet.aidl.IPluginClient.Stub.Proxy(obj);
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
        case TRANSACTION_onFloatingWindowClick:
        {
          this.onFloatingWindowClick();
          reply.writeNoException();
          break;
        }
        case TRANSACTION_onFloatingWindowDragEnd:
        {
          int _arg0;
          _arg0 = data.readInt();
          int _arg1;
          _arg1 = data.readInt();
          this.onFloatingWindowDragEnd(_arg0, _arg1);
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
    private static class Proxy implements io.nekohasekai.sagernet.aidl.IPluginClient
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
      /** 悬浮窗图标被点击时回调。 */
      @Override public void onFloatingWindowClick() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onFloatingWindowClick, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /** 悬浮窗拖动结束时回调，x/y 为当前窗口左上角坐标（像素）。 */
      @Override public void onFloatingWindowDragEnd(int x, int y) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(x);
          _data.writeInt(y);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onFloatingWindowDragEnd, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
    }
    static final int TRANSACTION_onFloatingWindowClick = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_onFloatingWindowDragEnd = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
  }
  /** @hide */
  public static final java.lang.String DESCRIPTOR = "io.nekohasekai.sagernet.aidl.IPluginClient";
  /** 悬浮窗图标被点击时回调。 */
  public void onFloatingWindowClick() throws android.os.RemoteException;
  /** 悬浮窗拖动结束时回调，x/y 为当前窗口左上角坐标（像素）。 */
  public void onFloatingWindowDragEnd(int x, int y) throws android.os.RemoteException;
}
