package am.tir.games.memomemefree.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class About extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);

		setMoreAppsButton();
	}

	private void setMoreAppsButton() {
		Button btnMoreApps = (Button) findViewById(R.id.btnMore);
		btnMoreApps.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("market://search?q=pub:Tir CS"));
				startActivity(intent);
			}
		});
	}
}