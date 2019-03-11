package com.ezz.presentation.mapper;

import com.ezz.domain.entity.NewsDomain;
import com.ezz.presentation.model.NewsUI;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Ezz Waleed on 11,March,2019
 */
public class NewsMapperTest {

	private String author, title, description, url, urlToImage, content,  sourceName;
	private long time;

	private NewsMapper newsMapper;

	@Before
	public void setUp() {
		initNewsFields();
		newsMapper = new NewsMapperImpl();
	}

	@Test
	public void mapToUI() {
		NewsDomain newsDomain = createDummyNewsDomain();
		NewsUI newsUI = newsMapper.mapToUI(newsDomain);
		assertEquals(newsUI.getContent(), content);
		assertEquals(newsUI.getPublishedDate(), new Date(time));
		assertEquals(newsUI.getDescription(), description);
		assertEquals(newsUI.getImageUrl(), urlToImage);
		assertEquals(newsUI.getSourceName(), sourceName);
		assertEquals(newsUI.getTitle(), title);
		assertEquals(newsUI.getUrl(), url);
	}

	private void initNewsFields(){
		author = "ezz";
		title = "title";
		description = "news description";
		url = "www.google.com";
		urlToImage = "google/image.jpg";
		content = "content";
		sourceName = "yahoo.com";
		time = Long.parseLong("1551085252000");
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