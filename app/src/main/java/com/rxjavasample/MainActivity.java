package com.rxjavasample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {


    @BindView(R.id.spinner1)
    Spinner spinner;
    @BindView(R.id.content)
    FrameLayout contentFragment;

    @Override
    int getLayoutResource(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    void initData(Bundle savedInstanceState) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operators, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    void bindData() {
        ButterKnife.bind(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String selectedItem = adapterView.getItemAtPosition(position).toString();
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new FilterFragment();
                break;
            case 1:
                fragment = new FilterFragment();
                break;
            case 2:
                fragment = new CreateFragment();
                break;
            case 3:
                fragment = new FilterFragment();
                break;
            case 4:
                fragment = new FilterFragment();
                break;
            case 5:
                fragment = new FilterFragment();
                break;
            case 6:
                fragment = new FilterFragment();
                break;
            case 7:
                fragment = new DeferFragment();
                break;
            case 8:
                fragment = new FilterFragment();
                break;
            case 9:
                fragment = new FromFragment();
                break;
            case 10:
                fragment = new FilterFragment();
                break;
            case 11:
                fragment = new FilterFragment();
                break;
            case 12:
                fragment = new FilterFragment();
                break;
            case 13:
                fragment = new JustFragment();
                break;
            default:
                fragment = null;
                showToast("Implementation is in progress");
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

