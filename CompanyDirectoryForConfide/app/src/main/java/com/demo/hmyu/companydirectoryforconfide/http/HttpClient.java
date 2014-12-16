package com.demo.hmyu.companydirectoryforconfide.http;

import android.util.Log;

import com.demo.hmyu.companydirectoryforconfide.model.Employees;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by Hsiang-Min on 12/15/14.
 */
public class HttpClient {

    private String TAG = HttpClient.class.getSimpleName();
    public static final int HTTP_OK = 200;
    public static final String CONTACT_URL = "https://s3-us-west-2.amazonaws.com/confide-media/interview/employees.json";
    private String url;

    public Employees getEmployees() {

        Log.v(TAG,"getEmployees");

        String result = query(CONTACT_URL);
        Log.v(TAG,"getEmployees result " + result);
        if (result == null) {
            return null;
        }
        return Employees.parse(result);
    }

    private String query(String url) {
        this.url = url;
        String responseString = null;
        try {

            // create httpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();

            // make request to url and get the response
            HttpResponse httpResponse;
            httpResponse = httpClient.execute(new HttpGet(url));

            // get response content
            HttpEntity entity = httpResponse.getEntity();

            if (httpResponse.getStatusLine().getStatusCode() == HTTP_OK) {
                responseString = EntityUtils.toString(entity);
                Log.v(TAG,"response " + responseString);
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }

        return responseString;
    }

}
