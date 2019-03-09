package com.ezz.newsapp.binding_adapter;

import android.view.View;
import android.view.ViewGroup;
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

	@BindingAdapter(value = {"bind:url"})
	public void loadImage(ImageView imageView, String url){
		imageLoader.loadImageWithoutProgress(imageView, url, R.color.white, R.color.white);
	}

	/**
	 * Used to load image from remote with shimmer loading behaviour.
	 * {@link ShimmerFrameLayout} rootView must contained at least one imageView with a tag to be able to use this adapter
	 * @param shimmerFrameLayout shimmer container
	 * @param url image remote url
	 * @param tag contained image view tag.
	 */
	@BindingAdapter(value = {"bind:url", "bind:imageViewTag"})
	public void loadImage(ShimmerFrameLayout shimmerFrameLayout, String url, String tag){
		@Nullable ImageView imageView = shimmerFrameLayout.getRootView().findViewWithTag(tag);
		if (imageView != null) {
			imageView.setVisibility(View.GONE);
			shimmerFrameLayout.setVisibility(View.VISIBLE);
			shimmerFrameLayout.startShimmer();
			imageLoader.loadImageWithCallback(imageView, url, () -> {
				shimmerFrameLayout.stopShimmer();
				shimmerFrameLayout.setVisibility(View.GONE);
				imageView.setVisibility(View.VISIBLE);
			}, R.color.white, R.color.white);
		}
	}

}
