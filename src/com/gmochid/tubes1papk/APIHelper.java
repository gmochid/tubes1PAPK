package com.gmochid.tubes1papk;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

public class APIHelper {
	
	public static String URL = "http://milestone.if.itb.ac.id/pbd/index.php";
	
	public static String GROUP_ID = "6bdd668d7304a37967b09e01412c6f8a";
	
	public static String FIELD_ACTION = "action";
	public static String FIELD_GROUP_ID = "group_id";
	
	public static String ACTION_RESET = "reset";
	public static String ACTION_RETRIEVE = "retrieve";
	public static String ACTION_NUMBER = "number";
	public static String ACTION_ACQUIRE = "acquire";
	
	private JSONArray actionResetChest() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(FIELD_ACTION, ACTION_RESET));
		params.add(new BasicNameValuePair(FIELD_GROUP_ID, GROUP_ID));
		return new HttpHelper().connect(URL, "POST", params);
	}
	
	private JSONArray actionGetUnachievedChestCount() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(FIELD_ACTION, ACTION_NUMBER));
		params.add(new BasicNameValuePair(FIELD_GROUP_ID, GROUP_ID));
		return new HttpHelper().connect(URL, "GET", params);
	}
	
	private JSONArray actionRetrieveChectLocation(double latitude, double longitude) {
		// TODO
		return null;
	}
	
	private JSONArray actionAcquireChest() {
		// TODO
		return null;
	}
	
	public JSONArray doAction(String Action, List<Object> params) {
		return new JSONArray();
	}
	
	private class InternetConnection extends AsyncTask<Object, Void, JSONArray> {
		@Override
		protected JSONArray doInBackground(Object... params) {
			String action = (String) params[0];
			if(action.equals(ACTION_RESET)) {
				return actionResetChest();
			} else if(action.equals(ACTION_RETRIEVE)) {
				return actionGetUnachievedChestCount();
			} else if(action.equals(ACTION_NUMBER)) {
				List<Double> pos = (List<Double>) params[1]; 
				return actionRetrieveChectLocation(pos.get(0), pos.get(1));
			} else if(action.equals(ACTION_ACQUIRE)) {
				return actionAcquireChest();
			}
			return null;
		}
	}
}
