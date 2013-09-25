package com.gmochid.tubes1papk;

import org.json.JSONObject;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView resultField;
	private TextView latitudeField;
	private TextView longitudeField;
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
	
	public void onUpdateLocationClick(View view) {
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
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
