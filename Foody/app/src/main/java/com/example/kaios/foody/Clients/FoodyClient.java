package com.example.kaios.foody.Clients;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

/**
 * Created by kaios on 5/12/2017.
 */
public class FoodyClient {
    private static final String BASE_URL = "http://10.0.2.2/WebServiceFoody/";//url

    private static AsyncHttpClient client = new AsyncHttpClient();


    //
    public static void get(Context context, String url, Header[] headers, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(60*1000);
        client.setConnectTimeout(60*1000);
        client.setResponseTimeout(60*1000);
        client.setMaxConnections(60);
        client.get(context, getAbsoluteUrl(url), headers, params, responseHandler);
    }

    //đăng ký
    public static void register(String url, RequestParams params,
                                AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(60*1000);
        client.setConnectTimeout(60*1000);
        client.setResponseTimeout(60*1000);
        client.setMaxConnections(60);
        client.get(getAbsoluteUrl(url),params,responseHandler);
    }
    //đổi passWord
    public static void ChangePass(String url, RequestParams params,
                                AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(60*1000);
        client.setConnectTimeout(60*1000);
        client.setResponseTimeout(60*1000);
        client.setMaxConnections(60);
        client.get(getAbsoluteUrl(url),params,responseHandler);
    }

    //đổi hình đại diện
    public static void changeimage(Context context, String url, HttpEntity entity, String contenType,
                                   AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(60 * 1000);
        client.setConnectTimeout(60 * 1000);
        client.setResponseTimeout(60 * 1000);
        client.setMaxConnections(60);
        client.post(context, getAbsoluteUrl(url), entity, contenType, responseHandler);
    }
    //thêm dịa điểm
    public static void ThemDiaDiem(Context context, String url, HttpEntity entity, String contenType,
                                   AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(60 * 1000);
        client.setConnectTimeout(60 * 1000);
        client.setResponseTimeout(60 * 1000);
        client.setMaxConnections(60);
        client.post(context, getAbsoluteUrl(url), entity, contenType, responseHandler);
    }

    //nối chuỗi thành url hoànchinhr
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}