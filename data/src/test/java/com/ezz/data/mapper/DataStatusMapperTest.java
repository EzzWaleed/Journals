package com.ezz.data.mapper;

import com.ezz.data.mapper.impl.DataStatusMapperImpl;
import com.ezz.data.remote.client.SettingsAPI;
import com.ezz.data.remote.model.NewsResponse;
import com.ezz.domain.resource.DataStatus;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
public class DataStatusMapperTest {

	private int totalResult = 37;

	private DataStatusMapper dataStatusMapper;

	@Before
	public void setUp() {
		dataStatusMapper = new DataStatusMapperImpl();
	}

	@Test
	public void mapNewsResponseToDataStatus_withErrorResponse() {
		NewsResponse newsResponse = createNewsResponseWithErrorResponse();
		DataStatus dataStatus = dataStatusMapper.mapNewsResponseToDataStatus(newsResponse, 2);
		assertEquals(dataStatus, DataStatus.ERROR);
	}

	@Test
	public void mapNewsResponseToDataStatus_withSuccessResponse_And_NotLoadedAllItems() {
		NewsResponse newsResponse = createNewsResponseWithSuccessResponse();
		DataStatus dataStatus = dataStatusMapper.mapNewsResponseToDataStatus(newsResponse, 1);
		assertEquals(dataStatus, DataStatus.SUCCESS);
	}

	@Test
	public void mapNewsResponseToDataStatus_WithLoadedAllItems() {
		NewsResponse newsResponse = createNewsResponseWithSuccessResponse();
		DataStatus dataStatus = dataStatusMapper.mapNewsResponseToDataStatus(newsResponse, 4);
		assertEquals(dataStatus, DataStatus.HAS_LOADED_ALL_ITEMS);
	}



	private NewsResponse createNewsResponseWithErrorResponse(){
		NewsResponse newsResponse = new NewsResponse();
		newsResponse.setTotalResults(totalResult);
		newsResponse.setCode(SettingsAPI.NetworkCodes.apiKeyInvalid.name());
		newsResponse.setStatus(SettingsAPI.NetworkStatus.error.name());
		newsResponse.setNewsRemotes(new ArrayList<>());
		return newsResponse;
	}

	private NewsResponse createNewsResponseWithSuccessResponse(){
		NewsResponse newsResponse = new NewsResponse();
		newsResponse.setTotalResults(totalResult);
		newsResponse.setCode(null);
		newsResponse.setStatus(SettingsAPI.NetworkStatus.ok.name());
		newsResponse.setNewsRemotes(new ArrayList<>());
		return newsResponse;
	}

}