package com.sistema.app;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MisPreferencias extends 
			PreferenceActivity {
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(
				R.xml.preferences);
	}
}
