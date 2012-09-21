package am.tir.games.memomemefree.activities;

import static am.tir.games.memomemefree.utils.MemeSettings.sound_mode;
import am.tir.games.memomemefree.utils.SoundMode;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

/**
 * @author Artak.Gevorgyan
 *
 */
public class Settings extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		initSoundSettings();
	}

	private void initSoundSettings() {
		RadioButton rbSoundTrollish = (RadioButton) findViewById(R.id.rbSoundTrollish);
		RadioButton rbSoundSimpsons = (RadioButton) findViewById(R.id.rbSoundSimpsons);
		RadioButton rbSoundNormal = (RadioButton) findViewById(R.id.rbSoundNormal);
		RadioButton rbSoundOff = (RadioButton) findViewById(R.id.rbSoundOff);

		rbSoundTrollish.setOnClickListener(this);
		rbSoundSimpsons.setOnClickListener(this);
		rbSoundNormal.setOnClickListener(this);
		rbSoundOff.setOnClickListener(this);

		if (sound_mode == null) {
			rbSoundOff.setChecked(true);
			return;
		}

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
		case OFF:
			rbSoundOff.setChecked(true);
			break;
		}
	}

	public void onClick(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

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
				sound_mode = SoundMode.OFF;
			break;
		}
	}
}
