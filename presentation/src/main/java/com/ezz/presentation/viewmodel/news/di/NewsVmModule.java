package com.ezz.presentation.viewmodel.news.di;

import com.ezz.data.di.SchedulersModule;
import com.ezz.presentation.viewmodel.news.NewsViewModel;
import com.ezz.presentation.viewmodel.viewmodel_factory.di.ViewModelKey;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
@Module(includes = {SchedulersModule.class})
public interface NewsVmModule {
	@Binds
	@IntoMap
	@ViewModelKey(NewsViewModel.class)
	ViewModel bindsNewsVm (NewsViewModel newsViewModel);
}
