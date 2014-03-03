package com.dapple;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class TourNeighborhoodSelectionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tour_neighborhood_selection);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tour_neighborhood_selection, menu);
		return true;
	}

		public void onStartTourClicked(View v)
		{
			String current_lat = "39.105633";
			String current_longi = "-84.484589";
			String destt_lat = "39.137794";
			String  dest_longi = "-84.537926";
			final Intent intent = new Intent(Intent.ACTION_VIEW,
					 Uri.parse("http://maps.google.com/maps?" + "saddr="+ current_lat+","+current_longi + "&daddr="+ destt_lat+","+ dest_longi));

					 intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
					
					 startActivity(intent); 
			
		}
}
