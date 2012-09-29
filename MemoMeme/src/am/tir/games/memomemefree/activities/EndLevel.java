package am.tir.games.memomemefree.activities;

import java.util.Random;

import am.tir.games.memomemefree.utils.ScoreModel;
import am.tir.games.memomemefree.utils.User;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end_level);

		final Random rand = new Random();

		TextView scoreBefore = (TextView) findViewById(R.id.valueBefore);
		TextView scoreLast = (TextView) findViewById(R.id.valueLast);
		TextView scoreTotal = (TextView) findViewById(R.id.valueTotal);
		TextView gameOver = (TextView) findViewById(R.id.tvGameOver);
		TextView newRecord = (TextView) findViewById(R.id.textRecord);

		user = getIntent().getParcelableExtra("user");
		score = getIntent().getIntExtra("score", 0);

		level = user.getLevel();

		scoreBefore.setText(String.valueOf(user.getPoints() - score));
		scoreLast.setText(String.valueOf(score));
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
				Intent go = new Intent(EndLevel.this, Level.class);
				go.putExtra("user", user);
				startActivity(go);
				finish();
			}
		});

		restartButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent go = new Intent(EndLevel.this, Level.class);
				user.setPoints(user.getPoints() - score);
				user.setLevel(level - 1);
				go.putExtra("user", user);
				startActivity(go);
				finish();
			}
		});

		mainMenuButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		highScoresButtoon.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent go = new Intent(getBaseContext(), HighScores.class);
				startActivity(go);
				finish();
			}
		});

		if (getIntent().getExtras().getBoolean("isWin") && level != 10) {
			gameOver.setVisibility(View.GONE);
			imgWl.setImageResource(winPics[rand.nextInt(winPics.length)]);
			highScoresButtoon.setVisibility(View.GONE);
			newRecord.setVisibility(View.GONE);
		} else {
			user.setLevel(0);
			ScoreModel scoreModel = new ScoreModel(getBaseContext());
			scoreModel.update(user);
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
