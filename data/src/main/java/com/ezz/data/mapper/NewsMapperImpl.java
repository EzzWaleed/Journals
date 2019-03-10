package com.ezz.data.mapper;

import com.ezz.data.local.model.NewsLocal;
import com.ezz.data.remote.client.SettingsAPI;
import com.ezz.data.remote.model.NewsRemote;
import com.ezz.data.remote.model.NewsResponse;
import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.resource.Resource;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Ezz Waleed on 05,March,2019
 */

/**
 * Implementation of {@link NewsMapper} interface.
 */
public class NewsMapperImpl implements NewsMapper {

	@Inject
	public NewsMapperImpl() {
	}

	@Override
	public NewsDomain mapToDomain(@NonNull NewsLocal newsLocal) {
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
	public NewsDomain mapToDomain(@NonNull NewsRemote newsRemote) {
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
			newsDomainList.add(mapToDomain(newsLocal));
		}
		return newsDomainList;
	}

	@Override
	public List<NewsDomain> mapRemoteListToDomain(@NonNull List<NewsRemote> newsRemoteList) {
		List<NewsDomain> newsDomainList = new ArrayList<>();
		for (NewsRemote newsRemote : newsRemoteList) {
			newsDomainList.add(mapToDomain(newsRemote));
		}
		return newsDomainList;
	}

	@Override
	public NewsLocal mapToLocal(@NonNull NewsRemote newsRemote) {
		NewsLocal newsLocal = new NewsLocal();
		newsLocal.authorName = newsRemote.getAuthor();
		newsLocal.content = removeExtraContent(newsRemote.getContent());
		newsLocal.description = newsRemote.getDescription();
		newsLocal.imageUrl = newsRemote.getUrlToImage();
		newsLocal.publishedDate = convertToTime(newsRemote.getPublishedAt());
		newsLocal.sourceName = newsRemote.getSourceRemote().getName();
		newsLocal.title = newsRemote.getTitle();
		newsLocal.url = newsRemote.getUrl();
		return newsLocal;
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

	@Override
	public NewsLocal mapToLocal(@NonNull NewsDomain newsDomain) {
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
			newsLocalList.add(mapToLocal(newsDomain));
		}
		return newsLocalList;
	}

	@Override
	public DataStatus mapToDataStatus(@NonNull NewsResponse newsResponse, @NonNull Integer pageNumber) {
		switch (SettingsAPI.NetworkStatus.valueOf(newsResponse.getStatus())) {
			case ok:
				if (isLastPage(newsResponse, pageNumber))
					return DataStatus.HAS_LOADED_ALL_ITEMS;
				return DataStatus.SUCCESS;
			case error:
				switch (SettingsAPI.NetworkCodes.valueOf(newsResponse.getCode())) {
					case apiKeyInvalid:
						return DataStatus.ERROR;
					case maximumResultsReached:
						return DataStatus.HAS_LOADED_ALL_ITEMS;
				}
				break;
		}
		return null;
	}

	@Override
	public Resource<List<NewsDomain>> mapToNewsDomainResource(@NonNull NewsResponse newsResponse, @NonNull Integer pageNumber) {
		DataStatus dataStatus = mapToDataStatus(newsResponse, pageNumber);
		return new Resource<List<NewsDomain>>(dataStatus, mapRemoteListToDomain(newsResponse.getNewsRemotes()), newsResponse.getCode());
	}

	/**
	 * detrmine {@link NewsResponse} page is the last or not
	 *
	 * @param newsResponse
	 * @param pageNumber   news response page number
	 */
	private boolean isLastPage(@NonNull NewsResponse newsResponse, @NonNull Integer pageNumber) {
		double lastPageNumber = Math.ceil(newsResponse.getTotalResults().doubleValue()
		/ SettingsAPI.getNumberOfItemsPerPage().doubleValue());

		return pageNumber.doubleValue() == lastPageNumber;
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

	@Override
	public List<NewsLocal> mapRemoteListToLocal(@NonNull List<NewsRemote> newsRemoteList) {
		List<NewsLocal> newsLocalList = new ArrayList<>();
		for (NewsRemote newsRemote : newsRemoteList) {
			newsLocalList.add(mapToLocal(newsRemote));
		}
		return newsLocalList;
	}
}
