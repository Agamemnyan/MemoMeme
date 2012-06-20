package com.memomeme.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SelectLevel extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_level);

		initLevels();
	}

	private void initLevels() {

		// find xml views
		TextView lvl1 = (TextView) findViewById(R.id.textLV1);
		TextView lvl2 = (TextView) findViewById(R.id.textLV3);
		TextView lvl3 = (TextView) findViewById(R.id.textLV6);
		TextView lvl4 = (TextView) findViewById(R.id.textLV10);

		// set listeners
		lvl1.setOnClickListener(this);
		lvl2.setOnClickListener(this);
		lvl3.setOnClickListener(this);
		lvl4.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	public void onClick(View v) {
		Intent go = null;
		switch (v.getId()) {
		case R.id.textLV1:
			go = new Intent(this, Level01.class);
			break;
		case R.id.textLV3:
			go = new Intent(this, Level02.class);
			break;
		case R.id.textLV6:
			go = new Intent(this, Level03.class);
			break;
		case R.id.textLV10:
			go = new Intent(this, Level04.class);
			break;
		}
		startActivity(go);
	}
}
