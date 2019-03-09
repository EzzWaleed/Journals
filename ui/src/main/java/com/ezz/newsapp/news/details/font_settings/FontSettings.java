package com.ezz.newsapp.news.details.font_settings;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
public class FontSettings {

	public ObservableField<Float> textSize = new ObservableField<>();
	public ObservableField<Boolean> textStyle = new ObservableField<>();
	public ObservableInt textSizePercentage = new ObservableInt();

	/**
	 * Use this method to convert percentage value to text size according to the default font range of the app.
	 * @param percentage the percentage of text size from (1 - 100).
	 */
	public void setTextSizeBy(int percentage) {
		this.textSizePercentage.set(percentage);
		this.textSize.set((90 * ((float)(percentage) / 100)));
	}

}
