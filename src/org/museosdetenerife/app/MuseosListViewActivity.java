package org.museosdetenerife.app;

import java.util.Vector;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class MuseosListViewActivity extends ListActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
//		Vector<String> nombre_museos = new Vector<String>();
//		nombre_museos.add("Naturaleza y El Hombre");
//		nombre_museos.add("Ciencia y El Cosmos");
//		nombre_museos.add("Historia");
//		nombre_museos.add("Documentaci—n");
		
		Vector<Integer> fotos_museos = new Vector<Integer>();
		Integer resID = getResources().getIdentifier("m_mnh" , "drawable", getPackageName());
		fotos_museos.add(resID);
		resID = getResources().getIdentifier("m_mcc" , "drawable", getPackageName());
		fotos_museos.add(resID);
		resID = getResources().getIdentifier("m_mha" , "drawable", getPackageName());
		fotos_museos.add(resID);
		resID = getResources().getIdentifier("m_cedocam" , "drawable", getPackageName());
		fotos_museos.add(resID);

		Vector<Integer> logotipos_museos = new Vector<Integer>();
		resID = getResources().getIdentifier("logotipo_mnh" , "drawable", getPackageName());
		logotipos_museos.add(resID);
		resID = getResources().getIdentifier("logotipo_mcc" , "drawable", getPackageName());
		logotipos_museos.add(resID);
		resID = getResources().getIdentifier("logotipo_mha" , "drawable", getPackageName());
		logotipos_museos.add(resID);
		resID = getResources().getIdentifier("logotipo_cedocam" , "drawable", getPackageName());
		logotipos_museos.add(resID);

		setListAdapter(
				new AdaptadorMuseo(this, fotos_museos, logotipos_museos)
				);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view, menu);
		return true;
	}

	@Override 
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		super.onListItemClick(listView, view, position, id);
		Object o = getListAdapter().getItem(position);
		startActivity(new Intent(MuseosListViewActivity.this,MuseoActivity.class));
	}
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			// This ID represents the Home or Up button. In the case of this
//			// activity, the Up button is shown. Use NavUtils to allow users
//			// to navigate up one level in the application structure. For
//			// more details, see the Navigation pattern on Android Design:
//			//
//			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
//			//
//			NavUtils.navigateUpFromSameTask(this);
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

}
