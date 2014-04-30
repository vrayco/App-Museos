package org.museosdetenerife;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventosArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
 
	public EventosArrayAdapter(Context context, String[] values) {
		super(context, R.layout.lista_museo, values);
		this.context = context;
		this.values = values;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.lista_eventos, parent, false);
		TextView titulo = (TextView) rowView.findViewById(R.id.titulo);
		TextView subtitulo = (TextView) rowView.findViewById(R.id.subtitulo);
		ImageView foto = (ImageView) rowView.findViewById(R.id.foto);
		
		titulo.setText("TITULO DE EVENTO");
		subtitulo.setText("SUBTITULO DE EVENTO");
		foto.setImageResource(R.drawable.foto_mha);
 
		// Change icon based on name
		String s = values[position];
 
		return rowView;
	}
}