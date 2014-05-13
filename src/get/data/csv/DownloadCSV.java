package get.data.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.widget.Toast;

public class DownloadCSV {

	private URL _url;
	private File _csv_file;
	private File _csv_file_date;
	private boolean _isInternetConnectionActive;

	public DownloadCSV(Context ctx) throws IOException, ParseException {
		_url = new URL("http://ckan.opendatacanarias.es/storage/f/2014-01-28T123441/Museos.csv");
		_csv_file = new File(ctx.getFilesDir().getAbsolutePath() + "/Museos.csv");
		_csv_file_date = new File(ctx.getFilesDir().getAbsolutePath() + "/Museos_date.txt");
		_isInternetConnectionActive = false;
		executeDownloadCSV(ctx);
	}

	/*
	 *
	 * Funcion que establece la logica de actualizacion
	 *
	 */
	private void executeDownloadCSV(Context ctx) throws IOException, ParseException {
		_isInternetConnectionActive = isInternetActive(ctx);	// testear si hay conexion a internet
		if (!_csv_file.exists())			// El fichero no existe
			if (_isInternetConnectionActive) {		// El fichero no existe y hay conexion a Internet --> Descargar el fichero
				networkDownload(ctx);
				Toast.makeText(ctx, "App-Museos no tenia el fichero, descarga del fichero desde internet", Toast.LENGTH_LONG).show();
			}
			else									// El fichero no existe y no hay conexion a Internet --> Mensaje de error (Muy Graveee)
				Toast.makeText(ctx, "App-Museos no funcionara correctamente, revise su conexion a Internet", Toast.LENGTH_LONG).show();
		else								// El fichero existe
			if (csvFileNeedsUpdate())				// El fichero existe y hay que actualizar	
				if (_isInternetConnectionActive) {		// El fichero existe, hay que actualizar y hay conexion --> Actualizamos
					_csv_file.delete();			// elimino fichero csv
					_csv_file_date.delete();	// elimino el fichero que contiene la fecha
					networkDownload(ctx);		// vuelvo a descargarlo
					Toast.makeText(ctx, "App-Museos actualizo el fichero que tenia", Toast.LENGTH_LONG).show();
				}
				else									// El fichero existe, hay que actualizar y no hay conexion --> Mensaje de error (Menos Graveee)
					Toast.makeText(ctx, "App-Museos funcionara correctamente con datos desactualizados, revise su conexion a Internet", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(ctx, "App-Museos no necesitaba actualizar fichero", Toast.LENGTH_LONG).show();
	}

	/*
	 *
	 * Funcion que devuelve el File guardado
	 *
	 */
	public File getCSVFile() {
		return _csv_file;
	}

	/*
	 *
	 * Funcion que decide si el fichero CSV necesita actualizacion (actualizar cada 6 meses)
	 *
	 *		true ---> el fichero necesita actualizacion
	 *		false ---> el fichero no necesita actualizacion
	 *
	 */
	private boolean csvFileNeedsUpdate() throws IOException, ParseException {

		// Leer la fecha en la que se descargo por ultima vez el fichero csv.
		//		Esta fecha esta guardada en el fichero Museos_date.txt
		FileReader fr = new FileReader(_csv_file_date);
		BufferedReader br = new BufferedReader(fr);
		String fechaDown = br.readLine();
		br.close();

		// Formato para las fechas
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);

	    // Fecha de la ultima descarga
		Date fechaDescarga = dateFormat.parse(fechaDown);

		// Fecha actual
		Date fechaActual = new Date(); // coger la fecha y hora actuales del sistema

		// Diferencia en milisegundos entre la fecha actual y la fecha de la ultima descarga
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
		long diferencia = (fechaActual.getTime() - fechaDescarga.getTime()) / MILLSECS_PER_DAY;

		if (diferencia >= 180)
			return true;
		else
			return false;
	}

	/*
	 *
	 * Descargar el fichero CSV que contiene la informacion de los Museos desde la URL _url
	 * y guardarlo en almacenamiento interno (Museos.csv).
	 *
	 * Guardar en el almacenamiento interno otro fichero (Museos_date.txt) que indica
	 * la fecha y hora en la que descargamos el fichero.
	 *
	 */
	private void networkDownload(Context ctx) throws IOException {

		// Guardar la fecha y hora de la descarga en el fichero Museos_date.txt
		String buffer_date;
		FileOutputStream fos_date = ctx.openFileOutput("Museos_date.txt", Context.MODE_PRIVATE);
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
		Date date = new Date(); // coger la fecha y hora actuales del sistema
		buffer_date = dateFormat.format(date);
		fos_date.write(buffer_date.getBytes());
		fos_date.close();

		StrictMode.ThreadPolicy permitNetwork = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
	    StrictMode.setThreadPolicy(permitNetwork);

	    URLConnection urlCon = _url.openConnection();
	    HttpURLConnection httpUrlCon = (HttpURLConnection) urlCon;
	    httpUrlCon.setReadTimeout(10000);
	    httpUrlCon.setConnectTimeout(15000);
	    httpUrlCon.setRequestMethod("GET");
	    httpUrlCon.setDoInput(true);
	    httpUrlCon.connect();
	    InputStreamReader isr = new InputStreamReader(urlCon.getInputStream(), "ISO-8859-1");
	    BufferedReader br = new BufferedReader(isr);

	    FileOutputStream fos = ctx.openFileOutput("Museos.csv", Context.MODE_PRIVATE);

	    String buffer;

	    while ((buffer = br.readLine()) != null) {
	    	buffer += "\n";
	        fos.write(buffer.getBytes());
	    }

	    fos.close();
	    br.close();
	    isr.close();

		StrictMode.ThreadPolicy notPermitNetwork = new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeathOnNetwork().build();
	    StrictMode.setThreadPolicy(notPermitNetwork);
	}

	/*
	 *
	 * Comprobar si hay conexion a Internet
	 *
	 *		true ---> hay conexion a internet
	 *		false ---> no hay conexion a internet
	 *
	 */
	private boolean isInternetActive(Context ctx) {
	    boolean bConectado = false;
	    ConnectivityManager connec = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] redes = connec.getAllNetworkInfo(); // comprobar todas las formas de conexion a internet (WiFi y GPRS)
	    for (int i = 0; i < 2; i++) {
	        if (redes[i].getState() == NetworkInfo.State.CONNECTED) { // desde que una sea true, considero que hay conexion
	            bConectado = true;
	        }
	    }
	    return bConectado;
	}

	
}
