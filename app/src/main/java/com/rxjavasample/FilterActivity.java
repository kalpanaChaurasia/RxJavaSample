package com.rxjavasample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class FilterActivity extends BaseActivity {


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
                filterOperatorExample();
            }
        });
    }

    private void filterOperatorExample() {
        Observable<String> aObservable = Observable.fromArray("Ant", "Ape",
                "Bat", "Bee", "Bear", "Butterfly",
                "Cat", "Crab", "Cod",
                "Dog", "Dove",
                "Fox", "Frog");
        Observer<String> aObserver = getFilterObserver();
        aObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.toLowerCase().startsWith("b");
            }
        }).subscribe(aObserver);

    }

    private Observer<String> getFilterObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete ");
            }
        };
    }


}

