package org.museosdetenerife;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class MuseoHistoriaArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
 
	public MuseoHistoriaArrayAdapter(Context context, String[] values) {
		super(context, R.layout.lista_museo, values);
		this.context = context;
		this.values = values;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.lista_museo, parent, false);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.foto);
		ImageView imageView2 = (ImageView) rowView.findViewById(R.id.logo);
 
		// Change icon based on name
		String s = values[position];
  
		if (s.equals("5")) {
			imageView.setImageResource(R.drawable.foto_mha_lercaro);
			imageView2.setImageResource(R.drawable.logotipo_mha_casa_lecaro);
		} else if (s.equals("7")) {
			imageView.setImageResource(R.drawable.foto_mha_carta);
			imageView2.setImageResource(R.drawable.logotipo_mha_casa_de_carta);
		} else if (s.equals("12")) {
			imageView.setImageResource(R.drawable.foto_mha_castillo);
			imageView2.setImageResource(R.drawable.logotipo_mha_castillo_san_cristobal);
		}
 
		return rowView;
	}
}