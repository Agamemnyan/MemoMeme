package am.tir.games.memomemechallenge.activities;

import static am.tir.games.memomemechallenge.utils.MemeSettings.cHeight_lvl_1;
import static am.tir.games.memomemechallenge.utils.MemeSettings.cHeight_lvl_2;
import static am.tir.games.memomemechallenge.utils.MemeSettings.cHeight_lvl_3;
import static am.tir.games.memomemechallenge.utils.MemeSettings.cHeight_lvl_4;
import static am.tir.games.memomemechallenge.utils.MemeSettings.dHeight;
import static am.tir.games.memomemechallenge.utils.MemeSettings.dWidth;
import static am.tir.games.memomemechallenge.utils.MemeSettings.isInit;
import static am.tir.games.memomemechallenge.utils.MemeSettings.is_sound_on;
import static am.tir.games.memomemechallenge.utils.MemeSettings.prefName;
import static am.tir.games.memomemechallenge.utils.MemeSettings.preferences;
import static am.tir.games.memomemechallenge.utils.MemeSettings.sound_mode;
import am.tir.games.memomemechallenge.utils.ScoreModel;
import am.tir.games.memomemechallenge.utils.SoundMode;
import am.tir.games.memomemechallenge.utils.User;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * @author Artak.Gevorgyan
 * 
 */
public class Main extends Activity implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initSettings();
		initStaticComponents();
	}

	@Override
	protected void onResume() {
		super.onResume();
		initDynamicComponents();
	}

	private void initSettings() {

		if (isInit)
			return;

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			dWidth = metrics.widthPixels;
			dHeight = metrics.heightPixels;
		} else {
			dWidth = metrics.heightPixels;
			dHeight = metrics.widthPixels;
		}

		// boardHeight = dHeight * 620 / 768;
		// boardBottomMargin = dHeight * 20 / 768;

		cHeight_lvl_1 = dHeight * 204 / 768;
		cHeight_lvl_2 = dHeight * 153 / 768;
		cHeight_lvl_3 = dHeight * 122 / 768;
		cHeight_lvl_4 = dHeight * 102 / 768;

		preferences = getSharedPreferences(prefName, MODE_PRIVATE);
		is_sound_on = preferences.getBoolean("is_sound_on", false);
		sound_mode = SoundMode.values()[preferences.getInt("sound_mode", 2)];

		isInit = true;
	}

	private void initStaticComponents() {
		Button buttonNewGame = (Button) findViewById(R.id.buttonGo);
		Button buttonSettings = (Button) findViewById(R.id.buttonSettings);
		Button buttonExit = (Button) findViewById(R.id.buttonExit);

		ImageButton ibHelp = (ImageButton) findViewById(R.id.ibHelp);
		ImageButton ibAbout = (ImageButton) findViewById(R.id.ibAbout);

		buttonNewGame.setOnClickListener(this);
		buttonSettings.setOnClickListener(this);
		buttonExit.setOnClickListener(this);

		ibHelp.setOnClickListener(this);
		ibAbout.setOnClickListener(this);
	}

	private void initDynamicComponents() {
		ScoreModel scoreModel = new ScoreModel(this);
		final User user = scoreModel.getContinueUserIfAny();

		Button buttonHighScoress = (Button) findViewById(R.id.buttonHighScores);
		Button buttonContinue = (Button) findViewById(R.id.buttonContinue);
		buttonHighScoress.setOnClickListener(this);

		if (scoreModel.getIsEmpty()) {
			buttonHighScoress.setVisibility(View.GONE);
		} else {
			buttonHighScoress.setVisibility(View.VISIBLE);
		}

		if (user == null) {
			buttonContinue.setVisibility(View.GONE);
		} else {
			buttonContinue.setVisibility(View.VISIBLE);
			buttonContinue.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					Intent go = new Intent(getBaseContext(), Splash.class);
					int minusScore = preferences.getInt("minusScore", 0);
					user.setPoints(user.getPoints() - minusScore);
					go.putExtra("user", user);
					go.putExtra("whichChallenge", user.getLevel() > 6 ? 2 : 1);
					go.putExtra("whichLevel", user.getLevel() + 1);
					startActivity(go);
				}
			});
		}
		scoreModel.close();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonGo:
			startActivity(new Intent(this, NewUser.class));
			break;
		case R.id.buttonSettings:
			startActivity(new Intent(this, Settings.class));
			break;
		case R.id.buttonHighScores:
			startActivity(new Intent(this, HighScores.class));
			break;
		case R.id.ibHelp:
			startActivity(new Intent(this, Help.class));
			break;
		case R.id.ibAbout:
			startActivity(new Intent(this, About.class));
			break;
		case R.id.buttonExit:
			finish();
			break;
		}
	}
}