package org.museosdetenerife;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class HistoriaListActivity extends ListActivity {
	
static final String[] MUSEOS_HISTORIA = new String[] { "5", "7", "12" };
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		final ActionBar ab = getActionBar();
		ab.setTitle("Museos de Tenerife");
		ab.setSubtitle("Historia y Antropolog’a");
		
		setListAdapter(new MuseoHistoriaArrayAdapter(this, MUSEOS_HISTORIA));
				
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
//		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
		
		Intent intent;
		intent = new Intent(this, MuseoActivity.class);
		intent.putExtra("ID", selectedValue);

		startActivity(intent);
 
	}	

}
