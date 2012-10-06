package am.tir.games.memomemechallenge.activities;

import static am.tir.games.memomemechallenge.utils.MemeSettings.POINTS_FOR_SEC_LEVEL_1_1;
import static am.tir.games.memomemechallenge.utils.MemeSettings.POINTS_FOR_SEC_LEVEL_2_1;
import static am.tir.games.memomemechallenge.utils.MemeSettings.POINTS_FOR_SEC_LEVEL_2_2;
import static am.tir.games.memomemechallenge.utils.MemeSettings.POINTS_FOR_SEC_LEVEL_3_1;
import static am.tir.games.memomemechallenge.utils.MemeSettings.POINTS_FOR_SEC_LEVEL_3_2;
import static am.tir.games.memomemechallenge.utils.MemeSettings.POINTS_FOR_SEC_LEVEL_3_3;
import static am.tir.games.memomemechallenge.utils.MemeSettings.POINTS_FOR_SEC_LEVEL_4_1;
import static am.tir.games.memomemechallenge.utils.MemeSettings.POINTS_FOR_SEC_LEVEL_4_2;
import static am.tir.games.memomemechallenge.utils.MemeSettings.POINTS_FOR_SEC_LEVEL_4_3;
import static am.tir.games.memomemechallenge.utils.MemeSettings.POINTS_FOR_SEC_LEVEL_4_4;

import java.util.Random;

import am.tir.games.memomemechallenge.utils.ScoreModel;
import am.tir.games.memomemechallenge.utils.User;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Artak.Gevorgyan
 * 
 */
public class EndLevel extends Activity {

	private User user;
	private int score;
	private int level;
	private int pointPerSecond;
	private int seconds;
	private boolean isWin;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end_level);

		final Random rand = new Random();
		isWin = getIntent().getExtras().getBoolean("isWin");

		TextView scoreBefore = (TextView) findViewById(R.id.valueBefore);
		TextView scoreLast = (TextView) findViewById(R.id.valueLast);
		TextView scoreForSeconds = (TextView) findViewById(R.id.valueForSeconds);
		TextView scoreTotal = (TextView) findViewById(R.id.valueTotal);
		TextView gameOver = (TextView) findViewById(R.id.tvGameOver);
		TextView newRecord = (TextView) findViewById(R.id.textRecord);

		user = getIntent().getParcelableExtra("user");
		score = getIntent().getIntExtra("score", 0);
		seconds = ((int) getIntent().getLongExtra("milliSeconds", 0)) / 1000;

		level = user.getLevel();

		switch (level) {
		case 0:
			pointPerSecond = 0;
			break;
		case 1:
			pointPerSecond = POINTS_FOR_SEC_LEVEL_1_1;
			break;
		case 2:
			pointPerSecond = POINTS_FOR_SEC_LEVEL_2_1;
			break;
		case 3:
			pointPerSecond = POINTS_FOR_SEC_LEVEL_2_2;
			break;
		case 4:
			pointPerSecond = POINTS_FOR_SEC_LEVEL_3_1;
			break;
		case 5:
			pointPerSecond = POINTS_FOR_SEC_LEVEL_3_2;
			break;
		case 6:
			pointPerSecond = POINTS_FOR_SEC_LEVEL_3_3;
			break;
		case 7:
			pointPerSecond = POINTS_FOR_SEC_LEVEL_4_1;
			break;
		case 8:
			pointPerSecond = POINTS_FOR_SEC_LEVEL_4_2;
			break;
		case 9:
			pointPerSecond = POINTS_FOR_SEC_LEVEL_4_3;
			break;
		case 10:
			pointPerSecond = POINTS_FOR_SEC_LEVEL_4_4;
			break;

		default:
			break;
		}

		scoreBefore.setText(String.valueOf(user.getPoints() - score
				- pointPerSecond * seconds));
		scoreLast.setText(String.valueOf(score));
		scoreForSeconds.setText(seconds + "×" + pointPerSecond + "=" + seconds
				* pointPerSecond);
		scoreTotal.setText(String.valueOf(user.getPoints()));

		ImageView imgWl = (ImageView) findViewById(R.id.gameoverImage);

		TypedArray winArray = getResources()
				.obtainTypedArray(R.array.win_faces);
		int[] winPics = new int[winArray.length()];
		for (int i = 0; i < winArray.length(); i++) {
			winPics[i] = winArray.getResourceId(i, 0);
		}

		TypedArray loseArray = getResources().obtainTypedArray(
				R.array.lose_faces);
		int[] losePics = new int[loseArray.length()];
		for (int i = 0; i < loseArray.length(); i++) {
			losePics[i] = loseArray.getResourceId(i, 0);
		}

		Button nextButton = (Button) findViewById(R.id.nextButton);
		Button restartButton = (Button) findViewById(R.id.restartButton);
		Button mainMenuButton = (Button) findViewById(R.id.buttonMainMenu);
		Button highScoresButtoon = (Button) findViewById(R.id.highScoresButton);

		nextButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent go = new Intent(EndLevel.this, Splash.class);
				go.putExtra("user", user);
				go.putExtra("whichChallenge", level > 6 ? 2 : 1);
				go.putExtra("whichLevel", level + 1);
				startActivity(go);
				finish();
			}
		});

		restartButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent go = new Intent(EndLevel.this, Splash.class);

				user.setPoints(user.getPoints() - score - pointPerSecond
						* seconds);
				if (isWin) {
					user.setLevel(level - 1);
				}
				go.putExtra("user", user);
				go.putExtra("whichChallenge", level > 7 ? 2 : 1);
				startActivity(go);
				finish();
			}
		});

		mainMenuButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent go = new Intent(EndLevel.this, Splash.class);
				go.putExtra("whichChallenge", isWin ? 3 : 4);
				startActivity(go);
				finish();
			}
		});

		highScoresButtoon.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent go = new Intent(getBaseContext(), HighScores.class);
				go.putExtra("userId", user.getId());
				startActivity(go);
				finish();
			}
		});

		if (isWin && level != 10) {
			gameOver.setVisibility(View.GONE);
			imgWl.setImageResource(winPics[rand.nextInt(winPics.length)]);
			highScoresButtoon.setVisibility(View.GONE);
			newRecord.setVisibility(View.GONE);
		} else {
			ScoreModel scoreModel = new ScoreModel(getBaseContext());
			if (scoreModel.getBestScore(user.getId()) > user.getPoints()) {
				newRecord.setVisibility(View.GONE);
			}
			scoreModel.close();
			nextButton.setVisibility(View.GONE);
			if (level != 10) {
				imgWl.setImageResource(losePics[rand.nextInt(losePics.length)]);
			} else {
				imgWl.setImageResource(R.integer.won_all);
				gameOver.setText(R.string.gameOverAll);
			}
		}
	}
}
