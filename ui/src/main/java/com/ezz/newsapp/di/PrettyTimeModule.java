package com.ezz.newsapp.di;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Locale;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
@Module
public class PrettyTimeModule {
	@Provides
	PrettyTime providePrettyTime(){
		return new PrettyTime(Locale.US);
	}
}
