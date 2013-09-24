package com.gmochid.tubes1papk;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class LocationActivity extends Activity implements LocationListener {
	
	private TextView latitudeField;
	private TextView longitudeField;
	private TextView providerField;
	private LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		
		latitudeField = (TextView) findViewById(R.id.latitudeField);
		longitudeField = (TextView) findViewById(R.id.longitudeField);
		providerField = (TextView) findViewById(R.id.provider);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		providerField.setText(locationManager.getBestProvider(criteria, false));
		Location location = locationManager.getLastKnownLocation(providerField.getText().toString());
		
		if(location != null) {
			onLocationChanged(location);
		} else {
			latitudeField.setText("Not available");
			longitudeField.setText("Not available");
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(providerField.getText().toString(), 400, 1, this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		latitudeField.setText(String.valueOf((double) location.getLatitude()));
		longitudeField.setText(String.valueOf((double) location.getLongitude()));
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
