package get.data.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ImportCSVMuseos {

	private ArrayList<Museo> _lista_museos;

	public ImportCSVMuseos(File csvFile) throws IOException {
		setListaMuseos(null);
		if (csvFile.exists())
			import_data_csv(csvFile);
	}

	public ArrayList<Museo> getListaMuseos() {
		return _lista_museos;
	}

	private void setListaMuseos(ArrayList<Museo> listaMuseos) {
		_lista_museos = listaMuseos;
	}

	private void import_data_csv(File csvFile) throws IOException {
		CSVMuseosParser csvParser = new CSVMuseosParser();
		InputStream csvStream = new FileInputStream(csvFile);
		ArrayList<Museo> listaMuseos = csvParser.parse(csvStream);
		csvStream.close();
		setListaMuseos(listaMuseos);
	}

	public String csvDataToString(String separator) {
		if (_lista_museos != null) {
			String csvData = "Museos contenidos en el fichero CSV:" + separator + separator;
			int i = 0;
			for (Iterator<Museo> iterador = getListaMuseos().listIterator(); iterador.hasNext(); i++) {
				if (iterador.hasNext())
					csvData += "Museo " + i + ":" + separator + iterador.next().museoToString(separator) + separator + separator;
				else
					csvData += "Museo " + i + ":" + separator + iterador.next().museoToString(separator);
			}
			return csvData;
		}
		else
			return "Error";
	}

}