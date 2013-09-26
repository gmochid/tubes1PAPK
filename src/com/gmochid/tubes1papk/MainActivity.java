package com.gmochid.tubes1papk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;

import org.json.JSONObject;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int REQUEST_CODE = 200;
	
	private TextView resultField;
	private TextView latitudeField;
	private TextView longitudeField;
	private TextView imageNameField;
	private Uri fileUri;
	private LocationManager locationManager;
	private String provider;
	private LocalLocationListener locationListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		resultField = (TextView) findViewById(R.id.displaytext);
		latitudeField = (TextView) findViewById(R.id.displaylatitude);
		longitudeField = (TextView) findViewById(R.id.displaylongitude);
		imageNameField = (EditText) findViewById(R.id.imagenamefield);
		
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		locationListener = new LocalLocationListener();
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);
		
		locationListener.onLocationChanged(location);
		
		Log.i("PAPK", "MainActivity");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onResetClick(View view) {
		new ResetTask().execute();
	}
	
	public void onGetRemainingClick(View view) {
		new RemainingTask().execute();
	}
	
	public void onSearchClick(View view) {
		new SearchTask().execute(latitudeField.getText().toString(), longitudeField.getText().toString());
	}
	
	public void onUpdateLocationClick(View view) {
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	}
	
	public void onCaptureImageClick(View view) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		startActivityForResult(intent, REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		InputStream stream = null;
	    if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
			try {
				stream = getContentResolver().openInputStream(data.getData());
				Bitmap bmp = BitmapFactory.decodeStream(stream);
				FileOutputStream out = new FileOutputStream("a.jpg");
				bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}
	
	private class ResetTask extends AsyncTask<Object, Void, JSONObject> {
		@Override
		protected JSONObject doInBackground(Object... params) {
			APIHelper apihelper = new APIHelper();
			return apihelper.actionResetChest();
		}
		
		@Override
		protected void onPostExecute(JSONObject result) {
			resultField.setText(result.toString());
		}
	}
	
	private class RemainingTask extends AsyncTask<Object, Void, JSONObject> {
		@Override
		protected JSONObject doInBackground(Object... params) {
			APIHelper apihelper = new APIHelper();
			return apihelper.actionGetUnachievedChestCount();
		}
		
		@Override
		protected void onPostExecute(JSONObject result) {
			resultField.setText(result.toString());
		}
	}
	
	private class SearchTask extends AsyncTask<Object, Void, JSONObject> {
		@Override
		protected JSONObject doInBackground(Object... params) {
			APIHelper apihelper = new APIHelper();
			return apihelper.actionRetrieveChectLocation((String) params[0], (String) params[1]);
		}
		
		@Override
		protected void onPostExecute(JSONObject result) {
			resultField.setText(result.toString());
		}
	}
	
	private class LocalLocationListener implements LocationListener {
		@Override
        public void onStatusChanged(String provider, int status, Bundle extras){
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onLocationChanged(Location location) {
            latitudeField.setText(String.format("%9.6f", location.getLatitude()));
            longitudeField.setText(String.format("%9.6f", location.getLongitude()));
        }
	}
}
