package com.ezz.newsapp;

import android.app.Application;

import com.ezz.data.di.DaggerDataComponent;
import com.ezz.presentation.di.DaggerPresentationComponent;
import com.ezz.presentation.di.PresentationComponent;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
public class App extends Application {

	private PresentationComponent presentationComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		presentationComponent = DaggerPresentationComponent.builder()
		.dataComponent(DaggerDataComponent.builder().appContext(getApplicationContext()).build())
		.build();
	}

	public static PresentationComponent getPresentationComponent(AppCompatActivity activity){
		return ((App) activity.getApplication()).presentationComponent;
	}

}
