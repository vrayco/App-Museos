package org.museosdetenerife;

import java.util.Locale;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Hashtable;

public class MuseoActivity extends FragmentActivity implements
		ActionBar.TabListener {
	
	static String id_museo;
	static String presentacion = "El Museo de la Naturaleza y el Hombre se encuentra ubicado en un destacado edificio de la capital insular, el Antiguo Hospital Civil de Nuestra Señora de los Desamparados, que data del año 1745, y que fue remodelado a finales del siglo XIX por el arquitecto Manuel de Oraá y Arcocha. Su fachada principal se considera como uno de los ejemplos más cualificados de la arquitectura neoclásica de las islas, por lo que fue declarado Bien de Interés Cultural, con la categoría de Monumento, en 1983. En el entorno del Museo se encuentran algunos de los espacios y edificios más emblemáticos de la ciudad Santa Cruz de Tenerife: la Iglesia de la Concepción, centro del núcleo fundacional de la ciudad, cuya primera construcción data del año 1500; la Calle de la Noria, uno de los paseos más tradicionales, animados y con más encanto del antiguo casco urbano y, finalmente, como ejemplo de arquitectura moderna, en los márgenes del Barranco de Santos, y justo al lado del Museo, el TEA (Tenerife Espacio de las Artes), inmueble vanguardista diseñado por el estudio de los arquitectos suizos Herzog & de Meuron. La rehabilitación integral del histórico edificio que alberga el Museo de la Naturaleza y el Hombre se ha venido realizando en distintas fases. Así, en el año 1993, se acometió una adecuación provisional, con el fin de destinar algunas dependencias a salas de exposición de los Museos de Ciencias Naturales y Arqueológico de Tenerife, de forma que estuvieran abiertas al público, en tanto se redactaban los proyectos, arquitectónico y museográfico, de la Fase I. La ejecución de ambos proyectos comenzó en 1994 y culminó en 1997. La adecuación del Museo de la Naturaleza y el Hombre continuó con la ejecución de su Fase II, que comprendió tanto obra civil como museográfica, y culminó en abril del año 1999. Con la Fase III, finalizó la intervención en las dependencias del inmueble para su uso como salas de exposición permanente. El conjunto museográfico, resultado de estas tres fases, fue inaugurado por Su Majestad la Reina Doña Sofía, el 9 de enero de 2002. Finalmente, en la IV y última fase, se intervino en la parte trasera del edificio, entre las calles Bravo Murillo, San Sebastián y lateral del Puente Serrador. Con ella, se acometieron áreas complementarias del Museo: Dependencias y laboratorios del Museo de Ciencias Naturales de Tenerife, del Museo Arqueológico de Tenerife y del Instituto Canario de Bioantropología, sala de exposiciones temporales, salón de actos, laboratorio de conservación de colecciones, taller de reproducciones y almacén general. Tras este largo proceso, hoy día, casi tres siglos después, se ha culminado la metamorfosis que ha transformado aquel antiguo hospital en un moderno museo, espacio para el conocimiento y trasmisión del importante patrimonio arqueológico y natural de Canarias.";
	static String horario = "De martes a sábado: de 9:00 a 20:00 h.<br/> Domingo, lunes y festivos: de 10:00 a 17:00 h.";
	static String tarifas= "<h4>TARIFAS EXPOSICIÓN PERMANENTE</h4><ul><li>General: 5,00 euros</li><li>Residente canario: 3,00 euros</li></ul><h4>Entradas bonificadas</h4><ul><li>Estudiantes: 2,50 euros</li><li>Jubilados y mayores de 65 años: 2,50 euros</li><li>Familia numerosa: 3,00 euros</li><li>Grupos de más de 8 visitantes: 3,00 euros</li></ul><h4>Entrada gratuita</h4><ul><li>Para todos los visitantes a la exposición permanente los viernes y sábado de 16:00 a 20:00.&nbsp;<span>En caso de que estos días sean festivos, el horario de gratuidad es de 13:00 a 17:00 h.</span></li><li>Grupos escolares de la Comunidad Autónoma de Canarias</li><li>Niños menos de 8 años</li><li>Miembros del ICOM</li><li>Profesores y guías acompañados de grupos o en visita prospectiva</li></ul>";

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_museo);
		
		Intent intent = getIntent();
		id_museo = intent.getStringExtra("ID");
		
		cambiarTheme(id_museo);
		
		final ActionBar actionBar = getActionBar();

		Titulos titulo = new Titulos();
		actionBar.setTitle(titulo.getTitulo(id_museo));
		if(titulo.containsKeySubtitulo(id_museo))
			actionBar.setSubtitle(titulo.getSubtitulo(id_museo));
		
		// Set up the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
	}
	
	public void cambiarTheme(String id_museo)
	{
		if(id_museo.equals("HOMBRE"))
			super.setTheme( R.style.Theme_Hombre );
		else if(id_museo.equals("COSMOS"))
			super.setTheme( R.style.Theme_Cosmos );
		if(id_museo.equals("HISTORIA1") || id_museo.equals("HISTORIA2") || id_museo.equals("HISTORIA3") )
			super.setTheme( R.style.Theme_Historia );
		if(id_museo.equals("CEDOCAM"))
			super.setTheme( R.style.Theme_Cedocam );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.museo, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = null;
			if(1 == getArguments().getInt(ARG_SECTION_NUMBER)){
				rootView = inflater.inflate(R.layout.fragment_info_museo,
						container, false);
				TextView dummyTextView = (TextView) rootView
						.findViewById(R.id.texto_info);
				dummyTextView.setText(Html.fromHtml(presentacion));
				
				ImageView imageView = (ImageView) rootView.findViewById(R.id.foto_museo);
				Titulos titulo = new Titulos();
				String mDrawableName = titulo.getFoto(id_museo);
				Context context = imageView.getContext();
				int resID = getResources().getIdentifier(mDrawableName , "drawable", context.getPackageName());
				imageView.setImageResource(resID);
				
				
			} else if(2 == getArguments().getInt(ARG_SECTION_NUMBER)) {
				rootView = inflater.inflate(R.layout.fragment_horarios_tarifas,
						container, false);
				TextView textoHorario = (TextView) rootView
						.findViewById(R.id.texto_horario);
				textoHorario.setText(Html.fromHtml(horario));
				
				TextView textoTarifas = (TextView) rootView
						.findViewById(R.id.texto_tarifas);
				textoTarifas.setText(Html.fromHtml(tarifas));
				
				
				
			}
			
			return rootView;				
			
		}
	}
	
	public void InfoActivity(View view) {
	    
		Intent intent;
		
		intent = new Intent(this, MuseoActivity.class);
		intent.putExtra("ID", id_museo);
			
		startActivity(intent);

	}
	
	public void EventosActivity(View view) {
		Intent intent;
		
		intent = new Intent(this, EventosActivity.class);
		intent.putExtra("ID", id_museo);
			
		startActivity(intent);
	}
	
	public void IrActivity(View view) {
		Intent intent;
		
		intent = new Intent(this, IrActivity.class);
		intent.putExtra("ID", id_museo);
			
		startActivity(intent);
	}

}
