package get.data.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVMuseosParser {

	public CSVMuseosParser() {
	}

	public ArrayList<Museo> parse(InputStream in) throws IOException {
		InputStreamReader csvStreamReader = new InputStreamReader(in);
		CSVReader csvReader = new CSVReader(csvStreamReader);
		return readCSV(csvReader);
	}

	private ArrayList<Museo> readCSV(CSVReader csvReader) throws IOException {

		ArrayList<Museo> listaMuseos = new ArrayList<Museo>();

		String[] line;

		// pasando la cabecera
		csvReader.readNext();

		while ((line = csvReader.readNext()) != null) {
			Museo m = new Museo();
			m.setId(fieldValueIfEmpty(line[0]));
			m.setId_padre(fieldValueIfEmpty(line[1]));
			m.setNombre(fieldValueIfEmpty(line[2]));
			m.setDomicilio(fieldValueIfEmpty(line[3]));
			m.setCp(fieldValueIfEmpty(line[4]));
			m.setLocalidad(fieldValueIfEmpty(line[5]));
			m.setProvincia(fieldValueIfEmpty(line[6]));
			m.setTelefono(fieldValueIfEmpty(line[7]));
			m.setUrl(fieldValueIfEmpty(line[8]));
			m.setLatitud(fieldValueIfEmpty(line[9]));
			m.setLongitud(fieldValueIfEmpty(line[10]));
			listaMuseos.add(m);
		}

		return listaMuseos;
	}

	private String fieldValueIfEmpty(String field) {
		if (field.isEmpty())
			return "-";
		else
			return field;
	}

}
