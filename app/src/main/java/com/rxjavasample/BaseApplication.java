package com.rxjavasample;

import android.app.Application;

public class BaseApplication extends Application {

    private static BaseApplication instance;
    private RxBus bus;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        bus = new RxBus();
    }

    public RxBus getBus() {
        return bus;
    }
}
