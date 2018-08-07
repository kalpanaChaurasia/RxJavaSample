package com.rxjavasample;

import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;

//When we implement EventBus pattern with RxJava, we call it as RxBus.
public class RxBus {

    private static RxBus sInstance = new RxBus();

    private static PublishSubject<Object> bus = PublishSubject.create();

    private RxBus() {

    }

    public static RxBus getsInstance() {
        return sInstance;
    }


    public void send(Object o) {
        bus.onNext(o);
    }

    public Observer<Object> register() {
        return bus;
    }
}
