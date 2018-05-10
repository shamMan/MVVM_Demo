/*
 * 1312424234234
 */
package com.via.mvvm_demo;

/**
 * Created by ShawLiao on 2018/5/8.
 */

import com.via.mvvm.BasePresenter;
import com.via.mvvm.BaseView;

/**
 * VP 接口
 */
public interface Contract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        /**
         * 切换城市
         * @param cityname
         */
        void changeCity(String cityname);
    }
}
