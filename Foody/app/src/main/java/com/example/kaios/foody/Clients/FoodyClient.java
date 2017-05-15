package com.example.kaios.foody.Clients;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kaios on 5/12/2017.
 */
public class FoodyClient {
    private static final String BASE_URL = "http://10.0.2.2/WebServiceFoody/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String url, Header[] headers, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(30*1000);
        client.setConnectTimeout(30*1000);
        client.setResponseTimeout(30*1000);
        client.get(context, getAbsoluteUrl(url), headers, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}