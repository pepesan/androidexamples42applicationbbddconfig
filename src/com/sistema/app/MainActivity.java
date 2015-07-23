package com.sistema.app;

import java.util.ArrayList;

import com.sistema.app.beans.Person;

import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity 
		extends AppListActivity {
	ListView lv;
	TestListAdapter adapter;
	private Boolean primera=true;
	private Carga hilo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpViews();
		//app.meteDatos();
		hilo=new Carga();
		hilo.execute();
	}
	public class Carga extends AsyncTask
	<Void,Void, Void>{
		@Override
		protected void onPreExecute(){
			Toast.makeText(MainActivity.this, 
					"Cargando", Toast.LENGTH_LONG)
					.show();
		}
		@Override
		protected Void doInBackground(Void... 
				nada) {
			app.loadList();
			adapter=new TestListAdapter(
					MainActivity.this,
					R.layout.item,app.getPersons());
			return null;
		}
		@Override
		protected void onPostExecute(Void v){
			lv.setAdapter(adapter);
			Toast.makeText(MainActivity.this, 
					"Cargado", Toast.LENGTH_LONG)
					.show();
		}
		
	}
	private void setUpViews() {
		// TODO Auto-generated method stub
		lv=this.getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, 
		    		View view,
		        int position, long id) {
		    	Intent intent = new Intent(MainActivity.this, 
						Mostrar.class);
		    	intent.putExtra("id", id);
		    	startActivity(intent);	
		    	
		    }
		});
	}

	public void goAdd(View v){
		Intent intent=new Intent(this,AddEdit.class);
		this.startActivity(intent);
	}
	public void goSpinner(View v){
		Intent intent=new Intent(this,
						SpinnerAct.class);
		this.startActivity(intent);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		if(primera==true){
			primera=false;
		}else{
			adapter.forceReload();
		}
	}
	
}
