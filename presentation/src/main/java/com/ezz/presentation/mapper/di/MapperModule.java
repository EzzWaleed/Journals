package com.ezz.presentation.mapper.di;

import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.mapper.Mapper;
import com.ezz.presentation.mapper.DataSourceMapper;
import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.mapper.NewsMapperImpl;
import com.ezz.presentation.model.NewsUI;

import androidx.paging.DataSource;
import dagger.Binds;
import dagger.Module;

/**
 * Created by Ezz Waleed on 07,March,2019
 */
@Module
public interface MapperModule {
	@Binds
	NewsMapper bindMapper(NewsMapperImpl newsMapperImpl);

	@Binds
	Mapper<DataSource.Factory<Integer, NewsDomain>, DataSource.Factory<Integer, NewsUI>> bindDataSourceMapper(DataSourceMapper dataSourceMapper);
}
