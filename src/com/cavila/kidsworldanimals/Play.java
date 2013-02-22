package com.cavila.kidsworldanimals;

import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Play extends Activity implements OnInitListener {
//create TextToSpeech native object
private TextToSpeech tts;
private Button btnSpell;
private EditText textboxSpell;
private Button btnDialog;
private Button btnLearnMore;	
final Context context = this;
public String currentAnimal = "cat";
private String animalNameAnswer = "";
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
		
    	// dialog button
    	btnDialog = (Button) findViewById(R.id.btnDialog);
    	// add dialog button listener
    	btnDialog.setOnClickListener(new View.OnClickListener() {
		  public void onClick(View arg0) {

			// custom dialog
			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.dialog_1);
			dialog.setTitle("Your score");

			TextView text = (TextView) dialog.findViewById(R.id.textDialog);
			
			//check there is an answer and if that answer is right
			animalNameAnswer = textboxSpell.getText().toString();
			if (animalNameAnswer != null && animalNameAnswer.length() != 0){
				
				if( answerIsCorrect(currentAnimal, animalNameAnswer) ){
					text.setText("Congratulations!");
				}else{
					text.setText("Please try again");
				}
			}else{
				text.setText("Hey, you need to write the animal name");
			}
				
			

			
			//			ImageView image = (ImageView) dialog.findViewById(R.id.image);
//			image.setImageResource(R.drawable.ic_launcher);

			Button dialogButton = (Button) dialog.findViewById(R.id.btnDialogOk);
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

			dialog.show();
		  }
		});
    	
    	// Learn More
    	btnLearnMore = (Button) findViewById(R.id.btnLearnMore);
    	// start Learn More activity
    	btnLearnMore.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			startActivity(new Intent(Play.this, AnimalInfo.class));
    		}
    	});
    	
		
	}
	
	// check if answer is correct
	public boolean answerIsCorrect (String animal, String userAnswer){
		if (animal.equalsIgnoreCase(userAnswer)){
			return true;
		}else{
			return false;
		}
		
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
