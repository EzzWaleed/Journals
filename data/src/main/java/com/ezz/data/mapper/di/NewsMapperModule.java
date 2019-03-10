package com.ezz.data.mapper.di;

import com.ezz.data.mapper.DataStatusMapper;
import com.ezz.data.mapper.DomainMapper;
import com.ezz.data.mapper.LocalMapper;
import com.ezz.data.mapper.impl.DataStatusMapperImpl;
import com.ezz.data.mapper.impl.DomainMapperImpl;
import com.ezz.data.mapper.impl.LocalMapperImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ezz Waleed on 06,March,2019
 */
@Module
public interface NewsMapperModule {
	@Binds
	DomainMapper bindDomainMapper(DomainMapperImpl domainMapperImpl);

	@Binds
	LocalMapper bindDomainMapper(LocalMapperImpl localMapperImpl);

	@Binds
	DataStatusMapper bindDomainMapper(DataStatusMapperImpl dataStatusMapperImpl);
}
