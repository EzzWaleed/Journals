package com.ezz.presentation.mapper.di;

import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.mapper.NewsMapperImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ezz Waleed on 07,March,2019
 */
@Module
public interface MapperModule {
	@Binds
	NewsMapper provideMapper(NewsMapperImpl newsMapperImpl);
}
