package com.ezz.data.mapper;

import com.ezz.data.local.model.NewsLocal;
import com.ezz.data.remote.model.NewsRemote;
import com.ezz.data.remote.model.NewsResponse;
import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.resource.DataStatus;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
public interface LocalMapper {

	/**
	 * map {@link NewsDomain} to {@link NewsLocal}
	 * @param newsDomain entity to be mapped
	 * @return mapped entity
	 */
	NewsLocal mapDomainToLocal(@NonNull NewsDomain newsDomain);


	/**
	 * map {@link NewsDomain} list to {@link NewsLocal} list.
	 * @param newsDomainList list to be mapped.
	 * @return mapped list.
	 */
	List<NewsLocal> mapDomainListToLocal(@NonNull List<NewsDomain> newsDomainList);


}
