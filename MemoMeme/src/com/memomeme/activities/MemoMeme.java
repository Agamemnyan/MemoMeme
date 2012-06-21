package com.memomeme.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class MemoMeme extends Activity {

	long ms;
	private CountDownTimer cdt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		if (savedInstanceState != null) {
			ms = savedInstanceState.getLong("ms");
		} else {
			ms = 3000;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		cdt = new CountDownTimer(ms, 100) {

			public void onTick(long millisUntilFinished) {
				ms = millisUntilFinished;
			}

			public void onFinish() {
				new Intent(MemoMeme.this, Main.class);
				startActivity(new Intent(MemoMeme.this, Main.class));
				finish();
			}
		};

		cdt.start();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putLong("ms", ms);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onPause() {
		cdt.cancel();
		super.onPause();		
	}
}