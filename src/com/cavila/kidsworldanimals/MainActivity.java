package com.cavila.kidsworldanimals;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {
public String animalSelec = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	public void onRadioButtonClicked(View view) {

	    boolean checked = ((RadioButton) view).isChecked();
    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radioButton1:
	            if (checked)
	            	animalSelec = "animal1";
	            break;
	        case R.id.radioButton2:
	            if (checked)
	            	//Log.e("radio selected", "radioButton2");
	            	animalSelec = "animal2";
	            break;
	        case R.id.radioButton3:
	        	if (checked)
	        		animalSelec = "animal3";
	        	break;
	        case R.id.radioButton4:
	        	if (checked)
	        		animalSelec = "animal4";
	        	break;
	        case R.id.radioButton5:
	        	if (checked)
	        		animalSelec = "animal5";
	        	break;
	        case R.id.radioButton6:
	        	if (checked)
	        		animalSelec = "animal6";
	        	break;
	    }
	    
	    startGame(view);
	}
    public void startGame(View view){
    	Log.e("in startgame", "in startGame");
    	Intent i = new Intent(MainActivity.this, Play.class);
    	i.putExtra("animal_selected", animalSelec);
    	startActivity(i);
    }
    
}
