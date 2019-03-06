package com.ezz.data.mapper;

import com.ezz.data.local.model.NewsLocal;
import com.ezz.data.remote.client.SettingsAPI;
import com.ezz.data.remote.model.NewsRemote;
import com.ezz.domain.entity.NewsDomain;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by Ezz Waleed on 05,March,2019
 */

/**
 * Implementation of {@link NewsMapper} interface.
 */
public class NewsMapperImpl implements NewsMapper{


    @Override
    public NewsDomain mapToDomain(NewsLocal newsLocal) {
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
        newsDomain.setContent(newsRemote.getContent());
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
        for (NewsLocal newsLocal : newsLocalList){
            newsDomainList.add(mapToDomain(newsLocal));
        }
        return newsDomainList;
    }

    @Override
    public List<NewsDomain> mapRemoteListToDomain(@NonNull List<NewsRemote> newsRemoteList) {
        List<NewsDomain> newsDomainList = new ArrayList<>();
        for (NewsRemote newsRemote : newsRemoteList){
            newsDomainList.add(mapToDomain(newsRemote));
        }
        return newsDomainList;
    }

    @Override
    public NewsLocal mapToLocal(@NonNull NewsRemote newsRemote) {
        NewsLocal newsLocal = new NewsLocal();
        newsLocal.authorName = newsRemote.getAuthor();
        newsLocal.content = newsRemote.getContent();
        newsLocal.description = newsRemote.getDescription();
        newsLocal.imageUrl = newsRemote.getUrlToImage();
        newsLocal.publishedDate = convertToTime(newsRemote.getPublishedAt());
        newsLocal.sourceName = newsRemote.getSourceRemote().getName();
        newsLocal.title = newsRemote.getTitle();
        newsLocal.url = newsRemote.getUrl();
        return newsLocal;
    }

    /**
     * convert date to TimeInMillis
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
    public List<NewsLocal> mapListToLocal(@NonNull List<NewsRemote> newsRemoteList) {
        List<NewsLocal> newsLocalList = new ArrayList<>();
        for (NewsRemote newsRemote : newsRemoteList){
            newsLocalList.add(mapToLocal(newsRemote));
        }
        return newsLocalList;
    }
}
