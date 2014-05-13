package get.data.csv;

public class Museo {

	private String id;
	private String id_padre;
	private String nombre;
	private String domicilio;
	private String cp;
	private String localidad;
	private String provincia;
	private String telefono;
	private String url;
	private String latitud;
	private String longitud;

	public Museo() {
		setId("");
		setId_padre("");
		setNombre("");
		setDomicilio("");
		setCp("");
		setLocalidad("");
		setProvincia("");
		setTelefono("");
		setUrl("");
		setLatitud("");
		setLongitud("");
	}

	public Museo(String id, String id_padre, String nombre, String domicilio, String cp,
			      String localidad, String provincia, String telefono, String url, String latitud, String longitud) {
		setId(id);
		setId_padre(id_padre);
		setNombre(nombre);
		setDomicilio(domicilio);
		setCp(cp);
		setLocalidad(localidad);
		setProvincia(provincia);
		setTelefono(telefono);
		setUrl(url);
		setLatitud(latitud);
		setLongitud(longitud);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_padre() {
		return id_padre;
	}

	public void setId_padre(String id_padre) {
		this.id_padre = id_padre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String museoToString(String separator) {
		return 	getId() + separator +
				getId_padre() + separator +
				getNombre() + separator +
				getDomicilio() + separator +
				getCp() + separator +
				getLocalidad() + separator +
				getProvincia() + separator +
				getTelefono() + separator +
				getUrl() + separator +
				getLatitud() + separator +
				getLongitud();
	}
}

