/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Using: C:\\Users\\99313\\AppData\\Local\\Android\\Sdk\\build-tools\\35.0.1\\aidl.exe -pC:\\Users\\99313\\AppData\\Local\\Android\\Sdk\\platforms\\android-35\\framework.aidl -oD:\\NekoBoxForAndroid\\app\\build\\generated\\aidl_source_output_dir\\playDebug\\out -ID:\\NekoBoxForAndroid\\app\\src\\main\\aidl -ID:\\NekoBoxForAndroid\\app\\src\\play\\aidl -ID:\\NekoBoxForAndroid\\app\\src\\debug\\aidl -ID:\\NekoBoxForAndroid\\app\\src\\playDebug\\aidl -IC:\\Users\\99313\\.gradle\\caches\\8.10.2\\transforms\\dcb1f18d0d85488c2870f0ec13379ad4\\transformed\\core-1.13.0\\aidl -IC:\\Users\\99313\\.gradle\\caches\\8.10.2\\transforms\\7dc1e7205b00da345ef0a2c1c8521d32\\transformed\\versionedparcelable-1.1.1\\aidl -dC:\\Users\\99313\\AppData\\Local\\Temp\\aidl10599070871940340122.d D:\\NekoBoxForAndroid\\app\\src\\main\\aidl\\io\\nekohasekai\\sagernet\\aidl\\ISagerNetService.aidl
 */
package io.nekohasekai.sagernet.aidl;
public interface ISagerNetService extends android.os.IInterface
{
  /** Default implementation for ISagerNetService. */
  public static class Default implements io.nekohasekai.sagernet.aidl.ISagerNetService
  {
    @Override public int getState() throws android.os.RemoteException
    {
      return 0;
    }
    @Override public java.lang.String getProfileName() throws android.os.RemoteException
    {
      return null;
    }
    @Override public void registerCallback(io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback cb, int id) throws android.os.RemoteException
    {
    }
    @Override public void unregisterCallback(io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback cb) throws android.os.RemoteException
    {
    }
    @Override public int urlTest() throws android.os.RemoteException
    {
      return 0;
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements io.nekohasekai.sagernet.aidl.ISagerNetService
  {
    /** Construct the stub at attach it to the interface. */
    @SuppressWarnings("this-escape")
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an io.nekohasekai.sagernet.aidl.ISagerNetService interface,
     * generating a proxy if needed.
     */
    public static io.nekohasekai.sagernet.aidl.ISagerNetService asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof io.nekohasekai.sagernet.aidl.ISagerNetService))) {
        return ((io.nekohasekai.sagernet.aidl.ISagerNetService)iin);
      }
      return new io.nekohasekai.sagernet.aidl.ISagerNetService.Stub.Proxy(obj);
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
        case TRANSACTION_getState:
        {
          int _result = this.getState();
          reply.writeNoException();
          reply.writeInt(_result);
          break;
        }
        case TRANSACTION_getProfileName:
        {
          java.lang.String _result = this.getProfileName();
          reply.writeNoException();
          reply.writeString(_result);
          break;
        }
        case TRANSACTION_registerCallback:
        {
          io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback _arg0;
          _arg0 = io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback.Stub.asInterface(data.readStrongBinder());
          int _arg1;
          _arg1 = data.readInt();
          this.registerCallback(_arg0, _arg1);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_unregisterCallback:
        {
          io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback _arg0;
          _arg0 = io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback.Stub.asInterface(data.readStrongBinder());
          this.unregisterCallback(_arg0);
          break;
        }
        case TRANSACTION_urlTest:
        {
          int _result = this.urlTest();
          reply.writeNoException();
          reply.writeInt(_result);
          break;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
      return true;
    }
    private static class Proxy implements io.nekohasekai.sagernet.aidl.ISagerNetService
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
      @Override public int getState() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getState, _data, _reply, 0);
          _reply.readException();
          _result = _reply.readInt();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public java.lang.String getProfileName() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getProfileName, _data, _reply, 0);
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void registerCallback(io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback cb, int id) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongInterface(cb);
          _data.writeInt(id);
          boolean _status = mRemote.transact(Stub.TRANSACTION_registerCallback, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void unregisterCallback(io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback cb) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongInterface(cb);
          boolean _status = mRemote.transact(Stub.TRANSACTION_unregisterCallback, _data, null, android.os.IBinder.FLAG_ONEWAY);
        }
        finally {
          _data.recycle();
        }
      }
      @Override public int urlTest() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_urlTest, _data, _reply, 0);
          _reply.readException();
          _result = _reply.readInt();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
    }
    static final int TRANSACTION_getState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_getProfileName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_registerCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_unregisterCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_urlTest = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
  }
  /** @hide */
  public static final java.lang.String DESCRIPTOR = "io.nekohasekai.sagernet.aidl.ISagerNetService";
  public int getState() throws android.os.RemoteException;
  public java.lang.String getProfileName() throws android.os.RemoteException;
  public void registerCallback(io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback cb, int id) throws android.os.RemoteException;
  public void unregisterCallback(io.nekohasekai.sagernet.aidl.ISagerNetServiceCallback cb) throws android.os.RemoteException;
  public int urlTest() throws android.os.RemoteException;
}
