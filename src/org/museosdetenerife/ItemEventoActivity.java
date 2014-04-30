package org.museosdetenerife;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ItemEventoActivity extends Activity {

	String id_museo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_evento);
		
		
		Intent intent = getIntent();
		id_museo = intent.getStringExtra("ID");
		String titulo = intent.getStringExtra("titulo");
		String subtitulo = intent.getStringExtra("subtitulo");
		String contenido = intent.getStringExtra("contenido");
		
		TextView texto_contenido = (TextView) findViewById(R.id.texto_evento);
		
		texto_contenido.setText(Html.fromHtml(contenido));
		
		
		
		final ActionBar actionBar = getActionBar();

		actionBar.setTitle(titulo);
		actionBar.setSubtitle(subtitulo);
		
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.item_evento, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {   
		// El boton de volver al listado de eventos
		onBackPressed();
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
