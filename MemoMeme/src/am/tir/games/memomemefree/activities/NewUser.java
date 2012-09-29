package am.tir.games.memomemefree.activities;

import am.tir.games.memomemefree.utils.ScoreModel;
import am.tir.games.memomemefree.utils.User;
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
				Intent go = new Intent(v.getContext(), Level.class);
				User user = new ScoreModel(getBaseContext()).add(etNewUser
						.getText().toString());
				go.putExtra("user", user);
				startActivity(go);
				finish();
			}
		});
	}
}
