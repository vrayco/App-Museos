package org.museosdetenerife.app;

import java.util.Vector;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class AdaptadorMuseo extends BaseAdapter {
	
	private final Activity _actividad;
    private final Vector<Integer> _logotipos;
    private final Vector<Integer> _fotos;

    public AdaptadorMuseo(Activity actividad, Vector<Integer> fotos, Vector<Integer> logotipos) {
        super();
        this._actividad = actividad;
        this._logotipos = logotipos;
        this._fotos = fotos;
  }
	@Override
	public int getCount() {
		return this._logotipos.size();
	}

	@Override
	public Object getItem(int position) {
		return this._logotipos.elementAt(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = this._actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_museo, null, true);
        
        ImageView imageView_foto=(ImageView)view.findViewById(R.id.foto);
        imageView_foto.setImageResource(this._fotos.elementAt(position));
        
        ImageView imageView_logotipo =(ImageView)view.findViewById(R.id.logo_museos);
        imageView_logotipo.setImageResource(this._logotipos.elementAt(position));
        
        return view;
		
	}

}
