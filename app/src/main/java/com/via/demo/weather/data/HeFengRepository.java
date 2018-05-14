/*
 * 1312424234234
 */

/*
 * 1312424234234
 */

/*
 * 1312424234234
 */
package com.via.demo.weather.data;

import com.via.demo.weather.pojo.Weather;
import com.via.util.HttpClient;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ShawLiao on 2018/5/9.
 */
public class HeFengRepository implements WeatherRepository {
    private HttpClient mHttpClient;

    private static final String URL_BASE =  "https://free-api.heweather.com/";
    private static final String HF_KEY =  "26337271a12441a6a827a0cf92da5853";

    public HeFengRepository() {
        mHttpClient = new HttpClient(URL_BASE);
    }

    @Override
    public void queryWeather(String cityName,final RepositoryCallBack<Weather> callBack) {
        Map<String,String> param = generateParam();
        param.put("location",cityName);
        mHttpClient.httpGetAsycn("s6/weather/now", param, new HttpClient.HttpResult() {
            @Override
            public void onSuccess(JSONObject object) {
                String status = JsonParser.getStatus(object);
                if (status.equals("ok")) {
                    Weather weather = new Weather();
                    if(JsonParser.parseWeather(object,weather)) {
                        callBack.onSuccess(weather);
                        return;
                    }
                    else {
                        status = "parse json failed!";
                    }
                }
                callBack.onError(0 , status);
            }
            @Override
            public void onFailed(int errorCode,String desc) {
                callBack.onError(-1 , desc);
            }
        });
    }

    private Map<String,String> generateParam() {
        Map<String,String> param = new HashMap<>();
        param.put("key",HF_KEY);
        return param;
    }
}
