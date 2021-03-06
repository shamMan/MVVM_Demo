/*
 * 1312424234234
 */

/*
 * 1312424234234
 */

/*
 * 1312424234234
 */
package com.via.demo.config;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.via.demo.base.NavBar.NavBarViewModel;
import com.via.demo.weather.presentation.WeatherViewModel;

/**
 * Created by ShawLiao on 2018/5/8.
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application) {
        mApplication = application;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection TryWithIdenticalCatches
        if (modelClass.isAssignableFrom(NavBarViewModel.class)) {
            return (T) new NavBarViewModel(mApplication);
        }
        else if (modelClass.isAssignableFrom(WeatherViewModel.class)) {
            //noinspection unchecked
            return (T) new WeatherViewModel(mApplication);
        } throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
