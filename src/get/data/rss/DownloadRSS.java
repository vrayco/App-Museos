package get.data.rss;

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

public class DownloadRSS {

	private URL _url;
	private File _rss_file;
	private File _rss_file_date;
	private boolean _isInternetConnectionActive;

	public DownloadRSS(Context ctx) throws IOException, ParseException {
		_url = new URL("http://www.museosdetenerife.org/rss/rss2.0.php");
		_rss_file = new File(ctx.getFilesDir().getAbsolutePath() + "/Eventos.rss");
		_rss_file_date = new File(ctx.getFilesDir().getAbsolutePath() + "/Eventos_date.txt");
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
		if (!_rss_file.exists())			// El fichero no existe
			if (_isInternetConnectionActive) {		// El fichero no existe y hay conexion a Internet --> Descargar el fichero
				networkDownload(ctx);
				Toast.makeText(ctx, "App-Museos no tenia el fichero, descarga del fichero desde internet", Toast.LENGTH_LONG).show();
			}
			else									// El fichero no existe y no hay conexion a Internet --> Mensaje de error (Muy Graveee)
				Toast.makeText(ctx, "App-Museos no funcionara correctamente, revise su conexion a Internet", Toast.LENGTH_LONG).show();
		else								// El fichero existe
			if (csvFileNeedsUpdate())				// El fichero existe y hay que actualizar	
				if (_isInternetConnectionActive) {		// El fichero existe, hay que actualizar y hay conexion --> Actualizamos
					_rss_file.delete();			// elimino fichero rss
					_rss_file_date.delete();	// elimino el fichero que contiene la fecha
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
	public File getRSSFile() {
		return _rss_file;
	}

	/*
	 *
	 * Funcion que decide si el fichero RSS necesita actualizacion
	 *
	 *		true ---> el fichero necesita actualizacion
	 *		false ---> el fichero no necesita actualizacion
	 *
	 */
	private boolean csvFileNeedsUpdate() throws IOException, ParseException {

		// Leer la fecha en la que se descargo por ultima vez el fichero rss.
		//		Esta fecha esta guardada en el fichero Eventos_date.txt
		FileReader fr = new FileReader(_rss_file_date);
		BufferedReader br = new BufferedReader(fr);
		String fechaDown = br.readLine();
		br.close();

		// Formato para las fechas
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);

	    // Fecha de la ultima descarga
		Date fechaDescarga = dateFormat.parse(fechaDown);
		String fechaDesc = dateFormat.format(fechaDescarga);

		// Fecha actual
		Date fechaActual = new Date(); // coger la fecha y hora actuales del sistema
		String fechaAct = dateFormat.format(fechaActual);

		// Diferencia en milisegundos entre la fecha actual y la fecha de la ultima descarga
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
		long diferencia = (fechaActual.getTime() - fechaDescarga.getTime()) / MILLSECS_PER_DAY;

		// Numero de dia de la ultima descarga
		String diaDes = fechaDesc.substring(8, 10);
		int diaDescarga = Integer.parseInt(diaDes);

		// Numero de dia actual
		String diaAct = fechaAct.substring(8, 10);
		int diaActual = Integer.parseInt(diaAct);

		// Diferencia entre dia actual y dia de la ultima descarga
		int dif = diaActual - diaDescarga;

		// Hora actual
		String horaAct = fechaAct.substring(11, 13);
		int horaActual = Integer.parseInt(horaAct);

		// Hora ultima descarga
		String horaDesc = fechaDesc.substring(11, 13);
		int horaDescarga = Integer.parseInt(horaDesc);

		if ((diferencia >= 1) || ((dif == 1) && (horaActual >= 14)) || ((dif == 0) && (horaActual >= 14) && (horaDescarga < 14)))
			return true;
		else
			return false;
	}

	/*
	 *
	 * Descargar el fichero RSS que contiene la informacion de los Museos desde la URL _url
	 * y guardarlo en almacenamiento interno (Eventos.rss).
	 *
	 * Guardar en el almacenamiento interno otro fichero (Eventos_date.txt) que indica
	 * la fecha y hora en la que descargamos el fichero.
	 *
	 */
	private void networkDownload(Context ctx) throws IOException {

		// Guardar la fecha y hora de la descarga en el fichero Eventos_date.txt
		String buffer_date;
		FileOutputStream fos_date = ctx.openFileOutput("Eventos_date.txt", Context.MODE_PRIVATE);
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
	    InputStreamReader isr = new InputStreamReader(urlCon.getInputStream());
	    BufferedReader br = new BufferedReader(isr);

	    FileOutputStream fos = ctx.openFileOutput("Eventos.rss", Context.MODE_PRIVATE);

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
