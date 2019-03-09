package com.ezz.newsapp.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
@Module
public class SharedPrefranceModule {
	@Provides
	SharedPreferences sharedPreferences(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
}
