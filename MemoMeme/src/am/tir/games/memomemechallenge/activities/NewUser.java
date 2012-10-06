package am.tir.games.memomemechallenge.activities;

import am.tir.games.memomemechallenge.utils.ScoreModel;
import am.tir.games.memomemechallenge.utils.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Artak.Gevorgyan
 * 
 */
public class NewUser extends Activity {

	EditText etNewUser;
	Button bSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_user);

		initViews();
		initListeners();
	}

	private void initViews() {
		etNewUser = (EditText) findViewById(R.id.editTextNewUser);
		bSubmit = (Button) findViewById(R.id.buttonSubmitUser);
	}

	private void initListeners() {
		bSubmit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent go = new Intent(v.getContext(), Splash.class);
				ScoreModel scoreModel = new ScoreModel(getBaseContext());
				User user = scoreModel.add(etNewUser.getText().toString());
				scoreModel.close();
				go.putExtra("user", user);
				go.putExtra("whichChallenge", 1);
				go.putExtra("whichLevel", 1);
				startActivity(go);
				finish();
			}
		});
	}
}