package com.ezz.presentation.viewmodel.news;

import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.usecase.GetNewsUsecase;
import com.ezz.presentation.mapper.NewsMapper;
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
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import static org.junit.Assert.*;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
@RunWith(JUnit4.class)
public class NewsViewModelTest {
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

	@Mock
	NewsMapper newsMapper;


	@Mock
	GetNewsUsecase usecase;

	private NewsViewModel viewModel;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		viewModel = new NewsViewModel(Schedulers.trampoline(), Schedulers.trampoline(), usecase, newsMapper, new PagingKeeper());
	}

	@Test
	public void success_load_news() throws InterruptedException {
		Mockito.when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.SUCCESS));
		viewModel.loadNews(1);
		assertEquals(LiveDataTestUtil.getValue(viewModel.loadNewsStats), DataStatus.SUCCESS);
	}

	@Test
	public void loading_news() throws InterruptedException {
		Mockito.when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.LOADING));
		viewModel.loadNews(1);
		assertEquals(LiveDataTestUtil.getValue(viewModel.loadNewsStats), DataStatus.LOADING);
	}

	@Test
	public void error_load_news() throws InterruptedException {
		Mockito.when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.ERROR));
		viewModel.loadNews(1);
		assertEquals(LiveDataTestUtil.getValue(viewModel.loadNewsStats), DataStatus.ERROR);
	}

	@Test
	public void all_news_items_loaded() throws InterruptedException {
		Mockito.when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.HAS_LOADED_ALL_ITEMS));
		viewModel.loadNews(1);
		assertEquals(LiveDataTestUtil.getValue(viewModel.loadNewsStats), DataStatus.HAS_LOADED_ALL_ITEMS);
	}
}