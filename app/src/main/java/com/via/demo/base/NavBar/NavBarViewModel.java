/*
 * 1312424234234
 */

/*
 * 1312424234234
 */
package com.via.demo.base.NavBar;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import static com.via.demo.define.BaseDefine.INTENT_ACTION_LOCATION_UPDATED;
import static com.via.demo.define.BaseDefine.INTENT_EXTRA_LATITUDE;
import static com.via.demo.define.BaseDefine.INTENT_EXTRA_LONGITUDE;

/**
 * 为 工具栏 提供数据
 * Created by ShawLiao on 2018/5/11.
 */
public class NavBarViewModel extends AndroidViewModel {

    protected Context mContext;

    public MutableLiveData<String> longitude = new MutableLiveData<>();
    public MutableLiveData<String> latitude = new MutableLiveData<>();

    public NavBarViewModel(
            Application context) {
        super(context);
        mContext = context.getApplicationContext();
        longitude.setValue("0.0");
        latitude.setValue("0.0");
        registerBroadcast();
    }

    @Override
    protected void onCleared() {
        Log.d("NavViewModel","onCleared called!");
        unRegisterBroadcast();
    }

    protected BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(INTENT_ACTION_LOCATION_UPDATED)) {
                Double dLongitude = intent.getDoubleExtra(INTENT_EXTRA_LONGITUDE,0.0);
                Double dLatitude = intent.getDoubleExtra(INTENT_EXTRA_LATITUDE,0.0);
                longitude.setValue(dLongitude.toString());
                latitude.setValue(dLatitude.toString());
            }
        }
    };

    private void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(INTENT_ACTION_LOCATION_UPDATED);
        getApplication().registerReceiver(mBroadcastReceiver , intentFilter);
    }

    private void unRegisterBroadcast() {
        getApplication().unregisterReceiver(mBroadcastReceiver);
    }
}
