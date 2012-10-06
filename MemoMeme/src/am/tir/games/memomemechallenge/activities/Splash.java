package am.tir.games.memomemechallenge.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Artak.Gevorgyan
 * 
 */
public class Splash extends Activity {

	private long ms;
	private CountDownTimer cdt;
	private Intent intent;
	private int whichChallenge;
	private int whichLevel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		ImageView logo = (ImageView) findViewById(R.id.ivLogo);
		TextView tvLevelName = (TextView) findViewById(R.id.tvLevelName);

		if (savedInstanceState != null) {
			ms = savedInstanceState.getLong("ms");
		} else {
			ms = 2000;
		}

		whichChallenge = getIntent().getIntExtra("whichChallenge", 0);
		whichLevel = getIntent().getIntExtra("whichLevel", 0);

		intent = new Intent(this, Level.class);
		intent.putExtras(getIntent().getExtras());

		switch (whichChallenge) {
		case 1:
			logo.setImageResource(R.integer.challenge_accepted);
			break;
		case 2:
			logo.setImageResource(R.integer.challenge_accepted_drunk);
			break;
		case 3:
			logo.setImageResource(R.integer.challenge_denied);
			break;
		case 4:
			logo.setImageResource(R.integer.challenge_failed);
			break;
		default:
			break;
		}

		switch (whichLevel) {
		case 1:
			tvLevelName.setText(R.string.lvl_0_name);
			break;
		case 2:
			tvLevelName.setText(R.string.lvl_1_name);
			break;
		case 3:
			tvLevelName.setText(R.string.lvl_2_name);
			break;
		case 4:
			tvLevelName.setText(R.string.lvl_3_name);
			break;
		case 5:
			tvLevelName.setText(R.string.lvl_4_name);
			break;
		case 6:
			tvLevelName.setText(R.string.lvl_5_name);
			break;
		case 7:
			tvLevelName.setText(R.string.lvl_6_name);
			break;
		case 8:
			tvLevelName.setText(R.string.lvl_7_name);
			break;
		case 9:
			tvLevelName.setText(R.string.lvl_8_name);
			break;
		case 10:
			tvLevelName.setText(R.string.lvl_9_name);
			break;
		default:
			tvLevelName.setVisibility(View.GONE);
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		cdt = new CountDownTimer(ms, 100) {

			public void onTick(long millisUntilFinished) {
				ms = millisUntilFinished;
			}

			public void onFinish() {
				if (whichChallenge < 3) {
					startActivity(intent);
				}
				finish();
			}
		};

		cdt.start();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putLong("ms", ms);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onPause() {
		cdt.cancel();
		super.onPause();
	}
}