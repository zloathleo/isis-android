package com.magus.isis.net;

import android.util.Log;

import com.magus.isis.common.utils.encoding.JsonUtils;
import com.magus.isis.net.res.AuthorizationUserRes;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Leo on 2016/7/11.
 */
public class IOkHttpClient {
  static final OkHttpClient client = new OkHttpClient();

    public static final <T> T httpGet(String url, Class<T> valueType) throws IOException {
        return httpGet(url,null,valueType);
    }

    public static final <T> T httpGet(String url,String params,Class<T> valueType) throws IOException {
        String fullUrl = getFullUrl(url);
        if(!StringUtils.isBlank(params)){
            fullUrl = fullUrl + "?" + params;
        }
        Log.w("Get请求路径",fullUrl);

        Request request = new Request.Builder()
                .url(fullUrl)
                .build();

        Response response = client.newCall(request).execute();

        byte[] bodyData = response.body().bytes();
        return JsonUtils.ToObject(bodyData,valueType);
    }

    public static final <T> T httpPut(String url, String u, String p,Class<T> valueType) throws IOException {
        String fullUrl = getFullUrl(url);
        FormBody formBody = new FormBody.Builder()
                .add("userName", u)
                .add("password", p)
                .build();

        Request request = new Request.Builder()
                .url(fullUrl)
                .put(formBody)
                .build();

        Response response = client.newCall(request).execute();
        byte[] bodyData = response.body().bytes();
        return JsonUtils.ToObject(bodyData,valueType);
    }

    private static final  String getFullUrl(String rurl){
        return String.format("http://%s:%d%s%s%s.do",IOkHttpUrlConst.Host,IOkHttpUrlConst.Port,IOkHttpUrlConst.ProjectUrl,IOkHttpUrlConst.Version,rurl);
    }

}
