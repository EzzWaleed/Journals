package com.ezz.data.mapper.impl;

import com.ezz.data.local.model.NewsLocal;
import com.ezz.data.mapper.DomainMapper;
import com.ezz.data.remote.client.SettingsAPI;
import com.ezz.data.remote.model.NewsRemote;
import com.ezz.domain.entity.NewsDomain;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
public class DomainMapperImpl implements DomainMapper {

	@Inject
	public DomainMapperImpl() {
	}

	@Override
	public NewsDomain mapLocalToDomain(@NonNull NewsLocal newsLocal) {
		NewsDomain newsDomain = new NewsDomain();
		newsDomain.setAuthorName(newsLocal.authorName);
		newsDomain.setContent(newsLocal.content);
		newsDomain.setDescription(newsLocal.description);
		newsDomain.setImageUrl(newsLocal.imageUrl);
		newsDomain.setPublishedDate(newsLocal.publishedDate);
		newsDomain.setSourceName(newsLocal.sourceName);
		newsDomain.setTitle(newsLocal.title);
		newsDomain.setUrl(newsLocal.url);
		return newsDomain;
	}

	@Override
	public NewsDomain mapRemoteToDomain(@NonNull NewsRemote newsRemote) {
		NewsDomain newsDomain = new NewsDomain();
		newsDomain.setAuthorName(newsRemote.getAuthor());
		newsDomain.setContent(removeExtraContent(newsRemote.getContent()));
		newsDomain.setDescription(newsRemote.getDescription());
		newsDomain.setImageUrl(newsRemote.getUrlToImage());
		newsDomain.setPublishedDate(convertToTime(newsRemote.getPublishedAt()));
		newsDomain.setSourceName(newsRemote.getSourceRemote().getName());
		newsDomain.setTitle(newsRemote.getTitle());
		newsDomain.setUrl(newsRemote.getUrl());
		return newsDomain;
	}

	@Override
	public List<NewsDomain> mapLocalListToDomain(@NonNull List<NewsLocal> newsLocalList) {
		List<NewsDomain> newsDomainList = new ArrayList<>();
		for (NewsLocal newsLocal : newsLocalList) {
			newsDomainList.add(mapLocalToDomain(newsLocal));
		}
		return newsDomainList;
	}

	@Override
	public List<NewsDomain> mapRemoteListToDomain(@NonNull List<NewsRemote> newsRemoteList) {
		List<NewsDomain> newsDomainList = new ArrayList<>();
		for (NewsRemote newsRemote : newsRemoteList) {
			newsDomainList.add(mapRemoteToDomain(newsRemote));
		}
		return newsDomainList;
	}

	/**
	 * Removes extra string at the of {@link NewsRemote} content.
	 * Extra content looks like (de…[+1119 chars])
	 * @param content {@link NewsRemote} content.
	 * @return {@link NewsRemote} content after removing extra text.
	 */
	private String removeExtraContent(@Nullable String content){
		if (content != null && content.length() > 0) {
			int endIndex = content.lastIndexOf("…");
			if (endIndex != -1) {
				if (endIndex == content.toCharArray().length - 1)
					return content;
				return content.substring(0, endIndex + 1);
			}
		}
		return content;
	}

	/**
	 * convert date to TimeInMillis
	 *
	 * @param date date to convert
	 * @return TimeInMillis
	 */
	private long convertToTime(@NonNull String date) {
		try {
			return SettingsAPI.getDateNetworkFormat().parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Calendar.getInstance().getTimeInMillis();
	}
}
