package com.ezz.presentation.di;

import com.ezz.presentation.mapper.di.MapperModule;
import com.ezz.presentation.viewmodel.viewmodel_factory.ViewModelFactory;
import com.ezz.presentation.viewmodel.viewmodel_factory.di.ViewModelFactoryModule;

import dagger.Component;

/**
 * Created by Ezz Waleed on 07,March,2019
 */
@Component(modules = {MapperModule.class, ViewModelFactoryModule.class})
@PresentationScope
public abstract class PresentationComponent {
	ViewModelFactory viewModelFactory;
	MapperModule mapperModule;
}
