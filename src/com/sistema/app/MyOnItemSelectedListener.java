package com.sistema.app;

import com.sistema.app.beans.Person;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MyOnItemSelectedListener 
	implements OnItemSelectedListener {

    public void onItemSelected(
    		AdapterView<?> parent,
        View view, int pos, long id) {
    	String text="";
    	Person p=(Person) 
    			parent.getItemAtPosition(pos);
    	Toast.makeText(parent.getContext(), 
    			"Nombre: "+p.getName()
    	, Toast.LENGTH_LONG).show();
      
    }

    public void onNothingSelected(
    		@SuppressWarnings("rawtypes") 					
    		AdapterView parent) {
    	Toast.makeText(parent.getContext(), 
    			"Select a person", 
    			Toast.LENGTH_LONG).show();
    }
}
