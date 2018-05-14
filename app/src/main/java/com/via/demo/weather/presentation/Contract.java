/*
 * 1312424234234
 */

/*
 * 1312424234234
 */
package com.via.demo.weather.presentation;

/**
 * Created by ShawLiao on 2018/5/8.
 */

import com.via.demo.base.BasePresenter;
import com.via.demo.base.BaseView;

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
