package com.ezz.presentation.viewmodel.search.di;

import com.ezz.presentation.viewmodel.search.SearchViewModel;
import com.ezz.presentation.viewmodel.viewmodel_factory.di.ViewModelKey;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
@Module
public interface SearchVmModule {
	@Binds
	@IntoMap
	@ViewModelKey(SearchViewModel.class)
	ViewModel provideSearchViewModel(SearchViewModel searchViewModel);
}
