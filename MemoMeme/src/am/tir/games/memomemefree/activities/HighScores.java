package am.tir.games.memomemefree.activities;

import am.tir.games.memomemefree.utils.ScoreModel;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HighScores extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.high_scores);

		TextView scores = (TextView) findViewById(R.id.textScores);
		scores.setText(new ScoreModel(this).getAllFormated());

		Button mainMenuButton = (Button) findViewById(R.id.buttonMainMenu);
		mainMenuButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}
