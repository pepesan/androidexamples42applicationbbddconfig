package com.sistema.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreferenciasCompartidas {
	private SharedPreferences preferences;
	private Editor edit;
	public static final String 
				USERNAMEKEY="username";
	public static final String 
				USERNAMEDEFAULT="n/a";
	public String username;
	public static final String 
				PASSWORDKEY="password";
	public static final String 
				PASSWORDDEFAULT="n/a";
	public String password;
	
	public PreferenciasCompartidas(Context c) {
		preferences = PreferenceManager
				.getDefaultSharedPreferences(c);
		edit = preferences.edit();
		username= preferences
				.getString(USERNAMEKEY, 
						USERNAMEDEFAULT);
		password = preferences
				.getString(PASSWORDKEY, 
						PASSWORDDEFAULT);
	}

	public String getUsername() {
		username=preferences.getString(
				USERNAMEKEY, USERNAMEDEFAULT);
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		edit.putString(USERNAMEKEY, username);
		edit.commit();
	}

	public String getPassword() {
		password=preferences.getString(
				PASSWORDKEY, PASSWORDDEFAULT);
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		edit.putString(PASSWORDKEY, password);
		edit.commit();
	}

}
