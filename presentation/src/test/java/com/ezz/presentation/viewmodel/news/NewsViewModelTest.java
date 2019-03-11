package com.ezz.presentation.viewmodel.news;

import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.usecase.GetNewsUseCase;
import com.ezz.presentation.model.NewsUI;
import com.ezz.presentation.viewmodel.news.paging.PagingKeeper;
import com.ezz.presentation.viewmodel.util.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
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

	private NewsViewModel viewModel;

	@Before
	public void setUp() {
		initMocks(this);
		viewModel = new NewsViewModel(Schedulers.trampoline(), Schedulers.trampoline(), usecase, new PagingKeeper());
	}

	@Test
	public void success_load_news() throws InterruptedException {
		when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.SUCCESS));
		viewModel.loadNews(1);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getLoadNewsStats()), DataStatus.SUCCESS);
	}

	@Test
	public void loading_news() throws InterruptedException {
		when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.LOADING));
		viewModel.loadNews(1);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getLoadNewsStats()), DataStatus.LOADING);
	}

	@Test
	public void error_load_news() throws InterruptedException {
		when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.ERROR));
		viewModel.loadNews(1);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getLoadNewsStats()), DataStatus.ERROR);
	}

	@Test
	public void all_news_items_loaded() throws InterruptedException {
		when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.HAS_LOADED_ALL_ITEMS));
		viewModel.loadNews(1);
		assertEquals(LiveDataTestUtil.getValue(viewModel.getLoadNewsStats()), DataStatus.HAS_LOADED_ALL_ITEMS);
	}
}