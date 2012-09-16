package am.tir.games.memomemefree.activities;

import java.util.Random;

import am.tir.games.memomemefree.utils.ScoreModel;
import am.tir.games.memomemefree.utils.User;
import android.app.Activity;
import android.content.Intent;
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

		TextView scoreText = (TextView) findViewById(R.id.scoreText);
		user = getIntent().getParcelableExtra("user");
		score = getIntent().getIntExtra("score", 0);

		scoreText
				.setText(user.getUserName() + " " + (user.getPoints() + score));

		ImageView imgWl = (ImageView) findViewById(R.id.gameoverImage);

		Integer[] winPics = new Integer[] { R.drawable.win_01,
				R.drawable.win_02, R.drawable.win_03, R.drawable.win_04,
				R.drawable.win_05 };
		Integer[] losePics = new Integer[] { R.drawable.lose_01,
				R.drawable.lose_02, R.drawable.lose_03, R.drawable.lose_04,
				R.drawable.lose_05 };

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
			imgWl.setImageResource(winPics[rand.nextInt(winPics.length)]);
			highScoresButtoon.setVisibility(View.GONE);
		} else {
			imgWl.setImageResource(losePics[rand.nextInt(losePics.length)]);
			nextButton.setVisibility(View.GONE);
			restartButton.setVisibility(View.GONE);
		}
	}
}
