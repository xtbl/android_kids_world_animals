package com.cavila.kidsworldanimals;

import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Play extends Activity implements OnInitListener {

	private TextToSpeech tts;
	private Button btnSpell;
	private EditText textboxSpell;
	private Button btnDialog;
	private Button btnLearnMore;
	private Button btnVideo;
	VideoView vid;
	final Context context = this;
	private String currentAnimal = "";
	private String animalNameAnswer = "";
	private String[] animArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);

		/**
		 *  get animal selected from previous activity
		 */
        Bundle bundle = getIntent().getExtras();
        currentAnimal = bundle.getString("animal_selected");        
        getAnimalInfo(currentAnimal);
        Log.e("ANIMAL_NAME", getAnimalName());


// GET RESOURCES TEST        	
//        	Toast.makeText(Play.this, getResources().getIdentifier("app_name", "string", 
//        			getPackageName()), Toast.LENGTH_LONG).show();

	/**
	 * video setup
	 */
        vid = (VideoView) findViewById(R.id.videoView1);
        String uripath = "android.resource://" + getPackageName() + "/" + getVideoResId(currentAnimal);
        vid.setVideoURI(Uri.parse(uripath));
    	btnVideo = (Button) findViewById(R.id.btnVideo);
    	btnVideo.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			vid.setVisibility(View.VISIBLE);
    			vid.start();
    		}
    	});
        
		tts = new TextToSpeech(this, this);
		btnSpell = (Button) findViewById(R.id.btnSpell);
		textboxSpell = (EditText) findViewById(R.id.textboxSpell);
		btnSpell.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				speakOut();
			}	
		});		
		
	/**
	 *  get my score and dialog button
	 */
    	btnDialog = (Button) findViewById(R.id.btnDialog);
    	// add dialog button listener
    	btnDialog.setOnClickListener(new View.OnClickListener() {
		  public void onClick(View arg0) {

			// custom dialog
			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.dialog_1);
			dialog.setTitle("Your score");

			TextView text = (TextView) dialog.findViewById(R.id.textDialog);
			
			//check if there is an answer and if that answer is right
			animalNameAnswer = textboxSpell.getText().toString();
			if (animalNameAnswer != null && animalNameAnswer.length() != 0){
				
				if( answerIsCorrect(getAnimalName(), animalNameAnswer) ){
					text.setText("Congratulations!");
				}else{
					text.setText("Please try again");
				}
			}else{
				text.setText("Hey, you need to write the animal name");
			}
				

			//ImageView image = (ImageView) dialog.findViewById(R.id.image);
			//image.setImageResource(R.drawable.ic_launcher);

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
    	
	/**
	 * Learn More
	 */    	
    	btnLearnMore = (Button) findViewById(R.id.btnLearnMore);
    	// start Learn More activity
    	btnLearnMore.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    	    	Intent i = new Intent(Play.this, AnimalInfo.class);
    	    	i.putExtra("animal_info", currentAnimal);
    	    	startActivity(i);
    		}
    	});
    	
		
	}

    /**
     * get id for an specific Video resource using the name as parameter
     */
    public int getVideoResId(String resName){
    	int resId;
    	resId = getResources().getIdentifier(resName, "raw", getPackageName());
    	return resId;
    }	
	
    /**
     * get current animal selection and sets an array with animal info
     */
    public void getAnimalInfo(String selectedAnimal){
        Resources res = getResources(); 
        int idResource = getResources().getIdentifier(currentAnimal, "array", getPackageName());
        animArray = res.getStringArray(idResource);
        Log.e("1st LETTER", animArray[0]);
    }
    /**
     * get current animal name
     */
    public String getAnimalName(){
    	String name = "";
    	name = animArray[1];
    	return name;
    }
    
	// check if answer is correct
	public boolean answerIsCorrect (String animal, String userAnswer){
		if (animal.equalsIgnoreCase(userAnswer)){
			return true;
		}else{
			return false;
		}
	}
	
    
	/**
	 * TextToSpeech related methods based on http://android.programmerguru.com/android-text-to-speech-example/
	 */
	public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Language not supported", Toast.LENGTH_LONG).show();
                Log.e("TTS", "Language is not supported");
            } 
            else {
            	btnSpell.setEnabled(true);
            }
        } else {
            Toast.makeText(this, "TTS Initilization Failed", Toast.LENGTH_LONG).show();
            Log.e("TTS", "Initilization Failed");
        }
    }	

	private void speakOut() {
       //Get the text typed
       //String tmptext = textboxSpell.getText().toString();
	   String tmptext = getAnimalName();
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
