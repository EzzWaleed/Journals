package com.ezz.newsapp.binding_adapter;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;

import static com.ezz.newsapp.di.ItemAnimatorModule.FADE_IN_UP;

/**
 * Created by Ezz Waleed on 09,March,2019
 */

/**
 * Use ItemAnimatorBindingAdapter to bind item animators via xml to recyclerView.
 */
public class ItemAnimatorBindingAdapter {

	RecyclerView.ItemAnimator itemAnimator;

	@Inject
	public ItemAnimatorBindingAdapter(@Named(value = FADE_IN_UP) RecyclerView.ItemAnimator itemAnimator) {
		this.itemAnimator = itemAnimator;
	}

	/**
	 * Use attachItemAnimator() to bind ItemAnimator to RecyclerView.
	 * @param recyclerView RecyclerView required to be animated.
	 * @param attach If true item animator will attach to RecyclerView.
	 */
	@BindingAdapter(value = "bind:attachItemAnimator")
	public void attachItemAnimator(RecyclerView recyclerView, boolean attach){
		if (attach)
			recyclerView.setItemAnimator(itemAnimator);
	}
}
