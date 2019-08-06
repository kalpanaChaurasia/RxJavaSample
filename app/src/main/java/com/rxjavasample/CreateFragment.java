package com.rxjavasample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateFragment extends BaseFragment {

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
        CreateExample();
    }

    private void CreateExample() {
        //This operator creates an Observable from scratch by calling observer methods programmatically.
        // An emitter is provided through which we can call the respective interface methods when needed.
        Observer<String> animalsObserver = getNumberObserver();
        String[] al = {"one", "two", "three", "four", "five", "six", "seven"};
        final List<String> alphabets = Arrays.asList(al);

        Observable<String> testObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                try {
                    for (String alphabet : alphabets) {
                        emitter.onNext(alphabet);
                    }
                    emitter.onComplete();
                }catch (Exception e){
                    emitter.onError(e);
                }

            }
        });
        testObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(animalsObserver);
        txtOuput.append("\n\nObservable<String> testObservable = Observable.just(\"one\", \"two\", \"three\", \"four\");\n" +
                "Observer<String> animalsObserver = getNumberObserver();\n" +
                "testObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(animalsObserver); \n\nOutput:=>\n");
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

