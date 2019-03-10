package com.ezz.data.mapper;

import com.ezz.data.remote.model.NewsResponse;
import com.ezz.domain.resource.DataStatus;

import androidx.annotation.NonNull;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
public interface DataStatusMapper {
	/**
	 * determine {@link NewsResponse} network status and return it as {@link DataStatus}
	 * @param newsResponse news network response
	 * @param pageNumber response page number
	 * @return mapping result
	 */
	DataStatus mapNewsRresponseToDataStatus(@NonNull NewsResponse newsResponse, @NonNull Integer pageNumber);
}
