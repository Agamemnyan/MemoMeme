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

public class EndLevel extends Activity {

	private User user;
	private int score;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end_level);

		final Random rand = new Random();

		TextView scoreBefore = (TextView) findViewById(R.id.valueBefore);
		TextView scoreLast = (TextView) findViewById(R.id.valueLast);
		TextView scoreTotal = (TextView) findViewById(R.id.valueTotal);
		TextView gameOver = (TextView) findViewById(R.id.tvGameOver);

		user = getIntent().getParcelableExtra("user");
		score = getIntent().getIntExtra("score", 0);

		scoreBefore.setText(String.valueOf(user.getPoints()));
		scoreLast.setText(String.valueOf(score));
		scoreTotal.setText(String.valueOf(user.getPoints() + score));

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
				Intent go = null;
				switch (getIntent().getIntExtra("lastLevel", 13)) {
				case 1:
					go = new Intent(EndLevel.this, Level02.class);
					break;
				case 2:
					go = new Intent(EndLevel.this, Level03.class);
					break;
				case 3:
					go = new Intent(EndLevel.this, Level04.class);
					break;
				case 4:
					go = new Intent(EndLevel.this, Level04.class);
					break;
				}
				user.setPoints(user.getPoints() + score);
				go.putExtra("user", user);
				startActivity(go);
				finish();
			}
		});

		restartButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent go = null;
				switch (getIntent().getIntExtra("lastLevel", 13)) {
				case 1:
					go = new Intent(EndLevel.this, Level01.class);
					break;
				case 2:
					go = new Intent(EndLevel.this, Level02.class);
					break;
				case 3:
					go = new Intent(EndLevel.this, Level03.class);
					break;
				case 4:
					go = new Intent(EndLevel.this, Level04.class);
					break;
				}
				go.putExtra("user", user);
				startActivity(go);
				finish();
			}
		});

		mainMenuButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				user.setPoints(user.getPoints() + score);
				new ScoreModel(getBaseContext()).add(user);
				finish();
			}
		});

		highScoresButtoon.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				user.setPoints(user.getPoints() + score);
				new ScoreModel(getBaseContext()).add(user);
				Intent go = new Intent(getBaseContext(), HighScores.class);
				startActivity(go);
				finish();
			}
		});

		if (getIntent().getExtras().getBoolean("isWin")) {
			gameOver.setVisibility(View.GONE);
			imgWl.setImageResource(winPics[rand.nextInt(winPics.length)]);
			highScoresButtoon.setVisibility(View.GONE);
		} else {
			imgWl.setImageResource(losePics[rand.nextInt(losePics.length)]);
			nextButton.setVisibility(View.GONE);
			restartButton.setVisibility(View.GONE);
		}
	}
}
