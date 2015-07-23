package com.sistema.app;


import java.util.ArrayList;
import java.util.List;


import com.sistema.app.beans.PersonSqliteHelper;
import static com.sistema.app.beans.PersonSqliteHelper.*;
import com.sistema.app.beans.Person;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

@SuppressLint("DefaultLocale")
public class Aplicacion extends Application {
	SQLiteDatabase db;
	List<Person> listado;
	PreferenciasCompartidas config;
	@Override
	public void onCreate() {
		super.onCreate();
		config=new PreferenciasCompartidas(this);
		PersonSqliteHelper helper = 
				new PersonSqliteHelper(this);
		db = helper.getWritableDatabase();
		
	}
	public PreferenciasCompartidas getPreferences() {
		return config;
	}
	
	public List<Person> getPersons(){
		return listado;
	}
	void loadList() {
		listado = new ArrayList<Person>();
		Cursor personCursor = db.query(PERSON_TABLE, 
									new String[] {PERSON_ID, 
									PERSON_NAME, 
									PERSON_TLF,PERSON_GLOBAL,
									PERSON_PHOTOPATH,
									PERSON_THUMBPHOTOPATH,
									PERSON_LOCATION}, 
									null, null, null, null, 
									String.format("%s,%s",
											PERSON_GLOBAL, PERSON_NAME ));
		personCursor.moveToFirst();
		Person p;
		if (! personCursor.isAfterLast()) {
			do {
				Long id = personCursor.getLong(0);
				String name  = personCursor.getString(1);
				String tlf = personCursor.getString(2);
				Float global = personCursor.getFloat(3);
				String filename  = personCursor.getString(4);
				String thumbfilename = personCursor.getString(5);
				String location = personCursor.getString(6);
				p = new Person(id,name,tlf,global);
				p.setPhotopath(filename);
				p.setThumbphotopath(thumbfilename);
				p.setLocation(location);
				listado.add(p);
			} while (personCursor.moveToNext());
		}
		
		personCursor.close();
		
	}

	public void add(Person p){
		assert(null != p);
		
		ContentValues values = new ContentValues();
		values.put(PERSON_NAME, p.getName());
		values.put(PERSON_TLF, p.getTlf());
		values.put(PERSON_GLOBAL, p.getGlobalpoints());
		values.put(PERSON_PHOTOPATH, p.getPhotopath());
		values.put(PERSON_THUMBPHOTOPATH, p.getThumbphotopath());
		values.put(PERSON_LOCATION, p.getLocation());
		values.put(PERSON_LATITUDE, p.getLatitude());
		values.put(PERSON_LONGITUDE, p.getLongitude());

		p.setId(db.insert(PERSON_TABLE, null, values));

		listado.add(p);
	}
	@SuppressLint("DefaultLocale")
	public void set(Person p) {
		assert(null != p);
		ContentValues values = new ContentValues();
		values.put(PERSON_NAME, p.getName());
		values.put(PERSON_TLF, p.getTlf());
		values.put(PERSON_GLOBAL, p.getGlobalpoints());
		values.put(PERSON_PHOTOPATH, p.getPhotopath());
		values.put(PERSON_THUMBPHOTOPATH, p.getThumbphotopath());
		values.put(PERSON_LOCATION, p.getLocation());
		values.put(PERSON_LATITUDE, p.getLatitude());
		values.put(PERSON_LONGITUDE, p.getLongitude());
		long id = p.getId();
		Log.d("id", ""+id);
		String where = String.format("%s = %d", 
				PERSON_ID, id);

		db.update(PERSON_TABLE, values, where, null);
		int tam=listado.size();
		for(int i=0;i<tam;i++){
			Person p2=listado.get(i);
			if(p.getId()==p2.getId()){
				listado.set(i,p);
				break;
			}
		}
	}
	public void remove(Person p) {
		String where = String.format("%s = %s", 
				PERSON_ID, 
				p.getId());
		db.delete(PERSON_TABLE, where, null);
		listado.remove(p);
	}
	
	public Person get(Long id){
		int count =listado.size();
		for(int i=0;i<count;i++){
			Person p= listado.get(i);
			if(p.getId()==id){
				return p; 
			}
		}
		return new Person();
	}
	public void removePersonByName(String s){
		//TODO: quitar persona por nombre
		//listado.remove(s);
	}
	public void meteDatos() {
		this.listado=new ArrayList<Person>();
		for(int i=0;i<1000;i++){
			Person p=new Person();
			p.setName("Pepe"+i);
			this.add(p);
		}
	}


}
