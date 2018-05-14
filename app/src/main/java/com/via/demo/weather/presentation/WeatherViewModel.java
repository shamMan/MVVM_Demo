/*
 * 1312424234234
 */

/*
 * 1312424234234
 */
package com.via.demo.weather.presentation;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;

import com.via.demo.weather.pojo.Weather;

/**
 * Created by ShawLiao on 2018/5/8.
 */
public class WeatherViewModel extends ViewModel {

    private Context mContext;
    // 天气查询结果
    public MutableLiveData<Weather> weather = new MutableLiveData<>();
    // 查询中状态标志
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    // 查询城市名
    public MutableLiveData<String> cityName = new MutableLiveData<>();
    // 查询失败状态标志
    public MutableLiveData<Error> loadFailed = new MutableLiveData<>();

    public WeatherViewModel(
            Application context) {
        mContext = context;
        Weather defaultWeather = new Weather();
        defaultWeather.setCity("上海");
        weather.setValue(defaultWeather);
        loading.setValue(Boolean.FALSE);
        loadFailed.setValue(null);
    }

    public void cityNameChanged(Editable editable) {
        cityName.setValue(editable.toString());
    }

    static public class Error {
        private int code;
        private String desc;
        public Error(int code , @Nullable String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
