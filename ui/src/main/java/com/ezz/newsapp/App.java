package com.ezz.newsapp;

import android.app.Application;

import com.ezz.data.di.DaggerDataComponent;
import com.ezz.data.di.DataComponent;
import com.ezz.newsapp.binding_adapter.di.DaggerDataBindingComponent;
import com.ezz.presentation.di.DaggerPresentationComponent;
import com.ezz.presentation.di.PresentationComponent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
public class App extends Application {

	private PresentationComponent presentationComponent;

	@Override
	public void onCreate() {
		super.onCreate();

		DataComponent dataComponent = DaggerDataComponent.builder().appContext(getApplicationContext()).build();

		presentationComponent = DaggerPresentationComponent.builder()
		.dataComponent(dataComponent)
		.build();

		DataBindingUtil.setDefaultComponent(DaggerDataBindingComponent.builder().dataComponent(dataComponent).build());

	}

	public static PresentationComponent getPresentationComponent(AppCompatActivity activity){
		return ((App) activity.getApplication()).presentationComponent;
	}

}
