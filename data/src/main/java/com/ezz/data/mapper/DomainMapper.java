package com.ezz.data.mapper;

import com.ezz.data.local.model.NewsLocal;
import com.ezz.data.remote.model.NewsRemote;
import com.ezz.domain.entity.NewsDomain;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
public interface DomainMapper {

	/**
	 * map {@link NewsLocal} to {@link NewsDomain}
	 * @param newsLocal entity to be mapped
	 * @return mapped entity
	 */
	NewsDomain mapLocalToDomain(@NonNull NewsLocal newsLocal);

	/**
	 * map {@link NewsRemote} to {@link NewsDomain}
	 * @param newsRemote entity to be mapped
	 * @return mapped entity
	 */
	NewsDomain mapRemoteToDomain(@NonNull NewsRemote newsRemote);

	/**
	 * map {@link NewsLocal} list to {@link NewsDomain} list.
	 * @param newsLocalList list to be mapped.
	 * @return mapped list.
	 */
	List<NewsDomain> mapLocalListToDomain(@NonNull List<NewsLocal> newsLocalList);

	/**
	 * map {@link NewsRemote} list to {@link NewsDomain} list.
	 * @param newsRemoteList list to be mapped.
	 * @return mapped list.
	 */
	List<NewsDomain> mapRemoteListToDomain(@NonNull List<NewsRemote> newsRemoteList);


}
