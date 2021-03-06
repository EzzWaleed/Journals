package com.ezz.data.di;

import android.content.Context;

import com.ezz.data.local.dao.NewsDao;
import com.ezz.data.local.di.DatabaseModule;
import com.ezz.data.mapper.di.NewsMapperModule;
import com.ezz.data.remote.client.NewsAPI;
import com.ezz.data.remote.di.ImageLoaderModule;
import com.ezz.data.remote.di.NetworkModule;
import com.ezz.data.remote.imageloader.ImageLoader;
import com.ezz.data.repository.di.RepositoryModule;
import com.ezz.domain.repository.NewsRepository;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
@Component(modules = {ImageLoaderModule.class, DatabaseModule.class, NewsMapperModule.class, RepositoryModule.class})
@DataScope
public interface DataComponent {

	NewsRepository newsRepository();

	ImageLoader imageLoader();

	@Component.Builder
	interface Builder {

		@BindsInstance
		Builder appContext(Context context);

		DataComponent build();
	}
}
