package com.gmochid.tubes1papk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.os.AsyncTask;
import android.util.Log;

public class HttpHelper {
	public JSONArray connect(String url, String method, List<NameValuePair> params) {
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		JSONArray ret = new JSONArray();
		
		try {
			Log.i("HttpHelper", "HttpHelper.connect()");
			if(method.equals("GET")) {
				HttpGet httpget = new HttpGet(url + URLEncodedUtils.format(params, "utf-8"));
				Log.i("HttpHelper", String.format("HttpHelper.connect() - GET [%s]", 
						url + URLEncodedUtils.format(params, "utf-8")));
				response = client.execute(httpget);
			} else {
				HttpPost httppost = new HttpPost(url);
				httppost.setEntity(new UrlEncodedFormEntity(params));
				Log.i("HttpHelper", String.format("HttpHelper.connect() - POST [%s]", 
						url));
				response = client.execute(httppost);
			}
			
	        Log.i("Praeda",response.getStatusLine().toString());
	        
	        HttpEntity entity = response.getEntity();
	        
	        Log.i("HttpHelper", String.format("HttpHelper.connect() - entity [%s]", entity.getContentLength()));

	        if (entity != null) {
	            InputStream instream = entity.getContent();
	            String result = convertStreamToString(instream);
	            ret = new JSONArray(result);
	            instream.close();
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	Log.e("HttpHelper", "HttpHelper.connect() ", e);
	    }
		
		Log.i("HttpHelper", "HttpHelper.connect() finish");
		
		return ret;
	}
	
	private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
