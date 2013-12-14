package org.museosdetenerife.app;


import android.os.Bundle;
import android.widget.ProgressBar;
import android.app.Activity;
import android.content.Intent;

public class StartActivity extends Activity {

	private int splashTime = 3000;
	private Thread thread;
	private ProgressBar mSpinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		mSpinner = (ProgressBar) findViewById(R.id.Splash_ProgressBar);
	    mSpinner.setIndeterminate(true);
	    thread = new Thread(runable);
	    thread.start(); 
	    
	}
	
	public Runnable runable = new Runnable() {
	    public void run() {
	        try {
	            Thread.sleep(splashTime);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        try {
	            startActivity(new Intent(StartActivity.this,MainActivity.class));
	            finish();
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	    }
	};

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.start, menu);
//		return true;
//	}

}
