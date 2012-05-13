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
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameover);

		final Random rand = new Random();

		TextView textWL = (TextView) findViewById(R.id.textWinLose);
		ImageView imgWl = (ImageView) findViewById(R.id.gameoverImage);

		Integer[] winPics = new Integer[] { R.drawable.win01,
				R.drawable.win02 };
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
		Button exitButton = (Button) findViewById(R.id.exitButton);
		
		restartButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent go = new Intent(v.getContext(),
						com.memomeme.activities.Game.class);
				startActivity(go);
			}
		});
		
		exitButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
//				Intent go = new Intent(v.getContext(),	org.ultradark.memomeme.Game.class);
//				go.putExtra("isExit", true);
//				startActivity(go);
				setResult(15);
				finish();
			}
		});
	}
}
