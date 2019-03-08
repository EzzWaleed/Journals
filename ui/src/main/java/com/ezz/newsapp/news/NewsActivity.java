package com.ezz.newsapp.news;

import android.os.Bundle;

import com.ezz.newsapp.App;
import com.ezz.newsapp.R;
import com.ezz.newsapp.news.adapter.NewsAdapter;
import com.ezz.newsapp.news.di.DaggerNewsScreenComponent;
import com.ezz.newsapp.paging.PagingManger;
import com.ezz.presentation.viewmodel.news.NewsViewModel;
import com.ezz.presentation.viewmodel.viewmodel_factory.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;
import com.paginate.Paginate;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity implements PagingManger.LoadMoreListener{

	@Inject
	NewsAdapter newsAdapter;

	@Inject
	PagingManger pagingManger;

	@Inject
	ViewModelFactory viewModelFactory;

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.news_recycler_view)
	RecyclerView recyclerView;

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

		newsViewModel.newsPagedListLiveData.observe(this, newsUIS -> newsAdapter.submitList(newsUIS));

		newsViewModel.loadNewsStats.observe(this, dataStatus -> {
			switch (dataStatus){
				case SUCCESS:
					pagingManger.incrementPageNumber();
					break;
				case ERROR:
					pagingManger.setLoadedAllItems(true);
					Snackbar.make(recyclerView, getString(R.string.error_occured_message), Snackbar.LENGTH_LONG)
					.setAction("Retry", v -> pagingManger.setLoadedAllItems(false)).show();
					break;
				case HAS_LOADED_ALL_ITEMS:
					pagingManger.setLoadedAllItems(true);
					break;
			}
			pagingManger.setLoading(false);
		});

		recyclerView.setAdapter(newsAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		Paginate.with(recyclerView, pagingManger).build();

	}

	@Override
	public void onLoadMore() {
		pagingManger.setLoading(true);
		newsViewModel.loadNews(pagingManger.getPageNumber());
	}

}
