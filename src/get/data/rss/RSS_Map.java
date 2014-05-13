package get.data.rss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RSS_Map {

	// Dado el String id_museo, obtienes la lista de todos los eventos que hay en ese museo 
	private HashMap<String, ArrayList<Item>> _museo_all_events = null;

	// Dado el String id_museo, se obtiene la lista de todos los eventos que hay en un museo.
	//		Importante: Cada evento es un HashMap
	private HashMap<String, ArrayList<HashMap<String, String>>> _museo_all_hashed_events = null;

	public RSS_Map(ArrayList<Channel> array) {
		setMuseoAllEvents(null);
		setMuseoAllHashedEvents(null);
		if (array != null)
			buildRSSMap(array);
	}

	private void setMuseoAllEvents(HashMap<String, ArrayList<Item>> museoAllEvents) {
		_museo_all_events = museoAllEvents;
	}

	private void setMuseoAllHashedEvents(HashMap<String, ArrayList<HashMap<String, String>>> museoAllHashedEvents) {
		_museo_all_hashed_events = museoAllHashedEvents;
	}

	private void buildRSSMap(ArrayList<Channel> array) {

		_museo_all_events = new HashMap<String, ArrayList<Item>>();
		_museo_all_hashed_events = new HashMap<String, ArrayList<HashMap<String, String>>>();

		// Lista con todos los eventos (tomamos los eventos que estan en el canal 0)
		ArrayList<Item> eventos = array.get(0).getItems();

		// Set con los museos que tienen eventos
		Set<String> museos = new HashSet<String>();
		for (Iterator<Item> iterador = eventos.listIterator(); iterador.hasNext(); ) {
			Item evento = iterador.next();
			museos.add(evento.getLugar());
		}

		// Inicializar los dos HashMap para los eventos que tienen museos
		for (Iterator<String> iterador = museos.iterator(); iterador.hasNext(); ) {
			String museo = iterador.next();

			ArrayList<Item> aux1 = new ArrayList<Item>();
			_museo_all_events.put(museo, aux1);

			ArrayList<HashMap<String, String>> aux2 = new ArrayList<HashMap<String, String>>();
			_museo_all_hashed_events.put(museo, aux2);
		}

		// Recorrer todos los eventos e ir clasificandolos
		for (Iterator<Item> iterador = eventos.listIterator(); iterador.hasNext(); ) {
			Item evento = iterador.next();

			ArrayList<Item> aux1 = _museo_all_events.get(evento.getLugar());
			aux1.add(evento);
			_museo_all_events.put(evento.getLugar(), aux1);

			ArrayList<HashMap<String, String>> aux2 = _museo_all_hashed_events.get(evento.getLugar());
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("Title", evento.getTitle());
			map.put("Description", evento.getDescription());
			map.put("Link", evento.getLink());
			map.put("Lugar", evento.getLugar());
			map.put("TipoEvento", evento.getEvento()); // Agenda o Noticia
			map.put("FechaFin", evento.getFecha_fin());
			map.put("Fecha", evento.getFecha());
			map.put("Imagen", evento.getImagen());
			map.put("PubDate", evento.getPubDate());
			map.put("Author", evento.getAuthor());
			map.put("Guid", evento.getGuid());
			aux2.add(map);
			_museo_all_hashed_events.put(evento.getLugar(), aux2);
		}

	}


	// Devuelve todos los eventos que hay en un museo, dado el id del museo
		// Si para el id del museo solicitado no hay eventos devuelve una lista vacia
	public ArrayList<Item> getListaCompletaEventosMuseo(String id_museo) {
		if (_museo_all_events != null) {
			ArrayList<Item> list = _museo_all_events.get(id_museo);
			if (list != null)
				return list;							// En el museo id_museo hay eventos. Devolver: la lista con los eventos que hay en el museo  
			else
				return new ArrayList<Item>();	// En el museo id_museo no hay eventos. Devolver: una lista vacia sin eventos
		}
		else
			return null;	// Problemas con la inicializacion
	}

	// Devuelve el numero de eventos que hay en el museo, dado el id del museo
	public int getNumeroEventosMuseo(String id_museo) {
		if (_museo_all_events != null) {
			ArrayList<Item> list = _museo_all_events.get(id_museo);
			if (list != null)
				return list.size(); // En el museo id_museo hay eventos. Devolver: numero de eventos en el museo id_museo
			else
				return 0;    // En el museo id_museo no hay eventos. Devolver: 0
		}
		else
			return -1;	// Problemas con la inicializacion
	}


	// Devuelve el campo "field" del evento "num_evento" que hay en el museo "id_museo"
	public String getEventField(String id_museo, String num_evento, String field) {
		if (_museo_all_hashed_events != null) {
			ArrayList<HashMap<String, String>> list = _museo_all_hashed_events.get(id_museo);
			if (list != null) {
				int n_evento = Integer.parseInt(num_evento);
				if (n_evento < list.size()) {
					HashMap<String, String> map = list.get(n_evento);
					String value = map.get(field);
					if (value != null)
						return value;				// Devolver: Campo field del evento num_evento que hay en el museo id_museo
					else
						return "FieldNotExist_Error";	// El campo field no existe en los eventos
				}
				else
					return "EventNotExist_Error";	// El numero de evento especificado no existe en el museo
			}
			else
				return "NoEvents_Error";	// En el museo id_museo no hay eventos
		}
		else
			return "Init_Error";	// Problemas con la inicializacion
	}

}