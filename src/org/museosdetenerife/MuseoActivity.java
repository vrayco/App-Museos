package org.museosdetenerife;

import get.data.csv.CSV_Map;
import get.data.csv.DownloadCSV;
import get.data.csv.ImportCSVMuseos;

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
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MuseoActivity extends FragmentActivity implements
		ActionBar.TabListener {
	
	static String id_museo;
	static String nombre = null;
	static String presentacion = null;
	static String horario = null;
	static String tarifas = null;
	static String latitud = null;
	static String longitud = null;

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
		
		Context context = this.getApplicationContext();


		DownloadCSV d_csv = null;
		ImportCSVMuseos csv_info = null;
		CSV_Map csv_map = null;

		try {
			d_csv = new DownloadCSV(context);
			csv_info = new ImportCSVMuseos(d_csv.getCSVFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		csv_map = new CSV_Map(csv_info.getListaMuseos());
		
		cambiarTheme(id_museo);
		
		final ActionBar actionBar = getActionBar();

//		Titulos titulo = new Titulos();
//		actionBar.setTitle(titulo.getTitulo(id_museo));
//		if(titulo.containsKeySubtitulo(id_museo))
//			actionBar.setSubtitle(titulo.getSubtitulo(id_museo));
		
		nombre = csv_map.getMuseoField(id_museo, "Nombre");
		if(csv_map.getMuseoField(id_museo, "IdPadre").equals("11"))
		{
			actionBar.setTitle("Historia y Antropolog’a");
			actionBar.setSubtitle(nombre);
		}
		else
			actionBar.setTitle(nombre);
		
		latitud = csv_map.getMuseoField(id_museo, "Latitud");
		longitud = csv_map.getMuseoField(id_museo, "Longitud");
		
		DatosEstaticos datos = new DatosEstaticos();
		presentacion = datos.getPresentacion(id_museo);
		horario = datos.getHorarios(id_museo);
		tarifas = datos.getTarifas(id_museo);
		
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
		if(id_museo.equals("1"))
			super.setTheme( R.style.Theme_Hombre );
		else if(id_museo.equals("6"))
			super.setTheme( R.style.Theme_Cosmos );
		if(id_museo.equals("12") || id_museo.equals("5") || id_museo.equals("7") )
			super.setTheme( R.style.Theme_Historia );
		if(id_museo.equals("9"))
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
				DatosEstaticos titulo = new DatosEstaticos();
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
		intent.putExtra("latitud",latitud);
		intent.putExtra("longitud",longitud);
		intent.putExtra("nombre", nombre);
			
		startActivity(intent);
	}
	
	public void IrActivity(View view) {
		Intent intent;
		
		intent = new Intent(this, IrActivity.class);
		intent.putExtra("ID", id_museo);
		intent.putExtra("latitud",latitud);
		intent.putExtra("longitud",longitud);
		intent.putExtra("nombre", nombre);
			
		startActivity(intent);
	}

}
