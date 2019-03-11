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
}
