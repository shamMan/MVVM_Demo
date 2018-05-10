/*
 * 1312424234234
 */
package com.via.mvvm_demo;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.via.mvvm_demo.databinding.FragmentWeatherBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherFragment extends Fragment implements Contract.View {

    private Contract.Presenter mPresenter;
    private FragmentWeatherBinding mBinding;
    private WeatherViewModel mWeatherViewModel;

    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_weather, container, false);
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_weather,container,false);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mWeatherViewModel = MainActivity.obtainViewModel(getActivity());
        mBinding.setWeather(mWeatherViewModel.weather);
        mBinding.setPresenter(mPresenter);
        mBinding.setViewModel(mWeatherViewModel);
    }

    @Override
    public void setPresenter(@NonNull Contract.Presenter presenter) {
        mPresenter = presenter;
    }
}
