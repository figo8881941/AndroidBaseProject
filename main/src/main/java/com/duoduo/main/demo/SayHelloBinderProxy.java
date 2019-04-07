package com.duoduo.main.demo;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;

public class SayHelloBinderProxy implements ISayHelloInterface {

    private IBinder binder;

    public SayHelloBinderProxy(IBinder binder) {
        this.binder = binder;
    }

    public static ISayHelloInterface asInterface(IBinder binder) {
        if (binder == null) {
            return null;
        }
        if (binder instanceof Binder) {
            return (ISayHelloInterface) binder;
        }
        return new SayHelloBinderProxy(binder);
    }

    @Override
    public String sayHello(String name) {
        String result = null;
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeString(name);
            binder.transact(SAY_HELLO_CODE, data, reply, 0);
            result = reply.readString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            data.recycle();
            data = null;
            reply.recycle();
            reply = null;
        }
        return result;
    }
}
