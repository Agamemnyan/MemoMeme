package com.memomeme.activities;

import com.memomeme.utils.MemeSettings;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class Main extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initSettings();
		initComponents();
	}

	private void initComponents() {

		// find xml views
		ImageView iv = (ImageView) findViewById(R.id.imageChallenge);

		Button buttonNewGame = (Button) findViewById(R.id.buttonGo);
		Button buttonLoadGame = (Button) findViewById(R.id.buttonLoad);
		Button buttonExit = (Button) findViewById(R.id.buttonExit);

		// set listeners
		buttonNewGame.setOnClickListener(this);
		buttonLoadGame.setOnClickListener(this);
		buttonExit.setOnClickListener(this);

		// set layoutparams
		LayoutParams ivl = new LayoutParams(MemeSettings.mChallengeHeight,
				MemeSettings.mChallengeHeight);
		ivl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		ivl.addRule(RelativeLayout.CENTER_HORIZONTAL);
		ivl.topMargin = MemeSettings.mChallengeTopMargin;
		iv.setLayoutParams(ivl);
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
	}

	private void initSettings() {

		if (MemeSettings.isInit)
			return;

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			MemeSettings.dWidth = metrics.widthPixels;
			MemeSettings.dHeight = metrics.heightPixels;
		} else {
			MemeSettings.dWidth = metrics.heightPixels;
			MemeSettings.dHeight = metrics.widthPixels;
		}

		MemeSettings.boardHeight = MemeSettings.dHeight * 660 / 768;
		MemeSettings.boardBottomMargin = MemeSettings.dHeight * 20 / 768;

		MemeSettings.cHeight1 = MemeSettings.dHeight * 199 / 768;
		MemeSettings.cHeight2 = MemeSettings.dHeight * 151 / 768;
		MemeSettings.cHeight3 = MemeSettings.dHeight * 122 / 768;
		MemeSettings.cHeight4 = MemeSettings.dHeight * 100 / 768;

		MemeSettings.mChallengeHeight = MemeSettings.dHeight * 450 / 768;
		MemeSettings.mChallengeTopMargin = MemeSettings.dHeight * 25 / 768;

		MemeSettings.timerTextSize = MemeSettings.dHeight * 88 / 768;
		MemeSettings.scoreTextSize = MemeSettings.dHeight * 88 / 768;
		MemeSettings.comboTextSize = MemeSettings.dHeight * 88 / 768;

		MemeSettings.isInit = true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonGo:
			startActivity(new Intent(this, NewUser.class));
			break;
		case R.id.buttonLoad:
			startActivity(new Intent(this, SelectLevel.class));
			break;
		case R.id.buttonExit:
			finish();
		}
	}
}