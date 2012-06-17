package com.memomeme.activities;

import java.util.Random;

import com.memomeme.activities.R;
import com.memomeme.utils.MemeSettings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;


public class EndLevel extends Activity {
	
	private LayoutParams lp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end_level);

		final Random rand = new Random();
		
		lp = new LayoutParams(MemeSettings.dHeight, MemeSettings.dHeight);

		ImageView imgWl = (ImageView) findViewById(R.id.gameoverImage);
		
		imgWl.setLayoutParams(lp);

		Integer[] winPics = new Integer[] { R.drawable.win01, R.drawable.win02 };
		Integer[] losePics = new Integer[] { R.drawable.lose01,
				R.drawable.lose02 };

		Button nextButton = (Button) findViewById(R.id.nextButton);
		Button restartButton = (Button) findViewById(R.id.restartButton);
		Button mainMenuButton = (Button) findViewById(R.id.buttonMainMenu);

		nextButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				switch (getIntent().getIntExtra("lastLevel", 13)) {
				case 1:
					startActivity(new Intent(EndLevel.this, Level02.class));
					break;
				case 2:
					startActivity(new Intent(EndLevel.this, Level03.class));
					break;
				case 3:
					startActivity(new Intent(EndLevel.this, Level04.class));
					break;
				case 4:
					startActivity(new Intent(EndLevel.this, Level04.class));
					break;
				}

				finish();
			}
		});

		restartButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				switch (getIntent().getIntExtra("lastLevel", 13)) {
				case 1:
					startActivity(new Intent(EndLevel.this, Level01.class));
					break;
				case 2:
					startActivity(new Intent(EndLevel.this, Level02.class));
					break;
				case 3:
					startActivity(new Intent(EndLevel.this, Level03.class));
					break;
				case 4:
					startActivity(new Intent(EndLevel.this, Level04.class));
					break;
				}
				finish();
			}
		});

		mainMenuButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		if (getIntent().getExtras().getBoolean("isWin")) {
			imgWl.setImageResource(winPics[rand.nextInt(winPics.length)]);
		} else {
			imgWl.setImageResource(losePics[rand.nextInt(losePics.length)]);
			LinearLayout layout = (LinearLayout) findViewById(R.id.game_over_layout);
			layout.removeView(nextButton);
		}
	}

}
