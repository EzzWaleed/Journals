package com.ezz.newsapp.binding_adapter;

import android.widget.ImageView;

import com.ezz.data.remote.imageloader.ImageLoader;
import com.ezz.newsapp.R;
import com.ezz.newsapp.binding_adapter.di.DataBindingScope;
import com.facebook.shimmer.ShimmerFrameLayout;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
@DataBindingScope
public class ImageBindingAdapter {

	private ImageLoader imageLoader;

	@Inject
	public ImageBindingAdapter(ImageLoader imageLoader) {
		this.imageLoader = imageLoader;
	}

	@BindingAdapter(value = {"url"})
	public void loadImage(ImageView imageView, String url){
		imageLoader.loadImageWithoutProgress(imageView, url, R.color.white, R.color.white);
	}

	@BindingAdapter(value = {"url"})
	public void loadImage(ShimmerFrameLayout shimmerFrameLayout, String url, String tag){
		@Nullable ImageView imageView = shimmerFrameLayout.findViewWithTag(tag);
		imageLoader.loadImageWithoutProgress(imageView, url, R.color.white, R.color.white);
	}

}
