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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TourNeighborhoodSelectionActivity extends Activity implements LocationListener {

	
	private static final int MAP_RESULT = 10;
	private LocationManager locationManager;
	private double currentLatitude;
	private double currentLongitude;
	private LinearLayout tourImage;
	private ImageView imageOTR;
	private ImageView imageDwnTwn;
	private TextView timeValue;
	private TextView mileValue;
	private TextView muralNum;
	private TextView muralInformation;
	private TextView currentLocation;
	private TextView muralTitleHeader;
	private String tourString = " Murals";
	private String muralMilesString = " Mile";
	private String durationString = " minutes";
	
	//temporary hard coded values
	private static final String muralCoorLong1 = "-84.516631"; //The Vision of Samuel Hannaford
	private static final String muralCoorLat1 = "39.109251";
	private int tourValue = 1;
	private String muralName = "Over The Rhine Mural Tour";
	private String muralDescription = "Take a tour of murals around historic OTR, one of the oldest neighborhoods in the country.";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tour_neighborhood_selection);

		//Get the LocationManager as a system service.
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		//Get access to GUI widgets
		muralTitleHeader = (TextView) findViewById(R.id.muralTitleHeader);
		muralNum = (TextView) findViewById(R.id.muralNumberValue);
		timeValue = (TextView) findViewById(R.id.timeValue);
		mileValue = (TextView) findViewById(R.id.mileValue);
		muralInformation = (TextView) findViewById(R.id.muralInformation);
		tourImage = (LinearLayout) findViewById(R.id.ll_hsv_images);
		imageOTR = (ImageView) findViewById(R.id.img_tour_otr);
		imageDwnTwn = (ImageView) findViewById(R.id.img_tour_downtown);
		currentLocation = (TextView) findViewById(R.id.currentLocation);
		
		//Set temporary hardcode vales to GUI widgets 
		muralTitleHeader.setText(muralName);
		muralNum.setText(10 + tourString);
		timeValue.setText(30 + durationString);
		mileValue.setText(1 + muralMilesString);
		muralInformation.setText(muralDescription);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tour_neighborhood_selection, menu);
		return true;
	}

	public void onStartTourClicked(View v)
	{
		//Convert coordinates from double to string
		String source_lat = String.valueOf(currentLatitude);
		String source_longi = String.valueOf(currentLongitude);
		
		//temporary hard coded coordinates
		String destt_lat = muralCoorLat1;
		String dest_longi = muralCoorLong1;

		//Create Map intent; concatenate input of source and destination coordinates
		Intent startMap = new Intent(Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?" + "saddr="+ source_lat+","+source_longi + "&daddr="+ destt_lat+","+ dest_longi));

		//Set default app to google maps 
		startMap.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");

		//start activity
		startActivityForResult(startMap, MAP_RESULT);
	
	}
	
//	public void onTourImageSwipe()
//	{
//		
//	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		//Only execute if intent returns RESULT_OK
//		if (resultCode == RESULT_OK)
//		{
			//Execute according to requestCode
			if (requestCode == MAP_RESULT)
			{
				//create MuralInformationActivity intent
				Intent muralInfo = new Intent(this, MuralInformationActivity.class);
				
				//load intent with tourValue
				muralInfo.putExtra("TOUR_SELECTED", tourValue);
				
				//Start MuralInformationActivity
				startActivity(muralInfo);
			}
//		}
//		else
//		{
//			Toast.makeText(this, "Camera return not ok", Toast.LENGTH_LONG).show();
//		}
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
		// Temporary for testing purposes
		currentLocation.setText("Lat: " + currentLatitude + " Long: " + currentLongitude);
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
