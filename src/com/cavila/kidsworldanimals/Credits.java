package com.cavila.kidsworldanimals;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
		public void sendEmail(View view){
			Intent send = new Intent(Intent.ACTION_SENDTO);
			String uriText = "mailto:" + Uri.encode("correodecristobal@gmail.com") + 
			          "?subject=" + Uri.encode("Kids World Animals Jungle") + 
			          "&body=" + Uri.encode("Hi!...");
			Uri uri = Uri.parse(uriText);

			send.setData(uri);
			startActivity(Intent.createChooser(send, "Send mail..."));
		}
}

