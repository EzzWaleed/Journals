package com.ezz.presentation.viewmodel.viewmodel_factory.di;

import java.lang.annotation.Target;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;

import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by Ezz Waleed on 07,March,2019
 */
@Target({METHOD})
@MapKey
public @interface ViewModelKey {
	Class< ? extends ViewModel> value();
}
