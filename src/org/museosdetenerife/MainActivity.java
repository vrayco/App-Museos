package org.museosdetenerife;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends ListActivity {
	
	//static final String[] MUSEOS = new String[] { "HISTORIA", "COSMOS", "HOMBRE", "CEDOCAM" };
	
	static final String[] MUSEOS = new String[] { "11", "6", "1", "9" };
	
	// Historia = 8
	// Cosmos = 6
	// Hombre = 1
	// Cedecam = 9
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		setListAdapter(new MuseoArrayAdapter(this, MUSEOS));
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		
		System.err.println("Select value:" + selectedValue);
		
		Intent intent;
		if(selectedValue.equals("11")){
			intent = new Intent(this, HistoriaListActivity.class);
		} else {
			intent = new Intent(this, MuseoActivity.class);
			intent.putExtra("ID", selectedValue);
		}
		
		
		startActivity(intent);
 
	}	

}
