package com.ezz.presentation.viewmodel.news;

import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.usecase.GetNewsUseCase;
import com.ezz.presentation.model.NewsUI;
import com.ezz.presentation.viewmodel.news.paging.PagingState;
import com.ezz.presentation.viewmodel.util.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.paging.PagedList;
import dagger.Module;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
@RunWith(JUnit4.class)
public class NewsViewModelTest {
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

	@Mock
	GetNewsUseCase<NewsUI> usecase;

	@Mock
	PagedList<NewsUI> pagedList;

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void test_create_paged_list_with_valid_output() throws InterruptedException {
		when(usecase.getNewsPagedList()).thenReturn(Observable.just(pagedList));
		NewsViewModel viewModel = new NewsViewModel(Schedulers.trampoline(), Schedulers.trampoline(), usecase, new PagingState());
		assertEquals(LiveDataTestUtil.getValue(viewModel.getNewsPagedListLiveData()), pagedList);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getPagingDataStatus()), DataStatus.SUCCESS);
	}

	@Test
	public void test_create_paged_list_with_invalid_output() throws InterruptedException {
		when(usecase.getNewsPagedList()).thenThrow(new NullPointerException(""));
		NewsViewModel viewModel = new NewsViewModel(Schedulers.trampoline(), Schedulers.trampoline(), usecase, new PagingState());
		assertEquals(LiveDataTestUtil.getValue(viewModel.getNewsPagedListLiveData()), null);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getPagingDataStatus()), DataStatus.ERROR);
	}

	@Test
	public void success_load_news() throws InterruptedException {
		when(usecase.getNewsPagedList()).thenReturn(Observable.just(pagedList));
		NewsViewModel viewModel = new NewsViewModel(Schedulers.trampoline(), Schedulers.trampoline(), usecase, new PagingState());
		when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.SUCCESS));
		viewModel.loadNews(1);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getLoadNewsStats()), DataStatus.SUCCESS);
	}

	@Test
	public void loading_news() throws InterruptedException {
		when(usecase.getNewsPagedList()).thenReturn(Observable.just(pagedList));
		NewsViewModel viewModel = new NewsViewModel(Schedulers.trampoline(), Schedulers.trampoline(), usecase, new PagingState());
		when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.LOADING));
		viewModel.loadNews(1);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getLoadNewsStats()), DataStatus.LOADING);
	}

	@Test
	public void error_load_news() throws InterruptedException {
		when(usecase.getNewsPagedList()).thenReturn(Observable.just(pagedList));
		NewsViewModel viewModel = new NewsViewModel(Schedulers.trampoline(), Schedulers.trampoline(), usecase, new PagingState());
		when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.ERROR));
		viewModel.loadNews(1);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getLoadNewsStats()), DataStatus.ERROR);
	}

	@Test
	public void all_news_items_loaded() throws InterruptedException {
		when(usecase.getNewsPagedList()).thenReturn(Observable.just(pagedList));
		NewsViewModel viewModel = new NewsViewModel(Schedulers.trampoline(), Schedulers.trampoline(), usecase, new PagingState());
		when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.HAS_LOADED_ALL_ITEMS));
		viewModel.loadNews(1);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getLoadNewsStats()), DataStatus.HAS_LOADED_ALL_ITEMS);
	}
}