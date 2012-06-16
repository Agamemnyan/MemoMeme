package com.memomeme.activities;

import com.memomeme.activities.R;
import com.memomeme.utils.MemeSettings;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);     

        Button buttonNewGame = (Button) findViewById(R.id.buttonGo);
        buttonNewGame.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent go = new Intent(v.getContext(), NewUser.class);
				startActivity(go);			
			}
		});
        
        Button buttonExit = (Button) findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});       
        
        DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			MemeSettings.dWidth = metrics.widthPixels;
			MemeSettings.dHeight = metrics.heightPixels;
		} else {
			MemeSettings.dWidth = metrics.heightPixels;
			MemeSettings.dHeight = metrics.widthPixels;
		}
			
    }
}