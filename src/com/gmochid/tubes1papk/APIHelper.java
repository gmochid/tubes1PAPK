package com.gmochid.tubes1papk;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class APIHelper {
	
	public static String URL = "http://milestone.if.itb.ac.id/pbd/index.php";
	
	public static String GROUP_ID = "6bdd668d7304a37967b09e01412c6f8a";
	
	public static String FIELD_ACTION = "action";
	public static String FIELD_GROUP_ID = "group_id";
	public static String FIELD_LONGITUDE = "longitude";
	public static String FIELD_LATITUDE = "latitude";
	
	public static String ACTION_RESET = "reset";
	public static String ACTION_RETRIEVE = "retrieve";
	public static String ACTION_NUMBER = "number";
	public static String ACTION_ACQUIRE = "acquire";
	
	public JSONObject actionResetChest() {
		Log.i("PAPK", "APIHelper.actionResetChest()");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(FIELD_ACTION, ACTION_RESET));
		params.add(new BasicNameValuePair(FIELD_GROUP_ID, GROUP_ID));
		return new HttpHelper().connect(URL, "POST", params);
	}
	
	public JSONObject actionGetUnachievedChestCount() {
		Log.i("PAPK", "APIHelper.actionGetUnachievedChestCount()");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(FIELD_ACTION, ACTION_NUMBER));
		params.add(new BasicNameValuePair(FIELD_GROUP_ID, GROUP_ID));
		return new HttpHelper().connect(URL, "GET", params);
	}
	
	public JSONObject actionRetrieveChectLocation(String latitude, String longitude) {
		Log.i("PAPK", "APIHelper.actionRetrieveChectLocation()");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(FIELD_ACTION, ACTION_RETRIEVE));
		params.add(new BasicNameValuePair(FIELD_GROUP_ID, GROUP_ID));
		params.add(new BasicNameValuePair(FIELD_LATITUDE, latitude));
		params.add(new BasicNameValuePair(FIELD_LONGITUDE, longitude));
		return new HttpHelper().connect(URL, "GET", params);
	}
	
	public JSONObject actionAcquireChest() {
		// TODO
		return null;
	}
}
