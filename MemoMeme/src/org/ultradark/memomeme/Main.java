package org.ultradark.memomeme;

import ultradark.memomeme.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);     
        
        if (getIntent().getExtras() != null && getIntent().getBooleanExtra("isExit", false)) {
			finish();
		}

        Button b = (Button) findViewById(R.id.buttonGo);
        b.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent go = new Intent(v.getContext(), org.ultradark.memomeme.Game.class);
				startActivity(go);				
			}
		});
    }
}