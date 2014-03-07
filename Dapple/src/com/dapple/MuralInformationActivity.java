package com.dapple;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MuralInformationActivity extends Activity {

	private int tourSelected;
	private TextView muralTitle;
	private TextView muralArtist;
	private TextView muralInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mural_information);
		
		//Get access to GUI widgets
		muralTitle = (TextView) findViewById(R.id.txt_muralInfoTitle);
		muralArtist = (TextView) findViewById(R.id.txt_muralInfoArtist);
		muralInfo = (TextView) findViewById(R.id.txt_muralInfoParagraph);
		
		//Get the selected tour value from previous activity
		getTourSelection();
		
		//Pull the mural data from database (database not implemented yet)
		getMuralData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mural_information, menu);
		return true;
	}

	private void getTourSelection() {
		// TODO Auto-generated method stub
		
		tourSelected = getIntent().getIntExtra("TOUR_SELECTED", tourSelected);
		
	}

	private void getMuralData() {
		// TODO Auto-generated method stub
		
		if(tourSelected == 1)
		{
			//Temporary hardcoded values
			muralTitle.setText("The Vision of Samuel Hannaford");
			muralArtist.setText("Artworks");
			muralInfo.setText("OTR; 1308 Race St,Cincinnati, OH 45202");
		}
	}

}
