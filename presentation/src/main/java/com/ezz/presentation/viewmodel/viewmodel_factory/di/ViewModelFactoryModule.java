package com.ezz.presentation.viewmodel.viewmodel_factory.di;

import com.ezz.presentation.di.PresentationScope;
import com.ezz.presentation.viewmodel.news.di.NewsVmModule;
import com.ezz.presentation.viewmodel.search.di.SearchVmModule;
import com.ezz.presentation.viewmodel.viewmodel_factory.ViewModelFactory;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;

/**
 * Created by Ezz Waleed on 07,March,2019
 */
@Module(includes = {NewsVmModule.class, SearchVmModule.class})
public interface ViewModelFactoryModule {
	@Binds
	@PresentationScope
	ViewModelProvider.Factory provideViewModelFactory(ViewModelFactory viewModelFactory);
}
