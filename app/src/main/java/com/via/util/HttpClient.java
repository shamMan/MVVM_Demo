/*
 * 1312424234234
 */
package com.via.util;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * Created by ShawLiao on 2018/5/9.
 */
public class HttpClient {
    private static final String TAG = "HttpClient";

    private final String URL_BASE;

    private int mErrorCode;
    private String mErrorDesc;

    public interface HttpResult{
        abstract void onSuccess(JSONObject object);
        abstract void onFailed(int errorCode,String desc);
    }

    public HttpClient(String urlBase) {
        URL_BASE = urlBase;
    }

    public void httpGetAsycn(final String action, final Map<String,String> req, final HttpResult observe){
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject object = httpGetData(action, req);
                if (observe != null) {
                    if (object != null)
                        observe.onSuccess(object);
                    else
                        observe.onFailed(mErrorCode,mErrorDesc);
                }

            }
        }).start();
    }

    private JSONObject httpGetData(String action, final Map<String,String> req) {
        JSONObject result = null;
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            StringBuffer uri = new StringBuffer(URL_BASE);
            if (action != null)
                uri.append(action);
            if (req != null) {
                boolean first = true;
                Set<Map.Entry<String,String>> entrySet = req.entrySet();
                for (Map.Entry<String,String> entry:entrySet) {
                    if (first) {
                        first = false;
                        uri.append("?");
                    }
                    else {
                        uri.append("&");
                    }
                    String key = URLEncoder.encode(entry.getKey(), "UTF-8");
                    uri.append(key);
                    uri.append("=");
                    String value = URLEncoder.encode(entry.getValue(), "UTF-8");
                    uri.append(value);
                }
            }
            Log.d(TAG, "httpGetData " + uri);
            HttpGet httpget = new HttpGet(uri.toString());
            HttpResponse response;
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
            httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
            response = httpclient.execute(httpget);
            int code = response.getStatusLine().getStatusCode();
            mErrorCode = code;
            if (code == 200) {
                HttpEntity httpEntity = response.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                while (null != (line = reader.readLine())) {
                    sb.append(line);
                }
                String sbStr = sb.toString();
//                String rev = EntityUtils.toString(response.getEntity());//返回json格式
                Log.d(TAG, "HTTP Receive:\n" + sbStr);
                result = new JSONObject(sbStr);
            }
            else
            {
                Log.e(TAG,"HTTP Response error : code " + code);
                mErrorDesc = response.getStatusLine().getReasonPhrase();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
