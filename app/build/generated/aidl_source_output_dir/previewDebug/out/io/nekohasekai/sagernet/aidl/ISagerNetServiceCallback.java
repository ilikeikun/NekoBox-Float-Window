/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Using: C:\\Users\\99313\\AppData\\Local\\Android\\Sdk\\build-tools\\35.0.1\\aidl.exe -pC:\\Users\\99313\\AppData\\Local\\Android\\Sdk\\platforms\\android-35\\framework.aidl -oD:\\NekoBoxForAndroid\\app\\build\\generated\\aidl_source_output_dir\\previewDebug\\out -ID:\\NekoBoxForAndroid\\app\\src\\main\\aidl -ID:\\NekoBoxForAndroid\\app\\src\\preview\\aidl -ID:\\NekoBoxForAndroid\\app\\src\\debug\\aidl -ID:\\NekoBoxForAndroid\\app\\src\\previewDebug\\aidl -IC:\\Users\\99313\\.gradle\\caches\\8.10.2\\transforms\\dcb1f18d0d85488c2870f0ec13379ad4\\transformed\\core-1.13.0\\aidl -IC:\\Users\\99313\\.gradle\\caches\\8.10.2\\transforms\\7dc1e7205b00da345ef0a2c1c8521d32\\transformed\\versionedparcelable-1.1.1\\aidl -dC:\\Users\\99313\\AppData\\Local\\Temp\\aidl15811305157067647267.d D:\\NekoBoxForAndroid\\app\\src\\main\\aidl\\io\\nekohasekai\\sagernet\\aidl\\ISagerNetServiceCallback.aidl
 */
package io.nekohasekai.sagernet.aidl;
public interface ISagerNetServiceCallback extends android.os.IInterface
{
  /** Default implementation for ISagerNetServiceCallback. */
  public static class Default implements io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback
  {
    @Override public void stateChanged(int state, java.lang.String profileName, java.lang.String msg) throws android.os.RemoteException
    {
    }
    @Override public void missingPlugin(java.lang.String profileName, java.lang.String pluginName) throws android.os.RemoteException
    {
    }
    @Override public void cbSpeedUpdate(io.nekohasekai.sagernet.aidl.SpeedDisplayData stats) throws android.os.RemoteException
    {
    }
    @Override public void cbTrafficUpdate(io.nekohasekai.sagernet.aidl.TrafficData stats) throws android.os.RemoteException
    {
    }
    @Override public void cbSelectorUpdate(long id) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback
  {
    /** Construct the stub at attach it to the interface. */
    @SuppressWarnings("this-escape")
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback interface,
     * generating a proxy if needed.
     */
    public static io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback))) {
        return ((io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback)iin);
      }
      return new io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback.Stub.Proxy(obj);
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
        case TRANSACTION_stateChanged:
        {
          int _arg0;
          _arg0 = data.readInt();
          java.lang.String _arg1;
          _arg1 = data.readString();
          java.lang.String _arg2;
          _arg2 = data.readString();
          this.stateChanged(_arg0, _arg1, _arg2);
          break;
        }
        case TRANSACTION_missingPlugin:
        {
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _arg1;
          _arg1 = data.readString();
          this.missingPlugin(_arg0, _arg1);
          break;
        }
        case TRANSACTION_cbSpeedUpdate:
        {
          io.nekohasekai.sagernet.aidl.SpeedDisplayData _arg0;
          _arg0 = _Parcel.readTypedObject(data, io.nekohasekai.sagernet.aidl.SpeedDisplayData.CREATOR);
          this.cbSpeedUpdate(_arg0);
          break;
        }
        case TRANSACTION_cbTrafficUpdate:
        {
          io.nekohasekai.sagernet.aidl.TrafficData _arg0;
          _arg0 = _Parcel.readTypedObject(data, io.nekohasekai.sagernet.aidl.TrafficData.CREATOR);
          this.cbTrafficUpdate(_arg0);
          break;
        }
        case TRANSACTION_cbSelectorUpdate:
        {
          long _arg0;
          _arg0 = data.readLong();
          this.cbSelectorUpdate(_arg0);
          break;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
      return true;
    }
    private static class Proxy implements io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback
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
      @Override public void stateChanged(int state, java.lang.String profileName, java.lang.String msg) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(state);
          _data.writeString(profileName);
          _data.writeString(msg);
          boolean _status = mRemote.transact(Stub.TRANSACTION_stateChanged, _data, null, android.os.IBinder.FLAG_ONEWAY);
        }
        finally {
          _data.recycle();
        }
      }
      @Override public void missingPlugin(java.lang.String profileName, java.lang.String pluginName) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(profileName);
          _data.writeString(pluginName);
          boolean _status = mRemote.transact(Stub.TRANSACTION_missingPlugin, _data, null, android.os.IBinder.FLAG_ONEWAY);
        }
        finally {
          _data.recycle();
        }
      }
      @Override public void cbSpeedUpdate(io.nekohasekai.sagernet.aidl.SpeedDisplayData stats) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _Parcel.writeTypedObject(_data, stats, 0);
          boolean _status = mRemote.transact(Stub.TRANSACTION_cbSpeedUpdate, _data, null, android.os.IBinder.FLAG_ONEWAY);
        }
        finally {
          _data.recycle();
        }
      }
      @Override public void cbTrafficUpdate(io.nekohasekai.sagernet.aidl.TrafficData stats) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _Parcel.writeTypedObject(_data, stats, 0);
          boolean _status = mRemote.transact(Stub.TRANSACTION_cbTrafficUpdate, _data, null, android.os.IBinder.FLAG_ONEWAY);
        }
        finally {
          _data.recycle();
        }
      }
      @Override public void cbSelectorUpdate(long id) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeLong(id);
          boolean _status = mRemote.transact(Stub.TRANSACTION_cbSelectorUpdate, _data, null, android.os.IBinder.FLAG_ONEWAY);
        }
        finally {
          _data.recycle();
        }
      }
    }
    static final int TRANSACTION_stateChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_missingPlugin = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_cbSpeedUpdate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_cbTrafficUpdate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_cbSelectorUpdate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
  }
  /** @hide */
  public static final java.lang.String DESCRIPTOR = "io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback";
  public void stateChanged(int state, java.lang.String profileName, java.lang.String msg) throws android.os.RemoteException;
  public void missingPlugin(java.lang.String profileName, java.lang.String pluginName) throws android.os.RemoteException;
  public void cbSpeedUpdate(io.nekohasekai.sagernet.aidl.SpeedDisplayData stats) throws android.os.RemoteException;
  public void cbTrafficUpdate(io.nekohasekai.sagernet.aidl.TrafficData stats) throws android.os.RemoteException;
  public void cbSelectorUpdate(long id) throws android.os.RemoteException;
  /** @hide */
  static class _Parcel {
    static private <T> T readTypedObject(
        android.os.Parcel parcel,
        android.os.Parcelable.Creator<T> c) {
      if (parcel.readInt() != 0) {
          return c.createFromParcel(parcel);
      } else {
          return null;
      }
    }
    static private <T extends android.os.Parcelable> void writeTypedObject(
        android.os.Parcel parcel, T value, int parcelableFlags) {
      if (value != null) {
        parcel.writeInt(1);
        value.writeToParcel(parcel, parcelableFlags);
      } else {
        parcel.writeInt(0);
      }
    }
  }
}
