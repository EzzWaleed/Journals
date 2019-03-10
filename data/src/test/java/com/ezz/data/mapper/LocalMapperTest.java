package com.ezz.data.mapper;

import com.ezz.data.local.model.NewsLocal;
import com.ezz.data.mapper.impl.LocalMapperImpl;
import com.ezz.domain.entity.NewsDomain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
public class LocalMapperTest {

	private String author, title, description, url, urlToImage, content, sourceName;
	private long time;

	private LocalMapper localMapper;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		initNewsFields();
		localMapper = new LocalMapperImpl();
	}

	@Test
	public void mapDomainToLocal() {
		NewsDomain newsDomain = createDummyNewsDomain();
		NewsLocal newsLocal = localMapper.mapDomainToLocal(newsDomain);
		assertEquals(newsLocal.authorName, author);
		assertEquals(newsLocal.content, content);
		assertEquals(newsLocal.description, description);
		assertEquals(newsLocal.imageUrl, urlToImage);
		assertEquals(newsLocal.publishedDate, time);
		assertEquals(newsLocal.url, url);
		assertEquals(newsLocal.sourceName, sourceName);
		assertEquals(newsLocal.title, title);
	}

	@Test
	public void mapDomain_EmptyList_ToLocal() {
		List<NewsDomain> newsDomainList = new ArrayList<>();
		List<NewsLocal> newsLocalList = localMapper.mapDomainListToLocal(newsDomainList);
		assertEquals(newsLocalList.size(), 0);
	}

	@Test
	public void mapDomainListToLocal() {
		List<NewsDomain> newsDomainList = new ArrayList<>();
		newsDomainList.add(createDummyNewsDomain());
		newsDomainList.add(createDummyNewsDomain());
		newsDomainList.add(createDummyNewsDomain());
		newsDomainList.add(createDummyNewsDomain());
		newsDomainList.add(createDummyNewsDomain());
		newsDomainList.add(createDummyNewsDomain());
		List<NewsLocal> newsLocalList = localMapper.mapDomainListToLocal(newsDomainList);
		assertEquals(newsLocalList.size(), newsLocalList.size());
	}

	private void initNewsFields(){
		author = "ezz";
		title = "title";
		description = "news description";
		url = "www.google.com";
		urlToImage = "google/image.jpg";
		content = "content";
		sourceName = "yahoo.com";
		time = 32332;
	}

	private NewsDomain createDummyNewsDomain(){
		NewsDomain newsDomain = new NewsDomain();
		newsDomain.setAuthorName(author);
		newsDomain.setContent(content);
		newsDomain.setDescription(description);
		newsDomain.setImageUrl(urlToImage);
		newsDomain.setPublishedDate(time);
		newsDomain.setSourceName(sourceName);
		newsDomain.setTitle(title);
		newsDomain.setUrl(url);
		return newsDomain;
	}

}