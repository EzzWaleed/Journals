package com.ezz.presentation.viewmodel.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

/**
 * Created by Ezz Waleed on 11,March,2019
 */

public class LiveDataTestUtil {
	public static < T > T getValue(LiveData< T > liveData) throws InterruptedException {
		final Object[] data = new Object[1];
		CountDownLatch latch = new CountDownLatch(1);
		Observer < T > observer = new Observer< T >() {
			@Override
			public void onChanged(@Nullable T o) {
				data[0] = o;
				latch.countDown();
				liveData.removeObserver(this);
			}
		};
		liveData.observeForever(observer);
		latch.await(2, TimeUnit.SECONDS);

		return (T) data[0];
	}
}