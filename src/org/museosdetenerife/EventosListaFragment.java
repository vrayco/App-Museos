package org.museosdetenerife;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventosListaFragment extends ListFragment {
	
	static String id_museo = null;
	
	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
		
	    String[] values = new String[] { "Evento 1", "Evento 2", "Evento 3", "Evento 4" };

	    setListAdapter(new EventosArrayAdapter(getActivity(), values));
	  }

	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data
		  //Log.d("item", Integer.toString(position));
		  Intent intent = new Intent(getActivity(), ItemEventoActivity.class);
		  String selectedValue = (String) getListAdapter().getItem(position);
		  intent.putExtra("ID_evento", selectedValue);
		  intent.putExtra("titulo", "TITULO EVENTO");
		  intent.putExtra("subtitulo", "SUBTITULO EVENTO");
		  intent.putExtra("contenido", "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris pellentesque viverra nisi, id varius nisl dapibus eget. Aenean ac nisi quis est tincidunt euismod et at magna. Fusce nunc leo, mollis lobortis tincidunt nec, varius bibendum diam. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam hendrerit rhoncus erat, lacinia vulputate neque blandit quis. Aenean ultrices viverra risus eget hendrerit. Morbi libero odio, ullamcorper sit amet hendrerit id, consectetur eu justo.</p>");
		  startActivity(intent);
	  }
	  
	  public static void setIdMuseo(String _id_museo)
	  {
		  id_museo = _id_museo;
	  }
}