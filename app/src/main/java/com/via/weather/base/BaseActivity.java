/*
 * 1312424234234
 */

/*
 * 1312424234234
 */

/*
 * 1312424234234
 */
package com.via.weather.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.via.mvvm_demo.R;

/**
 * Created by ShawLiao on 2018/5/8.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private String TAG = "BaseActivity";
    // 子类实现
    abstract protected void loadChildrenFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ");
        initContentView(R.layout.activity_base);
        if (savedInstanceState == null) {
            loadChildrenFragment();
        } else {

        }
    }
    /**
     * 初始化contentview
     */
    private void initContentView(int layoutResID) {
        setContentView(layoutResID);
    }

    public Fragment findFragmentByTag(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        return fm.findFragmentByTag(tag);
    }

    public void addFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fg = fm.findFragmentByTag(tag);
        ViewGroup vg = findViewById(R.id.fragment_container);
        Log.d(TAG,"vg : " + vg);
        if (fg != null) {
            Log.d(TAG, "addFragment: " + fragment + " replace fg: " + fg);
            fm.beginTransaction().replace(R.id.fragment_container, fragment, tag).commit();
        } else {
            Log.d(TAG, "addFragment: " + fragment);
            fm.beginTransaction().add(R.id.fragment_container, fragment, tag).commit();
        }
    }
}
