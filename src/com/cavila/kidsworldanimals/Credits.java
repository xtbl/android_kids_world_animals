package com.cavila.kidsworldanimals;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Credits extends Activity {
		private Button goBack;
		
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.credits);
	    }
		public void goBack(View view) {
			finish();
		}
}

