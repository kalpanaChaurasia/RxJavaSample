package com.rxjavasample;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

public class CodeExamples {

    private static final String TAG = "Sample";
    final String[] aStrings = {"A1", "A2", "A3", "A4"};
    final String[] bStrings = {"B1", "B2", "B3"};

    final Observable<String> aObservable = Observable.fromArray(aStrings);
    final Observable<String> bObservable = Observable.fromArray(bStrings);

    Observable names1 = Observable.fromArray(new String[]{"name1", "abc", "xyz", "lmn"});
    Observable names2 = Observable.fromArray(new String[]{"name1", "abc", "xyz", "lmn"});


    void doSomeWork() {

        /**
         * The Concat operator combines multiple Observables into one Observable and emits data, and transmits the data
         * in strict order.
         * The data of the previous Observable is not transmitted, and the data of the latter Observable cannot be transmitted.
         */
        Observable.concat(aObservable, bObservable)
                .subscribe(getObserver());

        /*
         * Using merge operator to combine Observable : merge does not maintain
         * the order of Observable.
         * It will emit all the 7 values may not be in order
         * Ex - "A1", "B1", "A2", "A3", "A4", "B2", "B3" - may be anything
         */
        Observable.merge(aObservable, bObservable)
                .subscribe(getObserver());


        Observable.sequenceEqual(names1, names2).subscribe(new SingleObserver<Boolean>() {

            @Override
            public void onError(Throwable throwable) {
                // TODO Auto-generated method stub
                System.out.println(throwable.getMessage());

            }

            @Override
            public void onSubscribe(Disposable disposable) {
                // TODO Auto-generated method stub
                System.out.println("is disposed:-" + disposable.isDisposed());

            }

            @Override
            public void onSuccess(Boolean value) {
                // TODO Auto-generated method stub
                if (value) {
                    System.out
                            .println("successfully finished comparision of two sequence and both sequences are equal");
                } else
                    System.out.println("successfully finished comparision of two sequence and both sequences are NOT equal");
            }
        });


        aObservable.startWith(bObservable).subscribe(getObserver());




        Observable.combineLatest(aObservable, bObservable, new BiFunction<String, String, String>() {
            @Override
            public String apply(@NonNull String s, @NonNull String s2) throws Exception {
                return s + "-" + s2;
            }
        }).subscribe(getObserver());



    }

    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, " onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, " onComplete");
            }
        };
    }
}
