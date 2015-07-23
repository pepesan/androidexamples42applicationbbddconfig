package com.sistema.app;

import java.util.List;



import com.sistema.app.beans.Person;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AppActivity extends Activity {
	
	protected PreferenciasCompartidas config;
	protected Aplicacion app;
	protected List<Person> datos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (Aplicacion)getApplication();
		datos=app.getPersons();
		config=app.getPreferences();
	}

	@Override
	public boolean onCreateOptionsMenu(
			Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);
		return true;
	}

	// This method is called once the menu is selected
	@Override
	public boolean onOptionsItemSelected(
			MenuItem item) {
		switch (item.getItemId()) {
		// We have only one menu option
		case R.id.preferences:
			// Launch Preference activity
			Intent i = new Intent(this, 
					MisPreferencias.class);
			startActivity(i);
			// Some feedback to the user
			Toast.makeText(this, 
				"Enter your user credentials.",
				Toast.LENGTH_LONG).show();
			break;

		}
		return true;
	}


}
