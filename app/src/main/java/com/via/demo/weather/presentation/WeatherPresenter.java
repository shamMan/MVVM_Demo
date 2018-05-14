/*
 * 1312424234234
 */

/*
 * 1312424234234
 */
package com.via.demo.weather.presentation;

import android.support.annotation.Nullable;
import android.util.Log;

import com.via.demo.base.UseCase;
import com.via.demo.base.UseCaseHandler;
import com.via.demo.weather.domian.UseCaseChangeCity;

/**
 * Created by ShawLiao on 2018/5/9.
 */
public class WeatherPresenter implements Contract.Presenter {

    private static final String TAG = "WeatherPresenter";

    WeatherViewModel mViewModel;

    UseCaseChangeCity mUseCaseChangeCity;

    public WeatherPresenter(WeatherViewModel viewModel,UseCaseChangeCity useCaseChangeCity) {
        mViewModel = viewModel;
        mUseCaseChangeCity = useCaseChangeCity;
    }

    @Override
    public void changeCity(String cityname) {
        Log.d(TAG,"changeCity " + cityname);
        mViewModel.loading.setValue(Boolean.TRUE);
        UseCaseHandler.getInstance().execute(mUseCaseChangeCity, new UseCaseChangeCity.RequestValues(cityname), new UseCase.UseCaseCallback<UseCaseChangeCity.ResponseValue>() {
            @Override
            public void onSuccess(UseCaseChangeCity.ResponseValue response) {
                Log.d(TAG,"changeCity onSuccess " + response.getWeather());
                mViewModel.loading.setValue(Boolean.FALSE);
                mViewModel.loadFailed.setValue(null);
                mViewModel.weather.setValue(response.getWeather());
            }

            @Override
            public void onError(int errCode , @Nullable String desc) {
                Log.d(TAG,"changeCity onError ");
                mViewModel.loading.setValue(Boolean.FALSE);
                mViewModel.loadFailed.setValue(new WeatherViewModel.Error(errCode,desc));
            }
        });
    }
}
