package com.sistema.app;

import com.sistema.app.beans.Person;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerAct extends AppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);
		Spinner spinner = (Spinner) 
	    		findViewById(R.id.spinner);
		ArrayAdapter<Person> adapter=new
				ArrayAdapter<Person>(this,
						android.R.layout.simple_spinner_item,
						datos
						);
		adapter.setDropDownViewResource
		(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(
	    		new MyOnItemSelectedListener());
	}


}
