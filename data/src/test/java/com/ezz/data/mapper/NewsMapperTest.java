package com.ezz.data.mapper;

import com.ezz.data.local.model.NewsLocal;
import com.ezz.data.remote.client.SettingsAPI;
import com.ezz.data.remote.model.NewsRemote;
import com.ezz.data.remote.model.NewsResponse;
import com.ezz.data.remote.model.SourceRemote;
import com.ezz.domain.entity.NewsDomain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
public class NewsMapperTest {


	private String author, title, description, url, urlToImage, publishedAt, content, sourceName, sourceId;
	private long time;


	NewsRemote newsRemote;

	NewsResponse newsResponse;

	NewsDomain newsDomain;

	NewsLocal newsLocal;

	@Mock
	NewsMapper newsMapper;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		initNewsFields();
		createDummyNewsDomain();
		createDummyNewsLocal();
		createDummyNewsRemote();
	}

	@Test
	public void mapToDomain() {

	}

	@Test
	public void mapToDomainFromRemote() {
	}

	@Test
	public void mapLocalListToDomain() {
	}

	@Test
	public void mapRemoteListToDomain() {
	}

	@Test
	public void mapToLocal() {
	}

	@Test
	public void mapToLocal1() {
	}

	@Test
	public void mapRemoteListToLocal() {
	}

	@Test
	public void mapDomainListToLocal() {
	}

	@Test
	public void mapToDataStatus() {
	}

	@Test
	public void mapToNewsDomainResource() {
	}

	private void initNewsFields(){
		author = any();
		title = any();
		description = any();
		url = any();
		urlToImage = any();
		publishedAt = "2019-02-25T09:00:52Z";
		content = any();
		sourceName = any();
		sourceId = any();
		time = Long.parseLong("1551085252000");
	}


	private void createDummyNewsRemote(){
		newsRemote = new NewsRemote();
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
	}

	private void createDummyNewsLocal(){
		NewsLocal newsLocal = new NewsLocal();
		newsLocal.authorName = author;
		newsLocal.content = content;
		newsLocal.description = description;
		newsLocal.imageUrl = urlToImage;
		newsLocal.publishedDate = time;
		newsLocal.sourceName = sourceName;
		newsLocal.title = title;
		newsLocal.url = url;
	}

	private void createDummyNewsDomain(){
		newsDomain = new NewsDomain();
		newsDomain.setAuthorName(author);
		newsDomain.setContent(content);
		newsDomain.setDescription(description);
		newsDomain.setImageUrl(urlToImage);
		newsDomain.setPublishedDate(time);
		newsDomain.setSourceName(sourceName);
		newsDomain.setTitle(title);
		newsDomain.setUrl(url);
	}

}