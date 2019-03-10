package com.ezz.data.mapper.impl;

import com.ezz.data.mapper.DataStatusMapper;
import com.ezz.data.remote.client.SettingsAPI;
import com.ezz.data.remote.model.NewsResponse;
import com.ezz.domain.resource.DataStatus;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
public class DataStatusMapperImpl implements DataStatusMapper {

	@Inject
	public DataStatusMapperImpl() {
	}

	@Override
	public DataStatus mapNewsResponseToDataStatus(@NonNull NewsResponse newsResponse, @NonNull Integer pageNumber) {
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

}
