package com.ezz.data.mapper;

import com.ezz.data.local.model.NewsLocal;
import com.ezz.data.mapper.impl.DomainMapperImpl;
import com.ezz.data.remote.model.NewsRemote;
import com.ezz.data.remote.model.SourceRemote;
import com.ezz.domain.entity.NewsDomain;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
public class DomainMapperTest {

	private String author, title, description, url, urlToImage, publishedAt, content, contentWithExtraData, sourceName, sourceId;
	private long time;

	private DomainMapper domainMapper;

	@Before
	public void setUp() {
		initNewsFields();
		domainMapper = new DomainMapperImpl();
	}

	@Test
	public void mapLocalToDomain() {
		NewsLocal newsLocal = createDummyNewsLocal();
		NewsDomain newsDomain = domainMapper.mapLocalToDomain(newsLocal);
		assertEquals(newsDomain.getPublishedDate(), time);
		assertEquals(newsDomain.getImageUrl(), urlToImage);
		assertEquals(newsDomain.getDescription(), description);
		assertEquals(newsDomain.getContent(), content);
		assertEquals(newsDomain.getAuthorName(), author);
		assertEquals(newsDomain.getSourceName(), sourceName);
		assertEquals(newsDomain.getTitle(), title);
		assertEquals(newsDomain.getUrl(), url);
	}

	@Test
	public void mapRemoteToDomain_withNormalContent() {
		NewsRemote newsRemote = createDummyNewsRemote();
		NewsDomain newsDomain = domainMapper.mapRemoteToDomain(newsRemote);
		assertEquals(newsDomain.getPublishedDate(), time);
		assertEquals(newsDomain.getImageUrl(), urlToImage);
		assertEquals(newsDomain.getDescription(), description);
		assertEquals(newsDomain.getContent(), content);
		assertEquals(newsDomain.getAuthorName(), author);
		assertEquals(newsDomain.getSourceName(), sourceName);
		assertEquals(newsDomain.getTitle(), title);
		assertEquals(newsDomain.getUrl(), url);
	}


	@Test
	public void mapRemoteToDomain_withExtraContent() {
		NewsRemote newsRemote = createDummyNewsRemoteWithExtraContent();
		NewsDomain newsDomain = domainMapper.mapRemoteToDomain(newsRemote);
		assertEquals(newsDomain.getPublishedDate(), time);
		assertEquals(newsDomain.getImageUrl(), urlToImage);
		assertEquals(newsDomain.getDescription(), description);
		assertEquals(newsDomain.getContent(), content + "…");
		assertEquals(newsDomain.getAuthorName(), author);
		assertEquals(newsDomain.getSourceName(), sourceName);
		assertEquals(newsDomain.getTitle(), title);
		assertEquals(newsDomain.getUrl(), url);
	}

	@Test
	public void mapLocalListToDomain() {
		List<NewsLocal> newsLocalList = new ArrayList<>();
		newsLocalList.add(createDummyNewsLocal());
		newsLocalList.add(createDummyNewsLocal());
		newsLocalList.add(createDummyNewsLocal());
		newsLocalList.add(createDummyNewsLocal());
		newsLocalList.add(createDummyNewsLocal());
		newsLocalList.add(createDummyNewsLocal());
		List<NewsDomain> newsDomainList = domainMapper.mapLocalListToDomain(newsLocalList);
		assertEquals(newsDomainList.size(), newsLocalList.size());
	}

	@Test
	public void mapLocal_WithEmptyList_ToDomain() {
		List<NewsLocal> newsLocalList = new ArrayList<>();
		List<NewsDomain> newsDomainList = domainMapper.mapLocalListToDomain(newsLocalList);
		assertEquals(newsDomainList.size(), 0);
	}

	@Test
	public void mapRemoteListToDomain() {
		List<NewsRemote> newsRemoteList = new ArrayList<>();
		newsRemoteList.add(createDummyNewsRemote());
		newsRemoteList.add(createDummyNewsRemote());
		newsRemoteList.add(createDummyNewsRemote());
		newsRemoteList.add(createDummyNewsRemote());
		newsRemoteList.add(createDummyNewsRemote());
		newsRemoteList.add(createDummyNewsRemote());
		List<NewsDomain> newsDomainList = domainMapper.mapRemoteListToDomain(newsRemoteList);
		assertEquals(newsDomainList.size(), newsRemoteList.size());
	}

	@Test
	public void mapRemote_WithEmptyList_ToDomain() {
		List<NewsRemote> newsRemoteList = new ArrayList<>();
		List<NewsDomain> newsDomainList = domainMapper.mapRemoteListToDomain(newsRemoteList);
		assertEquals(newsDomainList.size(), 0);
	}


	private void initNewsFields(){
		author = "ezz";
		title = "title";
		description = "news description";
		url = "www.google.com";
		urlToImage = "google/image.jpg";
		content = "lknlkkmlml";
		contentWithExtraData = content + "…extraContentBalbalbla[+50]";
		sourceName = "yahoo.com";
		sourceId = "21";
		publishedAt = "2019-02-25T09:00:52Z";
		time = Long.parseLong("1551085252000");
	}


	private NewsRemote createDummyNewsRemote(){
		NewsRemote newsRemote = new NewsRemote();
		newsRemote.setPublishedAt(publishedAt);
		newsRemote.setDescription(description);
		newsRemote.setContent(content);
		newsRemote.setAuthor(author);
		SourceRemote sourceRemote = new SourceRemote();
		sourceRemote.setId(sourceId);
		sourceRemote.setName(sourceName);
		newsRemote.setSourceRemote(sourceRemote);
		newsRemote.setTitle(title);
		newsRemote.setUrl(url);
		newsRemote.setUrlToImage(urlToImage);
		return newsRemote;
	}

	private NewsRemote createDummyNewsRemoteWithExtraContent(){
		NewsRemote newsRemote = new NewsRemote();
		newsRemote.setPublishedAt(publishedAt);
		newsRemote.setDescription(description);
		newsRemote.setContent(contentWithExtraData);
		newsRemote.setAuthor(author);
		SourceRemote sourceRemote = new SourceRemote();
		sourceRemote.setId(sourceId);
		sourceRemote.setName(sourceName);
		newsRemote.setSourceRemote(sourceRemote);
		newsRemote.setTitle(title);
		newsRemote.setUrl(url);
		newsRemote.setUrlToImage(urlToImage);
		return newsRemote;
	}

	private NewsLocal createDummyNewsLocal(){
		NewsLocal newsLocal = new NewsLocal();
		newsLocal.authorName = author;
		newsLocal.content = content;
		newsLocal.description = description;
		newsLocal.imageUrl = urlToImage;
		newsLocal.publishedDate = time;
		newsLocal.sourceName = sourceName;
		newsLocal.title = title;
		newsLocal.url = url;
		return newsLocal;
	}
}