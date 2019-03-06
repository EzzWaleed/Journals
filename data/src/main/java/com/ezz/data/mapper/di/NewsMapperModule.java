package com.ezz.data.mapper.di;

import com.ezz.data.mapper.NewsMapper;
import com.ezz.data.mapper.NewsMapperImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ezz Waleed on 06,March,2019
 */
@Module
public interface NewsMapperModule {
	@Binds
	NewsMapper provideNewsMapper(NewsMapperImpl newsMapperImpl);
}
