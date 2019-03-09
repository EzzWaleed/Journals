package com.ezz.newsapp.news.details.font_settings.di;

import android.content.Context;

import com.ezz.newsapp.di.SharedPrefranceModule;
import com.ezz.newsapp.news.details.font_settings.FontSettingsBottomSheet;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
@Component(modules = SharedPrefranceModule.class)
public interface FontSettingsSheetComponent {

	void inject(FontSettingsBottomSheet fontSettingsBottomSheet);

	@Component.Builder
	interface Builder{

		@BindsInstance
		Builder bindContext(Context context);

		FontSettingsSheetComponent build();
	}

}
