/*
 * 1312424234234
 */

/*
 * 1312424234234
 */

/*
 * 1312424234234
 */
package com.via.demo.weather.domian;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.via.demo.base.UseCase;
import com.via.demo.weather.data.WeatherRepository;
import com.via.demo.weather.pojo.Weather;

/**
 * Created by ShawLiao on 2018/5/9.
 */
public class UseCaseChangeCity extends UseCase<UseCaseChangeCity.RequestValues , UseCaseChangeCity.ResponseValue> {

    private WeatherRepository mRepository;

    public UseCaseChangeCity(@NonNull WeatherRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        String cityName = values.getCityName();
        mRepository.queryWeather(cityName, new WeatherRepository.RepositoryCallBack<Weather>() {
            @Override
            public void onSuccess(@NonNull Weather response) {
                ResponseValue rv = new ResponseValue(response);
                getUseCaseCallback().onSuccess(rv);
            }

            @Override
            public void onError(int errorCode, @Nullable String desc) {
                getUseCaseCallback().onError(errorCode,desc);
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final String mCityName;
        public RequestValues(String cityName) {
            this.mCityName = cityName;
        }
        public String getCityName() {
            return mCityName;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final Weather mWeather;
        public ResponseValue(Weather weather) {
            this.mWeather = weather;
        }
        public Weather getWeather() {
            return mWeather;
        }
    }
}
