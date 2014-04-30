package org.museosdetenerife;


import java.util.Hashtable;


public class Titulos{
	
	Hashtable<String, String> TITULOS = new Hashtable();
	Hashtable<String, String> SUBTITULOS = new Hashtable();
	Hashtable<String, String> COLOR = new Hashtable();
	Hashtable<String, String> FOTOS = new Hashtable();

	public Titulos()
	{
		TITULOS.put("HISTORIA1", "Historia y Antropología");
		TITULOS.put("HISTORIA2", "Historia y Antropología");
		TITULOS.put("HISTORIA3", "Historia y Antropología");
		TITULOS.put("COSMOS", "Ciencias y El Cosmos");
		TITULOS.put("HOMBRE", "La Naturaleza y El Hombre");
		TITULOS.put("CEDOCAM", "Centro de Documentación de Canariasy y Américas");
		
		SUBTITULOS.put("HISTORIA1", "Casa Lercaro");
		SUBTITULOS.put("HISTORIA2", "Casa de Carta");
		SUBTITULOS.put("HISTORIA3", "Castillo de San Cristobal");
		
		COLOR.put("HISTORIA1", "#b50937");
		COLOR.put("HISTORIA2", "#b50937");
		COLOR.put("HISTORIA3", "#b50937");
		COLOR.put("COSMOS", "#ffab18");
		COLOR.put("HOMBRE", "#5d9732");
		COLOR.put("CEDOCAM", "#1d5fad");
		
		FOTOS.put("HISTORIA1", "foto_mha_lercaro");
		FOTOS.put("HISTORIA2", "foto_mha_carta");
		FOTOS.put("HISTORIA3", "foto_mha_castillo");
		FOTOS.put("COSMOS", "foto_mcc");
		FOTOS.put("HOMBRE", "foto_mnh");
		FOTOS.put("CEDOCAM", "foto_cedocam");
		
		
	}
	
	
	public String getTitulo(String id_museo)
	{
		return TITULOS.get(id_museo);
	}
	
	public String getSubtitulo(String id_museo)
	{
		return SUBTITULOS.get(id_museo);
	}
	
	public String getColor(String id_museo)
	{
		return COLOR.get(id_museo);
	}

	public String getFoto(String id_museo)
	{
		return FOTOS.get(id_museo);
	}
	
	public boolean containsKeySubtitulo(String id_museo)
	{
		return SUBTITULOS.containsKey(id_museo);
	}

}
