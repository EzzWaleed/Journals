package com.ezz.newsapp.util;

import android.content.Context;
import android.content.Intent;

import com.ezz.presentation.model.NewsUI;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
public class ShareUtil {
	public static void shareNews(Context context, NewsUI newsUI) {
		Intent share = new Intent(android.content.Intent.ACTION_SEND);
		share.setType("text/plain");
		share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

		// Add data to the intent, the receiving app will decide
		// what to do with it.
		share.putExtra(Intent.EXTRA_SUBJECT, newsUI.getTitle());
		share.putExtra(Intent.EXTRA_TEXT, newsUI.getUrl());
		context.startActivity(Intent.createChooser(share, "Share link!"));
	}
}
