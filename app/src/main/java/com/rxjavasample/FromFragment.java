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
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);

    // Observable<Integer> observable = Observable.fr

    }

    private Observer<String> getNumberObserver() {
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
                txtOuput.append(s + "\n");
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

