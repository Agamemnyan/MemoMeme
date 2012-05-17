package com.memomeme.activities;

import java.util.Random;

import com.memomeme.activities.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOver extends Activity {

	Intent go;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameover);

		final Random rand = new Random();

		go = new Intent(this, com.memomeme.activities.Game.class);

		TextView textWL = (TextView) findViewById(R.id.textWinLose);
		ImageView imgWl = (ImageView) findViewById(R.id.gameoverImage);

		Integer[] winPics = new Integer[] { R.drawable.win01, R.drawable.win02 };
		Integer[] losePics = new Integer[] { R.drawable.lose01,
				R.drawable.lose02 };

		if (getIntent().getExtras().getBoolean("isWin")) {
			textWL.setText(R.string.youWin);
			imgWl.setImageResource(winPics[rand.nextInt(winPics.length)]);
		} else {
			textWL.setText(R.string.youLose);
			imgWl.setImageResource(losePics[rand.nextInt(losePics.length)]);
		}

		Button restartButton = (Button) findViewById(R.id.restartButton);
		Button mainMenuButton = (Button) findViewById(R.id.buttonMainMenu);

		restartButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(go);
				finish();
			}
		});

		mainMenuButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		startActivity(go);
		finish();
	}
}
