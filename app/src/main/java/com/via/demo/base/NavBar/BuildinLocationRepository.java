/*
 * 1312424234234
 */
package com.via.demo.base.NavBar;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import static com.via.demo.define.BaseDefine.INTENT_ACTION_LOCATION_UPDATED;
import static com.via.demo.define.BaseDefine.INTENT_EXTRA_LATITUDE;
import static com.via.demo.define.BaseDefine.INTENT_EXTRA_LONGITUDE;

/**
 * Created by ShawLiao on 2018/5/13.
 */
public class BuildinLocationRepository implements LocationRepository {
    private static final String TAG = "BuildinLocation";

    static BuildinLocationRepository instance;

    private Context mContext;
    private LocationRepository.RepositoryCallBack mCallBack;

    static public BuildinLocationRepository getInstance(Context context) {
        if (instance == null) {
            instance = new BuildinLocationRepository(context);
        }
        return instance;
    }

    private BuildinLocationRepository(Context context) {
        mContext = context;
    }

    private LocationListener mListener = new LocationListener() {
        @Override
        /*当地理位置发生改变的时候调用*/
        public void onLocationChanged(Location location) {
            Log.d(TAG,"onLocationChanged " + location);
            broadcastLocation(location);
        }

        /* 当状态发生改变的时候调用*/
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.d(TAG,"onStatusChanged " + s);
        }

        /*当定位者启用的时候调用*/
        @Override
        public void onProviderEnabled(String s) {
            Log.d(TAG,"onProviderEnabled " + s);
        }

        @Override
        public void onProviderDisabled(String s) {
            mCallBack.onError(-2,"请打开GPS");
        }
    };

    @Override
    public void start(final LocationRepository.RepositoryCallBack callBack) {
        mCallBack = callBack;
        //检查是否开启权限！
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mCallBack.onError(-1,"权限不够");
            return;
        }
        LocationManager locationManager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mCallBack.onError(-2,"请打开GPS");
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, mListener, Looper.getMainLooper());
        // 使用GPS获取上一次的地址
        Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        broadcastLocation(location);
    }


    @Override
    public void stop() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
        locationManager.removeUpdates(mListener);
    }

    private void broadcastLocation(Location location) {
        Intent intent = new Intent();
        intent.setAction(INTENT_ACTION_LOCATION_UPDATED);
        intent.putExtra(INTENT_EXTRA_LONGITUDE, location.getLongitude());
        intent.putExtra(INTENT_EXTRA_LATITUDE, location.getLatitude());
        mContext.sendBroadcast(intent);
    }
}
