package com.gmochid.tubes1papk;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class APIHelper {
	
	public static String URL = "milestone.if.itb.ac.id/pbd/index.php";
	
	public static String GROUP_ID = "6bdd668d7304a37967b09e01412c6f8a";
	
	public static String FIELD_ACTION = "action";
	public static String FIELD_GROUP_ID = "group_id";
	
	public static String ACTION_RESET = "reset";
	public static String ACTION_RETRIEVE = "retrieve";
	public static String ACTION_NUMBER = "number";
	
	public static JSONArray actionResetChest() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(FIELD_ACTION, ACTION_RESET));
		params.add(new BasicNameValuePair(FIELD_GROUP_ID, GROUP_ID));
		return HttpHelper.connect(URL, "POST", params);
	}
	
	public static JSONArray actionGetUnachievedChestCount() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(FIELD_ACTION, ACTION_NUMBER));
		params.add(new BasicNameValuePair(FIELD_GROUP_ID, GROUP_ID));
		return HttpHelper.connect(URL, "GET", params);
	}
	
	public static JSONArray actionRetrieveChectLocation(double latitude, double longitude) {
		// TODO
		return null;
	}
	
	public static JSONArray actionAcquireChest() {
		// TODO
		return null;
	}
}
