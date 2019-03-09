package com.ezz.newsapp.news.details.font_settings;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
public class FontSettingsDataStore {

	private static final String FONT_SIZE_KEY = "fontSizeKey";
	private static final String FONT_STYLE_KEY = "fontStyleKey";

	private SharedPreferences sharedPreferences;

	@Inject
	public FontSettingsDataStore(SharedPreferences sharedPreferences) {
		this.sharedPreferences = sharedPreferences;
	}

	public void setFontSize(int fontSize){
		sharedPreferences.edit().putInt(FONT_SIZE_KEY, fontSize).apply();
	}

	public int getFontSize(){
		return sharedPreferences.getInt(FONT_SIZE_KEY, 50);
	}

	public void setIsBold(boolean isBold){
		sharedPreferences.edit().putBoolean(FONT_STYLE_KEY, isBold).apply();
	}

	public boolean isBoldStyle(){
		return sharedPreferences.getBoolean(FONT_STYLE_KEY, false);
	}

}
