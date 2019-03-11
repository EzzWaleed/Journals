package com.ezz.presentation.mapper;

import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.mapper.Mapper;
import com.ezz.presentation.model.NewsUI;

import javax.inject.Inject;

import androidx.paging.DataSource;


/**
 * Created by Ezz Waleed on 11,March,2019
 */
public class DataSourceMapper implements Mapper<DataSource.Factory<Integer, NewsDomain>, DataSource.Factory<Integer, NewsUI>> {

	private NewsMapper newsMapper;

	@Inject
	public DataSourceMapper(NewsMapper newsMapper) {
		this.newsMapper = newsMapper;
	}

	@Override
	public DataSource.Factory<Integer, NewsUI> map(DataSource.Factory<Integer, NewsDomain> dataSourceFactoryDomain) {
		return dataSourceFactoryDomain.map(input -> newsMapper.mapToUI(input));
	}
}
