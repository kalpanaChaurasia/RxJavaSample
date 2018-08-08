package com.rxjavasample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class FilterFragment extends BaseFragment {

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
        filterOperatorExample();
    }

    private void filterOperatorExample() {
        Observable<String> aObservable = Observable.fromArray("one", "two", "three", "four", "five", "six", "seven");
        Observer<String> aObserver = getFilterObserver();
        aObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.toLowerCase().startsWith("s");
            }
        }).subscribe(aObserver);

        txtOuput.append("Observable<String> aObservable = Observable.fromArray(\"one\", \"two\", \"three\", \"four\", \"five\", \"six\", \"seven\");\n" +
                "Observer<String> aObserver = getFilterObserver();\n" +
                "aObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(new Predicate<String>() {\n" +
                "            @Override\n" +
                "            public boolean test(String s) {\n" +
                "                return s.toLowerCase().startsWith(\"s\");\n" +
                "            }\n" +
                "        }).subscribe(aObserver);\n\n\n\n Output:=>\n");

    }

    private Observer<String> getFilterObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext " + s);
                txtOuput.append(s+"\n");
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

