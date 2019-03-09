package com.ezz.newsapp.binding_adapter;

import android.graphics.Typeface;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
public class TextStyleBindingAdapter {
		@BindingAdapter("bind:typeface")
		public static void setTypeface(TextView v, boolean isBold) {
			if (isBold)
				v.setTypeface(null, Typeface.BOLD);
			else
				v.setTypeface(null, Typeface.NORMAL);
		}
	}

