package com.ezz.presentation.mapper;

import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.resource.Resource;
import com.ezz.presentation.model.NewsUI;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

/**
 * Created by Ezz Waleed on 07,March,2019
 */
public interface NewsMapper {

	/**
	 * maps {@link NewsDomain} to {@link NewsUI}
	 * @param newsDomain entity to be mapped
	 * @return mapped entity
	 */
	NewsUI mapToUI(@NonNull NewsDomain newsDomain);

	/**
	 * maps {@link NewsDomain} list to {@link NewsUI} list
	 * @param newsDomainList list to be mapped
	 * @return mapped list
	 */
	List<NewsUI> mapToUIList(@NonNull List<NewsDomain> newsDomainList);

	/**
	 * maps {@link NewsDomain} resource list to {@link NewsUI} resource list.
	 * @param newsDomainListResource resource to be mapped
	 * @return mapped resource
	 */
	Resource<List<NewsUI>> mapToUIResourceList(@NonNull Resource<List<NewsDomain>> newsDomainListResource);

}
