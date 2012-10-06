package am.tir.games.memomemechallenge.activities;

import static am.tir.games.memomemechallenge.utils.MemeSettings.*;
import am.tir.games.memomemechallenge.utils.SoundMode;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

/**
 * @author Artak.Gevorgyan
 * 
 */
public class Settings extends Activity implements OnClickListener {
	private SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		initSoundSettings();
	}

	private void initSoundSettings() {

		// Toggle
		RadioButton rbSoundOff = (RadioButton) findViewById(R.id.rbSoundOff);
		RadioButton rbSoundOn = (RadioButton) findViewById(R.id.rbSoundOn);

		rbSoundOff.setOnClickListener(this);
		rbSoundOn.setOnClickListener(this);

		if (is_sound_on) {
			rbSoundOn.setChecked(true);
		} else {
			rbSoundOff.setChecked(true);
		}

		// Packs
		RadioButton rbSoundTrollish = (RadioButton) findViewById(R.id.rbSoundTrollish);
		RadioButton rbSoundSimpsons = (RadioButton) findViewById(R.id.rbSoundSimpsons);
		RadioButton rbSoundNormal = (RadioButton) findViewById(R.id.rbSoundNormal);

		rbSoundTrollish.setOnClickListener(this);
		rbSoundSimpsons.setOnClickListener(this);
		rbSoundNormal.setOnClickListener(this);

		switch (sound_mode) {
		case TROLLISH:
			rbSoundTrollish.setChecked(true);
			break;
		case SIMPSONS:
			rbSoundSimpsons.setChecked(true);
			break;
		case NORMAL:
			rbSoundNormal.setChecked(true);
			break;
		}
	}

	public void onClick(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();
		editor = preferences.edit();

		// Check which radio button was clicked
		switch (view.getId()) {
		case R.id.rbSoundTrollish:
			if (checked)
				sound_mode = SoundMode.TROLLISH;
			break;
		case R.id.rbSoundSimpsons:
			if (checked)
				sound_mode = SoundMode.SIMPSONS;
			break;
		case R.id.rbSoundNormal:
			if (checked)
				sound_mode = SoundMode.NORMAL;
			break;
		case R.id.rbSoundOff:
			if (checked)
				is_sound_on = false;
			break;
		case R.id.rbSoundOn:
			if (checked)
				is_sound_on = true;
			break;
		}
	}

	@Override
	public void onBackPressed() {
		if (editor != null) {
			editor.putBoolean("is_sound_on", is_sound_on);
			editor.putInt("sound_mode", sound_mode.ordinal());
			editor.commit();
		}
		super.onBackPressed();
	}
}
