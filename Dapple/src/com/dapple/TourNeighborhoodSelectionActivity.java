package com.dapple;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class TourNeighborhoodSelectionActivity extends Activity implements LocationListener {

	private static final int MAP_RESULT = 10;
	private LocationManager locationManager;
	private double currentLatitude;
	private double currentLongitude;
	private TextView timeValue;
	private TextView mileValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tour_neighborhood_selection);

		//Get the LocationManager as a system service.
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		timeValue = (TextView) findViewById(R.id.timeValue);
		mileValue = (TextView) findViewById(R.id.mileValue);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tour_neighborhood_selection, menu);
		return true;
	}

	public void onStartTourClicked(View v)
	{
		
		//temporary hard coded coordinates
		String current_lat = String.valueOf(currentLatitude);
		String current_longi = String.valueOf(currentLongitude);
		String destt_lat = "39.137794";
		String dest_longi = "-84.537926";

		//Create Map intent; concatenate input of source and destination coordinates
		Intent startMap = new Intent(Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?" + "saddr="+ current_lat+","+current_longi + "&daddr="+ destt_lat+","+ dest_longi));

		//Set default app to google maps 
		startMap.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");

		//start activity
		startActivityForResult(startMap, MAP_RESULT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		//Only execute if intent returns RESULT_OK
		if (resultCode == RESULT_OK)
		{
			//Execute according to requestCode
			if (requestCode == MAP_RESULT)
			{
				//
				Intent muralInfo = new Intent(this, MuralInformationActivity.class);

				startActivity(muralInfo);
			}
		}
	}

	/**
	 *  The following methods handle GPS functionality
	 */

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// subscribe to location service.
		requestLocationUpdates();
		//Toast.makeText(this, "Latitude: " + currentLatitude + '\n' + "currentLongitude: " + currentLongitude, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		// Turn off GPS updates
		removeLocationUpdates();
	}

//	@Override
//	protected void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
//
//		removeLocationUpdates();
//	}

	private void requestLocationUpdates() {
		// TODO Auto-generated method stub
		if (locationManager != null) {

			// request current location
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 0, this);
		}
	}

	private void removeLocationUpdates() {
		// TODO Auto-generated method stub
		if (locationManager != null) {
			locationManager.removeUpdates(this);
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		currentLatitude = location.getLatitude();
		currentLongitude = location.getLongitude();
		updateUIForLocation();
	}

	private void updateUIForLocation() {
		// update our user interface.
		timeValue.setText("" + currentLatitude);
		mileValue.setText("" + currentLongitude);
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) { //String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) { //provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String arg0) { //provider) {
		// TODO Auto-generated method stub

	}
}
