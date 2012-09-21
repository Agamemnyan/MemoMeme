package am.tir.games.memomemefree.activities;

import am.tir.games.memomemefree.utils.MemeSettings;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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

		Button buttonNewGame = (Button) findViewById(R.id.buttonGo);
		Button buttonHighScoress = (Button) findViewById(R.id.buttonHighScores);
		Button buttonExit = (Button) findViewById(R.id.buttonExit);

		// set listeners
		buttonNewGame.setOnClickListener(this);
		buttonHighScoress.setOnClickListener(this);
		buttonExit.setOnClickListener(this);
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

		MemeSettings.boardHeight = MemeSettings.dHeight * 620 / 768;
		MemeSettings.boardBottomMargin = MemeSettings.dHeight * 20 / 768;

		MemeSettings.cHeight_lvl_1 = MemeSettings.dHeight * 204 / 768;
		MemeSettings.cHeight_lvl_2 = MemeSettings.dHeight * 153 / 768;
		MemeSettings.cHeight_lvl_3 = MemeSettings.dHeight * 122 / 768;
		MemeSettings.cHeight_lvl_4 = MemeSettings.dHeight * 102 / 768;

		MemeSettings.mChallengeHeight = MemeSettings.dHeight * 450 / 768;
		MemeSettings.mChallengeTopMargin = MemeSettings.dHeight * 25 / 768;

		MemeSettings.isInit = true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonGo:
			startActivity(new Intent(this, NewUser.class));
			break;
		case R.id.buttonHighScores:
			startActivity(new Intent(this, HighScores.class));
			break;
		case R.id.buttonExit:
			finish();
			break;
		}
	}
}