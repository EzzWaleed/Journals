package com.ezz.newsapp.news;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ezz.newsapp.App;
import com.ezz.newsapp.R;
import com.ezz.newsapp.news.adapter.NewsAdapter;
import com.ezz.newsapp.news.details.DetailsActivity;
import com.ezz.newsapp.news.di.DaggerNewsScreenComponent;
import com.ezz.newsapp.news.paging.PagingManger;
import com.ezz.newsapp.search.SearchActivity;

import com.ezz.presentation.viewmodel.news.NewsViewModel;

import com.ezz.presentation.viewmodel.viewmodel_factory.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;
import com.paginate.Paginate;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.ViewModelProviders;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity implements PagingManger.LoadMoreListener, SearchView.OnQueryTextListener{

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

	SearchView searchView;

	NewsViewModel newsViewModel;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);

		DaggerNewsScreenComponent.builder()
		.loadMoreListener(this)
		.PresentationComponent(App.getPresentationComponent(this))
		.build().inject(this);

		newsViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel.class);

		newsViewModel.loadNewsStats.observe(this, dataStatus -> {
			switch (dataStatus){
				case SUCCESS:
					pagingManger.incrementPageNumber();
					break;
				case ERROR:
					pagingManger.setLoadedAllItems(true);
					Snackbar.make(recyclerView, getString(R.string.error_occured_message), Snackbar.LENGTH_INDEFINITE)
					.setAction("Retry", v -> {
						loadNext();
						pagingManger.setLoadedAllItems(false);
					}).show();
					break;
				case HAS_LOADED_ALL_ITEMS:
					pagingManger.setLoadedAllItems(true);
					break;
				case LOADING:
					return;
			}
			pagingManger.setLoading(false);
		});


		newsViewModel.newsPagedListLiveData.observe(this, newsUIPagedList -> newsAdapter.submitList(newsUIPagedList));

		recyclerView.setAdapter(newsAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		pagingManger.setPagingKeeper(newsViewModel.getPagingKeeper());
		Paginate.with(recyclerView, pagingManger).addLoadingListItem(false).setLoadingTriggerThreshold(50).build();

		newsAdapter.setClickListener((newsUI, imageView) ->
		DetailsActivity.startDetailsActivity(this, newsUI, imageView));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		setupSearchView(menu);
		return true;
	}

	private void setupSearchView(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setOnQueryTextListener(this);
	}

	@Override
	public void onLoadMore() {
		loadNext();
	}

	private void loadNext() {
		pagingManger.setLoading(true);
		newsViewModel.loadNews(pagingManger.getPageNumber());
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		startSearchActivity(query);
		clearSearchView();
		return true;
	}

	private void clearSearchView() {
		searchView.setQuery("", false);
		searchView.clearFocus();
		searchView.setIconified(true);
		toolbar.getMenu().findItem(R.id.action_search).collapseActionView();
	}

	private void startSearchActivity(String query) {
		Intent intent = new Intent(this, SearchActivity.class);
		intent.putExtra(SearchActivity.SEARCH_QUERY_KEY, query);
		startActivity(intent);
	}



	@Override
	public boolean onQueryTextChange(String newText) {
		return false;
	}
}
