package com.memomeme.activities;

import com.memomeme.activities.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);     
        
        if (getIntent().getExtras() != null && getIntent().getBooleanExtra("isExit", false)) {
			finish();
		}

        Button buttonNewGame = (Button) findViewById(R.id.buttonGo);
        buttonNewGame.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent go = new Intent(v.getContext(), com.memomeme.activities.Game.class);
				startActivity(go);			
			}
		});
        
        Button buttonExit = (Button) findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});        
    }
}