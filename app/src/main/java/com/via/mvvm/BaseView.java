/*
 * 1312424234234
 */
package com.via.mvvm;

/**
 * Created by ShawLiao on 2018/5/9.
 */
public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}
