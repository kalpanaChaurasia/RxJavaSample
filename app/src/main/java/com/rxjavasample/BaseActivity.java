package com.rxjavasample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    abstract int getLayoutResource(Bundle savedInstanceState);

    abstract void initData(Bundle savedInstanceState);

    abstract void bindData();


    protected final String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource(savedInstanceState));
        bindData();
        initData(savedInstanceState);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


    /**
     * Start Activity with bundle
     *
     * @param clazz  class name
     * @param bundle parameter
     */
    protected void startActivity(Class clazz, Bundle bundle) {
        try {
            Intent intent = new Intent(this, clazz);
            if (bundle != null)
                intent.putExtras(bundle);
            startActivity(intent);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Start Activity without bundle
     *
     * @param clazz Class name
     */
    protected void startActivity(Class clazz) {
        startActivity(clazz, null);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
