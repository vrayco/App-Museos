package get.data.rss;

public class Item {

	private String title = "";
	private String description = "";
	private String link = "";
	private String lugar = "";
	private String evento = "";
	private String fecha_fin = "";
	private String fecha = "";
	private String imagen = "";
	private String pubDate = "";
	private String author = "";
	private String guid = "";

	public Item() {
		setTitle("");
		setDescription("");
		setLink("");
		setLugar("");
		setEvento("");
		setFecha_fin("");
		setFecha("");
		setImagen("");
		setPubDate("");
		setAuthor("");
    	setGuid("");
	}

	public Item(String title, String description, String link, String lugar,
				String evento, String fecha_fin, String fecha, String imagen,
				String pubDate, String author, String guid) {
        setTitle(title);
        setDescription(description);
        setLink(link);
        setLugar(lugar);
        setEvento(evento);
        setFecha_fin(fecha_fin);
        setFecha(fecha);
        setImagen(imagen);
        setPubDate(pubDate);
        setAuthor(author);
        setGuid(guid);
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String itemToString(String separator) {
		return	getTitle() + separator +
				getDescription() + separator +
				getLink() + separator +
				getLugar() + separator +
				getEvento() + separator +
				getFecha_fin() + separator +
				getFecha() + separator +
				getImagen() + separator +
				getPubDate() + separator +
				getAuthor() + separator +
				getGuid();
	}
}
