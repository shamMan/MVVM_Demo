/*
 * 1312424234234
 */
package com.via.mvvm_demo;

import android.support.annotation.NonNull;

import com.via.mvvm_demo.pojo.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ShawLiao on 2018/5/9.
 */
public class JsonParser {

    /**
     *
     * @param json
     * @return
     */
    static boolean parseWeather(@NonNull JSONObject json,@NonNull Weather weather) {
        boolean bSuccess = false;
        if (json != null) {
            try {
                JSONArray heWeather6 = json.getJSONArray("HeWeather6");
                if (heWeather6 != null && heWeather6.length() > 0) {
                    JSONObject firWeather = heWeather6.getJSONObject(0);
                    JSONObject basic = firWeather.getJSONObject("basic");
                    JSONObject now = firWeather.getJSONObject("now");

                    String location = basic.getString("location");
                    weather.setCity(location);
                    weather.setDesc(now.getString("cond_txt"));
                    weather.setHum(now.getString("hum"));
                    weather.setTmp(now.getString("tmp"));
                    weather.setWindDir(now.getString("wind_dir"));
                    weather.setWindPower(now.getString("wind_sc"));
                    bSuccess = true;
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return bSuccess;
    }
}
