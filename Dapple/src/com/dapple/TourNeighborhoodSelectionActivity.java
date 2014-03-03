package com.dapple;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class TourNeighborhoodSelectionActivity extends Activity {

	private static final int MAP_RESULT = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tour_neighborhood_selection);
		
		//Get the LocationManager as a system service.
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
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
			String current_lat = "39.105633";
			String current_longi = "-84.484589";
			String destt_lat = "39.137794";
			String  dest_longi = "-84.537926";
			
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
}
