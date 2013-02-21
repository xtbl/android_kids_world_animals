package com.cavila.kidsworldanimals;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import java.util.Locale;

public class SpeechTools /*implements OnInitListener*/ {

	 
	
//	public void onInit(int status) {
//        // TODO Auto-generated method stub
//          //TTS is successfully initialized
//        if (status == TextToSpeech.SUCCESS) {
//                       //Setting speech language
//            int result = tts.setLanguage(Locale.US);
//           //If your device doesn't support language you set above
//            if (result == TextToSpeech.LANG_MISSING_DATA
//                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                           //Cook simple toast message with message
//                //Toast.makeText(this, "Language not supported", Toast.LENGTH_LONG).show();
//                Log.e("TTS", "Language is not supported");
//            }
//                 //Enable the button - It was disabled in main.xml (Go back and Check it)
//                        else {
//               // btnSpeak.setEnabled(true);
//            }
//            //TTS is not initialized properly
//        } else {
//            //Toast.makeText(this, "TTS Initilization Failed", Toast.LENGTH_LONG).show();
//            Log.e("TTS", "Initilization Failed");
//        }
//    }
//	
//	public void speakOut(String text) {
//		
//		TextToSpeech tts;
//		//Initialize the tts object
//		tts = new TextToSpeech(context, listener)
//		//Get the text typed
//       //String text = txtText.getText().toString();
//        //If no text is typed, tts will read out 'You haven't typed text'
//        //else it reads out the text you typed
//       if (text.length() == 0) {
//           tts.speak("You haven't typed text", TextToSpeech.QUEUE_FLUSH, null);
//       } else {
//           tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//       }
//
//   }
//	
//	
//	public void onDestroy() {
//        // Don't forget to shutdown!
//        if (tts != null) {
//            tts.stop();
//            tts.shutdown();
//        }
//        this.onDestroy();
//    }	
//	
	
	public void speechTest(){
		//Toast.makeText(this, "Spelling...", Toast.LENGTH_SHORT).show();
		Log.e("sp class", "speechTest");
	}
	

	
	
	
}
