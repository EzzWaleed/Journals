package com.ezz.presentation.viewmodel.viewmodel_factory;

import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by Ezz Waleed on 07,March,2019
 */

/**
 * unique ViewModel Factory that provides
 * all kind of ViewModel we need based from the modules inside the component
 * using dagger multibindings.
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

	private Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelMap;

	@Inject
	public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelMap) {
		this.viewModelMap = viewModelMap;
	}

	@SuppressWarnings({"SuspiciousMethodCalls", "unchecked"})
	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		Provider<ViewModel> provider = viewModelMap.get(modelClass);
		final Set<Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>>> entries = viewModelMap.entrySet();
		for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : entries) {
			if (entry.getKey().isAssignableFrom(modelClass)) {
				return (T) provider.get();
			}
		}
		throw new IllegalArgumentException("unknown model class " + modelClass);
	}
}
