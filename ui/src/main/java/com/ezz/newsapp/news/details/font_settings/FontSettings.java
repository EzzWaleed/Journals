package com.ezz.newsapp.news.details.font_settings;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableFloat;
import androidx.databinding.ObservableInt;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
public class FontSettings {

	public ObservableFloat textSize = new ObservableFloat();
	public ObservableBoolean textStyle = new ObservableBoolean();
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
