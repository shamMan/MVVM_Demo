/*
 * 1312424234234
 */
package com.via.mvvm_demo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.text.Editable;

import com.via.mvvm_demo.pojo.Weather;

/**
 * Created by ShawLiao on 2018/5/8.
 */
public class WeatherViewModel extends AndroidViewModel {

    public MutableLiveData<Weather> weather = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<String> cityName = new MutableLiveData<>();

    private Context mContext;

    public WeatherViewModel(
            Application context) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.

        Weather defaultWeather = new Weather();
        defaultWeather.setCity("上海");
        weather.setValue(defaultWeather);
        loading.setValue(Boolean.FALSE);
    }

    public void cityNameChanged(Editable editable) {
        cityName.setValue(editable.toString());
    }
}
