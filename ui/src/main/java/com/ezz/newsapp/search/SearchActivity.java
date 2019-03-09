package com.ezz.newsapp.search;

import android.os.Bundle;

import com.ezz.newsapp.App;
import com.ezz.newsapp.R;
import com.ezz.newsapp.news.adapter.NewsAdapter;
import com.ezz.newsapp.search.di.DaggerSearchScreenComponent;
import com.ezz.presentation.viewmodel.search.SearchViewModel;
import com.ezz.presentation.viewmodel.viewmodel_factory.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.paginate.Paginate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.View;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

	@BindView(R.id.search_view)
	SearchView searchView;

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@BindView(R.id.news_recycler_view)
	RecyclerView recyclerView;

	@Inject
	ViewModelFactory viewModelFactory;

	@Inject
	NewsAdapter newsAdapter;

	SearchViewModel searchViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setSupportActionBar(toolbar);

		ButterKnife.bind(this);

		DaggerSearchScreenComponent.builder().
		presentationComponent(App.getPresentationComponent(this))
		.build().inject(this);

		searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel.class);

		searchViewModel.newsLiveData.observe(this, newsUIPagedList -> newsAdapter.submitList(newsUIPagedList));

		recyclerView.setAdapter(newsAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		searchView.setOnQueryTextListener(this);
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		searchViewModel.searchFor(query);
		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		return false;
	}

}
