package get.data.rss;

import java.util.ArrayList;
import java.util.Iterator;

public class Channel {
	public String title = "";
	public String link = "";
	public String description = "";
	public String language = "";
	public String copyright = "";
	public String managingEditor = "";
	public String webMaster = "";
	public String generator = "";
	public ArrayList<Item> items = new ArrayList<Item>();

	public Channel() {
		setTitle("");
		setLink("");
		setDescription("");
		setLanguage("");
		setCopyright("");
		setManagingEditor("");
		setWebMaster("");
		setGenerator("");
		setItems(new ArrayList<Item>());
	}

	public Channel(String title, String link, String description,
					String language, String copyright, String managingEditor,
					String webMaster, String generator, ArrayList<Item> items) {
		setTitle(title);
        setLink(link);
        setDescription(description);
        setLanguage(language);
        setCopyright(copyright);
        setManagingEditor(managingEditor);
        setWebMaster(webMaster);
        setGenerator(generator);
        setItems(items);
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getManagingEditor() {
		return managingEditor;
	}

	public void setManagingEditor(String managingEditor) {
		this.managingEditor = managingEditor;
	}

	public String getWebMaster() {
		return webMaster;
	}

	public void setWebMaster(String webMaster) {
		this.webMaster = webMaster;
	}

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public String channelToString(String separator) {

		int i = 0;
		String events = "";
		for (Iterator<Item> iterador = items.listIterator(); iterador.hasNext(); i++)
			if (iterador.hasNext())
				events += "Evento " + i + ":" + separator + iterador.next().itemToString(separator) + separator;
			else
				events += "Evento " + i + ":" + separator + iterador.next().itemToString(separator);

		return	getTitle() + separator +
				getLink() + separator +
				getDescription() + separator +
				getLanguage() + separator +
				getCopyright() + separator +
				getManagingEditor() + separator +
				getWebMaster() + separator +
				getGenerator() + separator +
				events;
	}

}
