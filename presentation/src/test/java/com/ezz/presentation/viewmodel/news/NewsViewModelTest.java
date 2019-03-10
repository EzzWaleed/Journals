package com.ezz.presentation.viewmodel.news;

import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.usecase.GetNewsUsecase;
import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.viewmodel.news.paging.PagingKeeper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ezz Waleed on 10,March,2019
 */
public class NewsViewModelTest {

	@Mock
	NewsMapper newsMapper;

	@Mock
	GetNewsUsecase usecase;

	private PagingKeeper pagingKeeper;

	private NewsViewModel viewModel;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		pagingKeeper = new PagingKeeper();
		viewModel = new NewsViewModel(Schedulers.trampoline(), Schedulers.trampoline(), usecase, newsMapper, pagingKeeper);
	}

	@Test
	public void loadNews() {
		Mockito.when(usecase.loadNews(1)).thenReturn(Observable.just(DataStatus.SUCCESS));
		Mockito.verify(viewModel.loadNewsStats).setValue(DataStatus.SUCCESS);
	}
}