package am.tir.games.memomemefree.activities;

import static am.tir.games.memomemefree.utils.MemeSettings.boardBottomMargin;
import static am.tir.games.memomemefree.utils.MemeSettings.boardHeight;
import static am.tir.games.memomemefree.utils.MemeSettings.cHeight_lvl_1;
import static am.tir.games.memomemefree.utils.MemeSettings.cHeight_lvl_2;
import static am.tir.games.memomemefree.utils.MemeSettings.cHeight_lvl_3;
import static am.tir.games.memomemefree.utils.MemeSettings.cHeight_lvl_4;
import static am.tir.games.memomemefree.utils.MemeSettings.dHeight;
import static am.tir.games.memomemefree.utils.MemeSettings.dWidth;
import static am.tir.games.memomemefree.utils.MemeSettings.isInit;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
		initComponents();
	}

	private void initComponents() {

		Button buttonNewGame = (Button) findViewById(R.id.buttonGo);
		Button buttonSettings = (Button) findViewById(R.id.buttonSettings);
		Button buttonHighScoress = (Button) findViewById(R.id.buttonHighScores);
		Button buttonExit = (Button) findViewById(R.id.buttonExit);

		// set listeners
		buttonNewGame.setOnClickListener(this);
		buttonSettings.setOnClickListener(this);
		buttonHighScoress.setOnClickListener(this);
		buttonExit.setOnClickListener(this);
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

		boardHeight = dHeight * 620 / 768;
		boardBottomMargin = dHeight * 20 / 768;

		cHeight_lvl_1 = dHeight * 204 / 768;
		cHeight_lvl_2 = dHeight * 153 / 768;
		cHeight_lvl_3 = dHeight * 122 / 768;
		cHeight_lvl_4 = dHeight * 102 / 768;

		isInit = true;
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
		case R.id.buttonExit:
			finish();
			break;
		}
	}
}