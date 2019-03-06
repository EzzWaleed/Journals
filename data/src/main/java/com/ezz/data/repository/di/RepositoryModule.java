package com.ezz.data.repository.di;

import com.ezz.data.repository.NewsRepositoryImpl;
import com.ezz.domain.repository.NewsRepository;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ezz Waleed on 06,March,2019
 */
@Module
public interface RepositoryModule {
	@Binds
	NewsRepository provideNewsRepository(NewsRepositoryImpl newsRepositoryImpl);
}
