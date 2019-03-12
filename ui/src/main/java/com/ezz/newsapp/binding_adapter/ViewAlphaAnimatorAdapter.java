package com.ezz.newsapp.binding_adapter;

import android.view.View;

import androidx.databinding.BindingAdapter;

/**
 * Created by Ezz Waleed on 12,March,2019
 */
public class ViewAlphaAnimatorAdapter {
	@BindingAdapter("binds:animateAlpha")
	public static void animate(View view, int delay){
		view.setAlpha(0f);
		view.animate().alpha(1f).setDuration(500).setStartDelay(delay);
	}
}
