package com.ezz.newsapp.di;

import javax.inject.Named;

import androidx.recyclerview.widget.RecyclerView;
import dagger.Module;
import dagger.Provides;
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
@Module
public class ItemAnimatorModule {

	public static final String FADE_IN_UP = "fadeInUp";

	@Provides
	@Named(value = FADE_IN_UP)
	RecyclerView.ItemAnimator provideItemAnimator(){
		FadeInUpAnimator fadeInUpAnimator = new FadeInUpAnimator();
		fadeInUpAnimator.setAddDuration(500);
		fadeInUpAnimator.setChangeDuration(500);
		fadeInUpAnimator.setMoveDuration(500);
		fadeInUpAnimator.setRemoveDuration(500);
		return fadeInUpAnimator;
	}
}
