package com.cavila.kidsworldanimals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Play extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);			
		
	}
	public void spellAnimal(View view){
		//Toast.makeText(this, "Spelling...", Toast.LENGTH_SHORT).show();
		SpeechTools spTools = new SpeechTools();
		//spTools.speechTest();
		spTools.speakOut("test");
		
		//startActivity(new Intent(Play.this, Play.class));
		//myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
	}
}
