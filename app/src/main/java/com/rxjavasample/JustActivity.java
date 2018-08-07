package com.rxjavasample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JustActivity extends BaseActivity {

    @Override
    int getLayoutResource(Bundle savedInstanceState) {
        return R.layout.activity_second;
    }

    @Override
    void initVariables(Bundle savedInstanceState) {

    }

    @Override
    void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                justOperatorExample();
            }
        });
    }


    private void justOperatorExample() {
        Observable<String> testObservable = Observable.just("one", "two", "three");
        Observer<String> animalsObserver = getAnimalObserver();
        testObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(animalsObserver);
    }

    private Observer<String> getAnimalObserver() {
        return new Observer<String>() {

            //Method will be called when an Observer subscribes to Observable
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            // This method will be called when Observable starts emitting the data.
            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext " + s);
            }

            //In case of any error, onError() method will be called.
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error : " + e.getLocalizedMessage());
            }

            //When an Observable completes the emission of all the items, onComplete() will be called.
            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
    }
}

