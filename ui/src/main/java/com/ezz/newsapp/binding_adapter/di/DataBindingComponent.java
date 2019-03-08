package com.ezz.newsapp.binding_adapter.di;

import com.ezz.data.di.DataComponent;
import com.ezz.newsapp.di.PrettyTimeModule;

import dagger.Component;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
@DataBindingScope
@Component(modules = PrettyTimeModule.class, dependencies = DataComponent.class)
public interface DataBindingComponent extends androidx.databinding.DataBindingComponent {

}
