package get.data.csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CSV_Map {

	private HashMap<String, HashMap<String,String>> _csv_map;

	public CSV_Map(ArrayList<Museo> array) {
		setMapMuseos(null);
		if (array != null)
			buildCSVMap(array);
	}

	private void setMapMuseos(HashMap<String, HashMap<String,String>> csvMap) {
		_csv_map = csvMap;
	}

	private void buildCSVMap(ArrayList<Museo> array) {
		_csv_map = new HashMap<String, HashMap<String,String>>();
		for (Iterator<Museo> iterador = array.listIterator(); iterador.hasNext(); ) {
			Museo m = iterador.next();
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("Id", m.getId());
			map.put("IdPadre", m.getId_padre());
			map.put("Nombre", m.getNombre());
			map.put("Domicilio", m.getDomicilio());
			map.put("CP", m.getCp());
			map.put("Localidad", m.getLocalidad());
			map.put("Provincia", m.getProvincia());
			map.put("Telefono", m.getTelefono());
			map.put("URL", m.getUrl());
			map.put("Latitud", m.getLatitud());
			map.put("Longitud", m.getLongitud());
			_csv_map.put(m.getId(), map);
		}	
	}

	// Devuelve el campo "field" del museo "id_museo"
	public String getMuseoField(String id_museo, String field) {
		if (_csv_map != null) {
			HashMap<String,String> map = _csv_map.get(id_museo);
			if  (map != null)
				if (map.get(field) != null)
					return map.get(field);						// Devolver: Campo field del museo id_museo
				else
					return "FieldNotExist_Error";			// El campo field no existe en los museos
			else
				return "MuseumNotExist_Error";		// El museo id_museo no existe
		}
		else
			return "Init_Error";	// Problemas con la inicializacion

	}

}