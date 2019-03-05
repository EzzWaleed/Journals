package com.ezz.data.maper;

import com.ezz.data.local.model.NewsLocal;
import com.ezz.data.remote.model.NewsRemote;
import com.ezz.domain.entity.NewsDomain;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Ezz Waleed on 05,March,2019
 */
public interface NewsMapper {

    /**
     * map {@link NewsLocal} to {@link NewsDomain}
     * @param newsLocal entity to be mapped
     * @return mapped entity
     */
    NewsDomain mapToDomain(@NonNull NewsLocal newsLocal);

    /**
     * map {@link NewsLocal} list to {@link NewsDomain} list.
     * @param newsLocalList list to be mapped.
     * @return mapped list.
     */
    List<NewsDomain> mapListToDomain(@NonNull List<NewsLocal> newsLocalList);

    /**
     * map {@link NewsRemote} to {@link NewsLocal}
     * @param newsRemote entity to be mapped
     * @return mapped entity
     */
    NewsLocal mapToLocal(@NonNull NewsRemote newsRemote);

    /**
     * map {@link NewsRemote} list to {@link NewsLocal} list.
     * @param newsRemoteList list to be mapped.
     * @return mapped list.
     */
    List<NewsLocal> mapListToLocal(@NonNull List<NewsRemote> newsRemoteList);

}
