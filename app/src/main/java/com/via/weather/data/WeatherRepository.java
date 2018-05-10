/*
 * 1312424234234
 */

/*
 * 1312424234234
 */

/*
 * 1312424234234
 */
package com.via.weather.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.via.mvvm.BaseRepository;
import com.via.weather.pojo.Weather;

/**
 * Created by ShawLiao on 2018/5/9.
 */
public interface WeatherRepository extends BaseRepository {

    void queryWeather(String cityName,final RepositoryCallBack<Weather> callBack);

    public interface RepositoryCallBack<R> {
        void onSuccess(@NonNull R response);
        void onError(int errorCode , @Nullable String desc);
    }
}
