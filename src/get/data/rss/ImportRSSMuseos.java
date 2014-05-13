package get.data.rss;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.xmlpull.v1.XmlPullParserException;

public class ImportRSSMuseos {

	private ArrayList<Channel> _listaEventos;

	public ImportRSSMuseos(File rssFile) throws XmlPullParserException, IOException {
		setListaEventos(null);
		if (rssFile.exists())
			import_data_rss(rssFile);
	}

	public ArrayList<Channel> getListaEventos() {
		return _listaEventos;
	}

	private void setListaEventos(ArrayList<Channel> listaEventos) {
		_listaEventos = listaEventos;
	}

	private void import_data_rss(File rssFile) throws XmlPullParserException, IOException {
		RSSEventsParser rssParser = new RSSEventsParser();
	    InputStream rssStream = new FileInputStream(rssFile);
		ArrayList<Channel> listaEventos = rssParser.parse(rssStream);
		rssStream.close();
		setListaEventos(listaEventos);
	}

	public String rssDataToString(String separator) {
		if (_listaEventos != null) {
			String rssData = "Canales de eventos contenidos en el fichero RSS:" + separator + separator;
			int i = 0;
			for (Iterator<Channel> iterador = getListaEventos().listIterator(); iterador.hasNext(); i++) {
				if (iterador.hasNext())
					rssData += "Eventos del Canal " + i + ":" + separator + iterador.next().channelToString(separator) + separator + separator;
				else
					rssData += "Eventos del Canal " + i + ":" + separator + iterador.next().channelToString(separator);
			}
			return rssData;
		}
		else
			return "Error";
	}

}