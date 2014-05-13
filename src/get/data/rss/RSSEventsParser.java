
package get.data.rss;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class RSSEventsParser {

	private static final String ns = null;

	public RSSEventsParser() {
	}

	public ArrayList<Channel> parse(InputStream in) throws XmlPullParserException, IOException {
		XmlPullParser parser = Xml.newPullParser();
		parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
		parser.setInput(in, null);
		parser.nextTag();
		return readRSS(parser);
    }

	// realiza el parseo completo de la fuente segun el formato especificado
	private ArrayList<Channel> readRSS(XmlPullParser parser) throws XmlPullParserException, IOException {

		parser.require(XmlPullParser.START_TAG, ns, "rss");

		ArrayList<Channel> channels = new ArrayList<Channel>();

		while (parser.next() != XmlPullParser.END_TAG) {

	    	if (parser.getEventType() != XmlPullParser.START_TAG)
	    		continue;

	    	String name = parser.getName();

	    	if (name.equals("channel")) {
	    		channels.add(readChannel(parser));
	    	} else {
	    		skip(parser);
	    	}

		}

		return channels;

	}
	
	// devuelve un canal completo con sus atributos y la lista de eventos correspondientes
	private Channel readChannel(XmlPullParser parser) throws XmlPullParserException, IOException {

		parser.require(XmlPullParser.START_TAG, ns, "channel");

	    String title = "";
	    String link = "";
	    String description = "";
	    String language = "";
	    String copyright = "";
	    String managingEditor = "";
	    String webMaster = "";
	    String generator = "";
		ArrayList<Item> items = new ArrayList<Item>();

	    while (parser.next() != XmlPullParser.END_TAG) {

	    	if (parser.getEventType() != XmlPullParser.START_TAG)
	    		continue;

	    	String name = parser.getName();

	    	if (name.equals("title")) {
	    		title = readTag(parser, "title");
	    	} else if (name.equals("link")) {
	    		link = readTag(parser, "link");
	    	} else if (name.equals("description")) {
	    		description = readTag(parser, "description");
	    	} else if (name.equals("language")) {
	    		language = readTag(parser, "language");
	    	} else if (name.equals("copyright")) {
	    		copyright = readTag(parser, "copyright");
	    	} else if (name.equals("managingEditor")) {
	    		managingEditor = readTag(parser, "managingEditor");
	    	} else if (name.equals("webMaster")) {
	    		webMaster = readTag(parser, "webMaster");
	    	} else if (name.equals("generator")) {
	    		generator = readTag(parser, "generator");
	    	} else if (name.equals("item")) {
	    		items.add(readChannelItem(parser));
	    	} else {
	    		skip(parser);
	    	}

	    }

	    return new Channel(title, link, description, language, copyright, managingEditor, webMaster, generator, items);

	}

	// devuelve un evento completo con todos sus atributos
	private Item readChannelItem(XmlPullParser parser) throws XmlPullParserException, IOException {

		parser.require(XmlPullParser.START_TAG, ns, "item");

		String title = "";
		String description = "";
		String link = "";
		String lugar = "";
		String evento = "";
		String fecha_fin = "";
		String fecha = "";
		String imagen = "";
		String pubDate = "";
		String author = "";
		String guid = "";

	    while (parser.next() != XmlPullParser.END_TAG) {

	    	if (parser.getEventType() != XmlPullParser.START_TAG)
	    		continue;

	    	String name = parser.getName();

	    	if (name.equals("title")) {
	    		title = readTag(parser, "title");
	    	} else if (name.equals("description")) {
	    		description = readTag(parser, "description");
	    	} else if (name.equals("link")) {
	    		link = readTag(parser, "link");
	    	} else if (name.equals("lugar")) {
	    		lugar = readTag(parser, "lugar");
	    	} else if (name.equals("evento")) {
	    		evento = readTag(parser, "evento");
	    	} else if (name.equals("fecha_fin")) {
	    		fecha_fin = readTag(parser, "fecha_fin");
	    	} else if (name.equals("fecha")) {
	    		fecha = readTag(parser, "fecha");
	    	} else if (name.equals("imagen")) {
	    		imagen = readTag(parser, "imagen");
	    	} else if (name.equals("pubDate")) {
	    		pubDate = readTag(parser, "pubDate");
	    	} else if (name.equals("author")) {
	    		author = readTag(parser, "author");
	    	} else if (name.equals("guid")) {
	    		guid = readTag(parser, "guid");
	    	} else {
	    		skip(parser);
	    	}

	    	// El campo "evento" puede tomar dos valores: "Noticia" o "Agenda"
	    	//		si es "Noticia" no hay campo "fecha_fin"
	    	//		si es "Agenda" hay campo "fecha_fin"
	    	if (evento.equals("Noticia"))
	    		fecha_fin = "-";

	    }

	    return new Item(title, description, link, lugar, evento, fecha_fin, fecha, imagen, pubDate, author, guid);

	}

	private String readTag(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, tag);
	    String generator = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, tag);
	    return generator;
	}

	// extraer el contenido de las distintas etiquetas
	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
	    String result = "";
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
	    }
	    return result;
	}

	// saltar aquel texto que no queremos
	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }


}