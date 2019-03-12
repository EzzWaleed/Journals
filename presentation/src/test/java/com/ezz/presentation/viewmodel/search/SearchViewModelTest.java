package com.ezz.presentation.viewmodel.search;

import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.usecase.GetNewsUseCase;
import com.ezz.domain.usecase.SearchNewsUseCase;
import com.ezz.presentation.model.NewsUI;
import com.ezz.presentation.viewmodel.news.NewsViewModel;
import com.ezz.presentation.viewmodel.news.paging.PagingState;
import com.ezz.presentation.viewmodel.util.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.paging.PagedList;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Ezz Waleed on 12,March,2019
 */
public class SearchViewModelTest {

	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

	@Mock
	SearchNewsUseCase<NewsUI> usecase;

	@Mock
	PagedList<NewsUI> pagedList;

	private SearchViewModel viewModel;

	private String query = "query";

	@Before
	public void setUp() {
		initMocks(this);
		viewModel = new SearchViewModel(Schedulers.trampoline(), Schedulers.trampoline(), usecase);
	}

	@Test
	public void test_create_paged_list_with_success() throws InterruptedException {
		when(usecase.getSearchPagedList(query)).thenReturn(Observable.just(pagedList));
		viewModel.searchFor(query);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getNewsLiveData()), pagedList);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getPagingDataStatus()), DataStatus.SUCCESS);
	}

	@Test
	public void test_create_paged_list_with_loading() throws InterruptedException {
		Observable<PagedList<NewsUI>> observable = Observable.empty();
		when(usecase.getSearchPagedList(query)).thenReturn(observable);
		viewModel.searchFor(query);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getPagingDataStatus()), DataStatus.LOADING);
		assertNull(LiveDataTestUtil.getValue(viewModel.getNewsLiveData()));
	}

	@Test
	public void test_create_paged_list_with_error() throws InterruptedException {
		Observable<PagedList<NewsUI>> observable = Observable.create(emitter -> emitter.onError(new NullPointerException()));
		when(usecase.getSearchPagedList(query)).thenReturn(observable);
		viewModel.searchFor(query);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getPagingDataStatus()), DataStatus.ERROR);
		assertNull(LiveDataTestUtil.getValue(viewModel.getNewsLiveData()));
	}

	@Test
	public void verify_getSearchPagedList_called_only_once_time() {
		when(usecase.getSearchPagedList(query)).thenReturn(Observable.just(pagedList));
		verify(usecase, times(0)).getSearchPagedList(query);
		viewModel.searchFor(query);
		verify(usecase, times(1)).getSearchPagedList(query);
		viewModel.searchFor(query);
		verify(usecase, times(1)).getSearchPagedList(query);
	}

	@Test
	public void verify_updateSearchQuery_not_called_in_first_time(){
		when(usecase.getSearchPagedList(query)).thenReturn(Observable.just(pagedList));
		viewModel.searchFor(query);
		verify(usecase, times(0)).updateSearchQuery(query);
	}

	@Test
	public void verify_updateSearchQuery_called_after_first_time() {
		when(usecase.getSearchPagedList(query)).thenReturn(Observable.just(pagedList));
		viewModel.searchFor(query);
		viewModel.searchFor(query);
		verify(usecase, times(1)).updateSearchQuery(query);
		viewModel.searchFor(query);
		verify(usecase, times(2)).updateSearchQuery(query);
		viewModel.searchFor(query);
		verify(usecase, times(3)).updateSearchQuery(query);
	}
}