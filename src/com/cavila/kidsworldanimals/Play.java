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
		
		//Initialize the tts object
		tts = new TextToSpeech(this, this);
		//Refer 'Speak' button
		btnSpell = (Button) findViewById(R.id.btnSpell);
		//Refer 'Text' control
		textboxSpell = (EditText) findViewById(R.id.textboxSpell);
		//Handle onClick event for button 'Speak'
		btnSpell.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View arg0) {
		         //Method yet to be defined 
		         speakOut();
		}

});		
		
	}
	
	
	// TextToSpeech related methods
	public void onInit(int status) {
        // TODO Auto-generated method stub
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
       String text = textboxSpell.getText().toString();
        //If no text is typed, tts will read out 'You haven't typed text'
        //else it reads out the text you typed
       if (text.length() == 0) {
           tts.speak("You haven't typed text", TextToSpeech.QUEUE_FLUSH, null);
       } else {
           tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
       }

	}
	
	public void onDestroy() {
        // Don't forget to shutdown!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }	
	

}
