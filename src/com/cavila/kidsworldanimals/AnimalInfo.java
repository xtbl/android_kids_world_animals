package com.cavila.kidsworldanimals;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalInfo extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animal);

		setListAdapter(new MyAdapter(this,
				android.R.layout.simple_list_item_1, R.id.textView1,
//TODO: get dynamic resource here animalx
				getResources().getStringArray(R.array.animal1)));
	}

	public void goBackHome(View v){
		startActivity(new Intent(AnimalInfo.this, MainActivity.class));
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
//TODO: get dynamic resource here animalx
			String[] items = getResources().getStringArray(R.array.animal1);

			ImageView iv = (ImageView) row.findViewById(R.id.imageView1);
			TextView tv = (TextView) row.findViewById(R.id.textView1);

			tv.setText(items[position]);
//TODO: get dynamic resource here animalx
		
			if (position == 0){
				iv.setImageResource(R.drawable.animal_ico);
			}
			else if ( position == 1 ){
				iv.setImageResource(R.drawable.animal_ico);
			}
			else if ( position == 2 ) {
				iv.setImageResource(R.drawable.animal_ico);
			}
			else if ( position == 3 ) {
				iv.setImageResource(R.drawable.animal_ico);
			}
			else if ( position == 4 ){
				iv.setImageResource(R.drawable.animal_ico);
			}
			else if ( position == 5 ){
				iv.setImageResource(R.drawable.animal_ico);
			}
			else if ( position == 6 ){
				iv.setImageResource(R.drawable.animal_ico);
			}

			return row;
		}

	}

}
