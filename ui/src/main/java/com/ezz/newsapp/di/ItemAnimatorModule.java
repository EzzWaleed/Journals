package com.ezz.newsapp.di;

import javax.inject.Named;

import androidx.recyclerview.widget.RecyclerView;
import dagger.Module;
import dagger.Provides;
import jp.wasabeef.recyclerview.animators.FadeInRightAnimator;
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
@Module
public class ItemAnimatorModule {

	public static final String FADE_IN_UP = "fadeInUp";

	public static final String FADE_IN_RIGHT = "fadeInRight";

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

	@Provides
	@Named(value = FADE_IN_RIGHT)
	RecyclerView.ItemAnimator provideItemAnimatorRight(){
		FadeInRightAnimator fadeInRightAnimator = new FadeInRightAnimator();
		fadeInRightAnimator.setAddDuration(900);
		fadeInRightAnimator.setChangeDuration(900);
		fadeInRightAnimator.setMoveDuration(900);
		fadeInRightAnimator.setRemoveDuration(900);
		return fadeInRightAnimator;
	}
}
