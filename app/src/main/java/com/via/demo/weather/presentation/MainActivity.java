/*
 * 1312424234234
 */

/*
 * 1312424234234
 */

/*
 * 1312424234234
 */
package com.via.demo.weather.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.via.demo.base.BaseActivity;
import com.via.demo.config.ViewModelFactory;
import com.via.demo.weather.data.HeFengRepository;
import com.via.demo.weather.data.WeatherRepository;
import com.via.demo.weather.domian.UseCaseChangeCity;

public class MainActivity extends BaseActivity {

    private WeatherFragment mFragment;
    private Contract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle();

        // 生成 Presenter
        if (mPresenter == null) {
            mPresenter = new WeatherPresenter(obtainViewModel(this),obtainUseCaseChangeCity());
            mFragment.setPresenter(mPresenter);
        }
    }

    @Override
    protected void loadChildrenFragment() {
        if (mFragment == null) {
            mFragment = WeatherFragment.newInstance();
        }
        addFragment(mFragment,mFragment.getClass().toString());
    }

    public static WeatherViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(WeatherViewModel.class);
    }

    public static UseCaseChangeCity obtainUseCaseChangeCity() {
        return new UseCaseChangeCity(obtainWeatherRepository());
    }

    public static WeatherRepository obtainWeatherRepository() {
        return new HeFengRepository();
    }
}
