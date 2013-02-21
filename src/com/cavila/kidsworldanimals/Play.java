package com.cavila.kidsworldanimals;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Play extends Activity implements OnInitListener {
//create TextToSpeech native object
private TextToSpeech tts;
private Button btnSpell;
private EditText textboxSpell;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
		
		// retrieve animal selected
        Bundle bundle = getIntent().getExtras();

        //if(bundle.getInt("animal_selected")!= null)
        //{
        	Log.e("animal selected was: ", Integer.toString(bundle.getInt("animal_selected")));
        	//Log.e("animal selected was: ", bundle.getString("animal_selected"));
        	//R.array.animal1;
        	
        //}
        	//String currentAnimal = getResources().getIdentifier("animal1", "array","com.cavila.kidsworldanimals");
        	//Log.e("array resource is ", Integer.toString(bundle.getInt("animal_selected")));
        	Toast.makeText(Play.this, getResources().getIdentifier("app_name", "string", 
        			getPackageName()), Toast.LENGTH_LONG).show();
        	
        	
		//Initialize the tts object
		tts = new TextToSpeech(this, this);
		//Refer 'Speak' button
		btnSpell = (Button) findViewById(R.id.btnSpell);
		//Refer 'Text' control
		textboxSpell = (EditText) findViewById(R.id.textboxSpell);
		//Handle onClick event for button 'Speak'
		btnSpell.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View arg0) {
			speakOut();
		}

});		
		
	}
	
	
	// TextToSpeech related methods based on http://android.programmerguru.com/android-text-to-speech-example/
	public void onInit(int status) {
        //TTS is successfully initialized
        if (status == TextToSpeech.SUCCESS) {
                       //Setting speech language
            int result = tts.setLanguage(Locale.US);
           //If your device doesn't support language you set above
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            	//Cook simple toast message with message
                Toast.makeText(this, "Language not supported", Toast.LENGTH_LONG).show();
                Log.e("TTS", "Language is not supported");
            } 
         //Enable the button - It was disabled in play.xml (Go back and Check it)
                        else {
                        	btnSpell.setEnabled(true);
            }
         //TTS is not initialized properly
        } else {
                    Toast.makeText(this, "TTS Initilization Failed", Toast.LENGTH_LONG).show();
            Log.e("TTS", "Initilization Failed");
        }
    }	

	private void speakOut() {
       //Get the text typed
       String tmptext = textboxSpell.getText().toString();
       String text = "";
       //divide characters to spell
       for (int i = 0;i < tmptext.length(); i++){
    	    text += tmptext.charAt(i) + " ";
    	}
       
       if (text.length() == 0) {
           tts.speak("Hey, please type an animal name", TextToSpeech.QUEUE_FLUSH, null);
       } else {
           tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
       }

	}
	
	public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }	
	

}
