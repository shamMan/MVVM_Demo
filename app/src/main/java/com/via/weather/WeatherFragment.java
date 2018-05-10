/*
 * 1312424234234
 */
package com.via.mvvm_demo;


import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

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
        setUpViewModel();
        setUpBinding();
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setUpViewModel() {
        // 获取Activity关联的ViewModel,便于同一个Activity里Fragment账号之间共享数据源
        mWeatherViewModel = MainActivity.obtainViewModel(getActivity());
        // 监听加载失败
        mWeatherViewModel.loadFailed.observe(this, new Observer<WeatherViewModel.Error>() {
            @Override
            public void onChanged(@Nullable WeatherViewModel.Error error) {
                if (error != null) {
                    // 提示加载失败
                    String desc = error.getDesc();
                    if (desc == null || desc.isEmpty())
                        desc = "获取天气失败！";
                    Toast.makeText(getContext(),desc,Toast.LENGTH_LONG).show();
                }
            }
        });
        mWeatherViewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    // 检查 关闭 键盘
                    if (mBinding.tvCity.hasFocus()) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(mBinding.tvCity.getWindowToken(), 0) ;
                        mBinding.tvCity.clearFocus();
                    }
                }
            }
        });
    }

    private void setUpBinding() {
        // 填入 Layout 变量
        mBinding.setWeather(mWeatherViewModel.weather);
        mBinding.setPresenter(mPresenter);
        mBinding.setViewModel(mWeatherViewModel);
        mBinding.setLoading(mWeatherViewModel.loading);
        // 关联 当前 Fragment 和 LiveData 数据（LiveData 会监听Fragment生命周期，避免Fragment非活跃时更新UI）
        mBinding.setLifecycleOwner(this);
    }

    @Override
    public void setPresenter(@NonNull Contract.Presenter presenter) {
        mPresenter = presenter;
    }
}
