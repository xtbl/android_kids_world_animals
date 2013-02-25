package com.cavila.kidsworldanimals;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalInfo extends ListActivity {

	private String[] animArray;	
	private String currentAnimal;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animal);

		/**
		 *  get animal selected from previous activity
		 */
        Bundle bundle = getIntent().getExtras();
        currentAnimal = bundle.getString("animal_info");
        Log.e("INFO_ANIMAL", currentAnimal);		
		
		setListAdapter(new MyAdapter(this,
				android.R.layout.simple_list_item_1, R.id.textView1,

				getResources().getStringArray( getArrayResId(currentAnimal) )));
	}

	public void goBackHome(View v){
		startActivity(new Intent(AnimalInfo.this, MainActivity.class));
	}

    /**
     * get id for an specific Array resource using the name as parameter
     */
    public int getArrayResId(String resName){
    	int resId;
    	resId = getResources().getIdentifier(resName, "array", getPackageName());
    	return resId;
    }	

    /**
     * get id for an specific drawable resource using the name as parameter
     */
    public int getDrawableResId(String resName){
    	int resId;
    	resId = getResources().getIdentifier(resName, "drawable", getPackageName());
    	return resId;
    }	
	    
    
	private class MyAdapter extends ArrayAdapter<String> {
		public MyAdapter(Context context, int resource, int textViewResourceId,
				String[] strings) {
			super(context, resource, textViewResourceId, strings);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(R.layout.list_item, parent, false);
			String[] items = getResources().getStringArray(getArrayResId(currentAnimal));

			ImageView iv = (ImageView) row.findViewById(R.id.imageView1);
			TextView tv = (TextView) row.findViewById(R.id.textView1);

			tv.setText(items[position]);
		
			if (position == 0){
				iv.setImageResource(getDrawableResId(currentAnimal));
			}
			else if ( position == 1 ){
				iv.setImageResource(getDrawableResId(currentAnimal));
			}
			else if ( position == 2 ) {
				iv.setImageResource(getDrawableResId(currentAnimal));
			}
			else if ( position == 3 ) {
				iv.setImageResource(getDrawableResId(currentAnimal));
			}
			else if ( position == 4 ){
				iv.setImageResource(getDrawableResId(currentAnimal));
			}
			else if ( position == 5 ){
				iv.setImageResource(getDrawableResId(currentAnimal));
			}
			else if ( position == 6 ){
				iv.setImageResource(getDrawableResId(currentAnimal));
			}

			return row;
		}

	}
	
	public void goBack(View view) {
		finish();
	}

}
