package com.ezz.newsapp.news;

import android.os.Bundle;

import com.ezz.newsapp.App;
import com.ezz.newsapp.R;
import com.ezz.newsapp.news.adapter.NewsAdapter;
import com.ezz.newsapp.news.di.DaggerNewsScreenComponent;
import com.ezz.newsapp.paging.PagingCallbacksManger;
import com.ezz.presentation.viewmodel.news.NewsViewModel;
import com.ezz.presentation.viewmodel.viewmodel_factory.ViewModelFactory;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity implements PagingCallbacksManger.LoadMoreListener{

	@Inject
	NewsAdapter newsAdapter;

	@Inject
	PagingCallbacksManger pagingCallbacksManger;

	@Inject
	ViewModelFactory viewModelFactory;

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	NewsViewModel newsViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		ButterKnife.bind(this);

		DaggerNewsScreenComponent.builder()
		.loadMoreListener(this)
		.PresentationComponent(App.getPresentationComponent(this))
		.build().inject(this);

		newsViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel.class);

	}

	@Override
	public void onLoadMore() {

	}

}
