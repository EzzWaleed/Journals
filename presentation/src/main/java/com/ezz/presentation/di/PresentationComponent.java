package com.ezz.presentation.di;

import com.ezz.data.di.DataComponent;
import com.ezz.presentation.mapper.DataSourceMapper;
import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.mapper.di.MapperModule;
import com.ezz.presentation.viewmodel.news.NewsViewModel;
import com.ezz.presentation.viewmodel.news.di.NewsVmModule;
import com.ezz.presentation.viewmodel.viewmodel_factory.ViewModelFactory;
import com.ezz.presentation.viewmodel.viewmodel_factory.di.ViewModelFactoryModule;

import dagger.Component;

/**
 * Created by Ezz Waleed on 07,March,2019
 */
@Component(modules = {MapperModule.class, ViewModelFactoryModule.class}, dependencies = DataComponent.class)
@PresentationScope
public interface  PresentationComponent {
	ViewModelFactory viewModelFactory();
}
