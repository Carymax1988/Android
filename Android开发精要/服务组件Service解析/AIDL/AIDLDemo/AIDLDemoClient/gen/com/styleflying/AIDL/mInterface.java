/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\AndroidStudySpace\\AIDLDemoClient\\src\\com\\styleflying\\AIDL\\mInterface.aidl
 */
package com.styleflying.AIDL;
public interface mInterface extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.styleflying.AIDL.mInterface
{
private static final java.lang.String DESCRIPTOR = "com.styleflying.AIDL.mInterface";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.styleflying.AIDL.mInterface interface,
 * generating a proxy if needed.
 */
public static com.styleflying.AIDL.mInterface asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.styleflying.AIDL.mInterface))) {
return ((com.styleflying.AIDL.mInterface)iin);
}
return new com.styleflying.AIDL.mInterface.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_invokTest:
{
data.enforceInterface(DESCRIPTOR);
this.invokTest();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.styleflying.AIDL.mInterface
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
@Override public void invokTest() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_invokTest, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_invokTest = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void invokTest() throws android.os.RemoteException;
}
