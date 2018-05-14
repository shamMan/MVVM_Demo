/*
 * 1312424234234
 */

/*
 * 1312424234234
 */

/*
 * 1312424234234
 */
package com.via.demo.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.via.demo.base.NavBar.BuildinLocationRepository;
import com.via.demo.base.NavBar.NavBarViewModel;
import com.via.demo.base.NavBar.UseCaseStartLocation;
import com.via.demo.base.NavBar.UseCaseStopLocation;
import com.via.demo.config.ViewModelFactory;
import com.via.mvvm_demo.R;
import com.via.mvvm_demo.databinding.ActivityBaseBinding;

/**
 * Created by ShawLiao on 2018/5/8.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private String TAG = "BaseActivity";
    // 子类实现
    abstract protected void loadChildrenFragment();

    protected ActivityBaseBinding mBaseBinding;
    protected NavBarViewModel mNavViewModel;

    protected UseCaseStartLocation mCaseStartLocation;
    protected UseCaseStopLocation mCaseStopLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ");

        // 设置数据绑定
        mBaseBinding = DataBindingUtil.setContentView(this,R.layout.activity_base);
        mNavViewModel = obainViewModel(NavBarViewModel.class);
        mBaseBinding.setLifecycleOwner(this);
        mBaseBinding.setLongitude(mNavViewModel.longitude);
        mBaseBinding.setLatitude(mNavViewModel.latitude);

        mCaseStartLocation = new UseCaseStartLocation(BuildinLocationRepository.getInstance(this));
        UseCaseHandler.getInstance().execute(mCaseStartLocation, new UseCaseStartLocation.RequestValues(), new UseCase.UseCaseCallback<UseCaseStartLocation.ResponseValue>() {
            @Override
            public void onSuccess(UseCaseStartLocation.ResponseValue response) {

            }

            @Override
            public void onError(int errCode , @Nullable String desc) {
                Log.d(TAG,"StartLocation onError ");
                    if (desc != null) {
                    Toast.makeText(BaseActivity.this,desc,Toast.LENGTH_LONG);
                }
            }
        });

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

    /**
     * 获取viewModel接口，实际调用 {@link com.via.demo.config.ViewModelFactory}
     * @param modelClass
     * @return
     */
    protected <T extends ViewModel> T obainViewModel(@NonNull Class<T> modelClass) {
        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(modelClass);
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
