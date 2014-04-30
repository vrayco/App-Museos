package org.museosdetenerife;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class EventosActivity extends Activity {
	
	String id_museo;

	static final String[] MUSEOS = new String[] { "HISTORIA", "COSMOS", "HOMBRE", "CEDOCAM" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventos);
		
		Intent intent = getIntent();
		id_museo = intent.getStringExtra("ID");
		
		final ActionBar actionBar = getActionBar();

		Titulos titulo = new Titulos();
		actionBar.setTitle(titulo.getTitulo(id_museo));
		if(titulo.containsKeySubtitulo(id_museo))
			actionBar.setSubtitle(titulo.getSubtitulo(id_museo));
		
		View separator = (View) findViewById(R.id.separator1);

		if(id_museo.equals("HISTORIA1") || id_museo.equals("HISTORIA2") || id_museo.equals("HISTORIA3"))
			separator.setBackgroundColor(0XFFB50937);
		else if(id_museo.equals("COSMOS"))
			separator.setBackgroundColor(0XFFFFAB18);
		else if(id_museo.equals("HOMBRE"))
			separator.setBackgroundColor(0XFF5D9732);
		else if(id_museo.equals("CEDOCAM"))
			separator.setBackgroundColor(0XFF1D5FAD);

		 
	}
	
	public void selectFrag(View view) {

		 Fragment fr;		 
			
		 fr = new EventosListaFragment();		
		 
		 android.app.FragmentManager fm = getFragmentManager();
	     FragmentTransaction fragmentTransaction = fm.beginTransaction();
	     fragmentTransaction.replace(R.id.fragment_eventos, fr);
	     fragmentTransaction.commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.eventos, menu);
		return true;
	}
	
	public void InfoActivity(View view) {
	    
		Intent intent;
		
		intent = new Intent(this, MuseoActivity.class);
		intent.putExtra("ID", id_museo);
			
		startActivity(intent);

	}
	
	public void EventosActivity(View view) {
		Intent intent;
		
		intent = new Intent(this, EventosActivity.class);
		intent.putExtra("ID", id_museo);
			
		startActivity(intent);
	}
	
	public void IrActivity(View view) {
		Intent intent;
		
		intent = new Intent(this, IrActivity.class);
		intent.putExtra("ID", id_museo);
			
		startActivity(intent);
	}

}
