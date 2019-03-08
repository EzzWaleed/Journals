package com.ezz.newsapp.binding_adapter;

import android.widget.TextView;

import com.ezz.newsapp.binding_adapter.di.DataBindingScope;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

import javax.inject.Inject;

import androidx.databinding.BindingAdapter;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
@DataBindingScope
public class PrettyTimeAdapter {

	private PrettyTime prettyTime;

	@Inject
	public PrettyTimeAdapter(PrettyTime prettyTime) {
		this.prettyTime = prettyTime;
	}

	@BindingAdapter(value = "app:date")
	public void setPrettyTime(TextView textView, Date date){
		textView.setText(prettyTime.format(date));
	}

}
