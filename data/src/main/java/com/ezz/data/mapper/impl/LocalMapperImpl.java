package com.ezz.data.mapper.impl;

import com.ezz.data.local.model.NewsLocal;
import com.ezz.data.mapper.LocalMapper;
import com.ezz.domain.entity.NewsDomain;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
public class LocalMapperImpl implements LocalMapper {


	@Override
	public NewsLocal mapDomainToLocal(@NonNull NewsDomain newsDomain) {
		NewsLocal newsLocal = new NewsLocal();
		newsLocal.authorName = newsDomain.getAuthorName();
		newsLocal.content = newsDomain.getContent();
		newsLocal.description = newsDomain.getDescription();
		newsLocal.imageUrl = newsDomain.getImageUrl();
		newsLocal.publishedDate = newsDomain.getPublishedDate();
		newsLocal.sourceName = newsDomain.getSourceName();
		newsLocal.title = newsDomain.getTitle();
		newsLocal.url = newsDomain.getUrl();
		return newsLocal;
	}

	@Override
	public List<NewsLocal> mapDomainListToLocal(@NonNull List<NewsDomain> newsDomainList) {
		List<NewsLocal> newsLocalList = new ArrayList<>();
		for (NewsDomain newsDomain : newsDomainList) {
			newsLocalList.add(mapDomainToLocal(newsDomain));
		}
		return newsLocalList;
	}
}
