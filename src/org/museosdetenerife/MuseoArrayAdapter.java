package org.museosdetenerife;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class MuseoArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
 
	public MuseoArrayAdapter(Context context, String[] values) {
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
  
		if (s.equals("11")) {
			imageView.setImageResource(R.drawable.foto_mha);
			imageView2.setImageResource(R.drawable.logotipo_mha);
		} else if (s.equals("6")) {
			imageView.setImageResource(R.drawable.foto_mcc);
			imageView2.setImageResource(R.drawable.logotipo_mcc);
		} else if (s.equals("1")) {
			imageView.setImageResource(R.drawable.foto_mnh);
			imageView2.setImageResource(R.drawable.logotipo_mnh);
		} else if (s.equals("9")){
			imageView.setImageResource(R.drawable.foto_cedocam);
			imageView2.setImageResource(R.drawable.logotipo_cedocam);
		}
 
		return rowView;
	}
}