package com.rxjavasample;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ObservableTester {
    public static void main(String[] args) throws InterruptedException {

        //The Single class represents the single value response. Single observable can only emit either a single successful value or an error. It does not emit onComplete event.
        //Create the observable
        Single<String> testSingle = Single.just("Hello World");

        //Create an observer
        Disposable disposable = testSingle
                .delay(2, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith(
                        new DisposableSingleObserver<String>() {

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onSuccess(String value) {
                                System.out.println(value);
                            }
                        });
        Thread.sleep(3000);
        //start observing
        disposable.dispose();

        //The MayBe class represents deferred response. MayBe observable can emit either a single successful value or no value.
        //Create an observer
        disposable = Maybe.just("Hello World")
                .delay(2, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith(new DisposableMaybeObserver<String>() {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onSuccess(String value) {
                        System.out.println(value);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });
        Thread.sleep(3000);
        //start observing
        disposable.dispose();


        //The Completable class represents deferred response. Completable observable can either indicate a successful completion or error.

        disposable = Completable.complete()
                .delay(2, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onStart() {
                        System.out.println("Started!");
                    }
                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });
        Thread.sleep(3000);
        //start observing
        disposable.dispose();
    }
}
