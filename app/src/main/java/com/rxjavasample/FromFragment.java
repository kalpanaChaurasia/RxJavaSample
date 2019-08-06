package com.rxjavasample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FromFragment extends BaseFragment {

    @BindView(R.id.output)
    TextView txtOuput;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_second, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        justOperatorExample();
    }

    private void justOperatorExample() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);
        numbers.add(9);

        Integer[]  num = {1, 2, 3, 4, 5};

     Observable<Integer> observable = Observable.fromArray(num);
     Observer observer = getNumberObserver();
     observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

        Observable<Integer> observable1 = Observable.fromIterable(numbers);
        Observer observer1 = getNumberObserver();
        observable1.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer1);

    }

    private Observer<Integer> getNumberObserver() {
        return new Observer<Integer>() {

            //Method will be called when an Observer subscribes to Observable
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integers) {
                Log.d(TAG, "next - "+integers);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}

