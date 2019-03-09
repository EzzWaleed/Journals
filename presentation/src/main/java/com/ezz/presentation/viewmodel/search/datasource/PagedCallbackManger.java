package com.ezz.presentation.viewmodel.search.datasource;

import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.resource.Resource;
import com.ezz.presentation.model.NewsUI;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.paging.PageKeyedDataSource;

/**
 * Created by Ezz Waleed on 09,March,2019
 */

/**
 * Generate results for PageKeyedDataSource Callbacks according the data resource status
 */
public class PagedCallbackManger {

	private boolean hasLoadedAllItems = false;


	public  PagedCallbackHandler createPagedCallbackHandler(Resource<List<NewsUI>> resource, @Nullable PageKeyedDataSource.LoadParams loadParams){
		return new PagedCallbackHandler(resource, loadParams);
	}

	public boolean hasLoadedAllItems() {
		return hasLoadedAllItems;
	}

	public class PagedCallbackHandler{

		private Resource<List<NewsUI>> resource;

		@Nullable
		private
		PageKeyedDataSource.LoadParams<Integer> loadParams;

		private PagedCallbackHandler(Resource<List<NewsUI>> resource, @Nullable PageKeyedDataSource.LoadParams loadParams) {
			this.resource = resource;
			this.loadParams = loadParams;
		}

		public Integer getNextPaggedKey(){
			if (loadParams != null) {
				return loadParams.key + 1;
			}else {
				return 2;
			}
		}

		public Integer getPreviousPaggedKey(){
			if (loadParams != null) {
				return loadParams.key + 1;
			}else {
				return null;
			}
		}

		public List<NewsUI> getData(){
			switch (resource.status){
				case SUCCESS:
					return resource.data;
				case HAS_LOADED_ALL_ITEMS:
					hasLoadedAllItems = true;
					if (resource.data != null){
						return resource.data;
					}
					else {
						return new ArrayList<>();
					}
				case ERROR:
					return new ArrayList<>();
			}
			return new ArrayList<>();
		}
	}
}
