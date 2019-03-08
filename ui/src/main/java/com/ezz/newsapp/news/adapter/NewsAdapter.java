package com.ezz.newsapp.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezz.newsapp.BR;
import com.ezz.newsapp.R;
import com.ezz.newsapp.databinding.ListItemNewsBinding;
import com.ezz.presentation.model.NewsUI;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
public class NewsAdapter extends PagedListAdapter<NewsUI, NewsAdapter.NewsViewHolder> {

	@Inject
	public NewsAdapter() {
		super(DIFF_CALLBACK);
	}

	@NonNull
	@Override
	public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater =
		LayoutInflater.from(parent.getContext());
		ViewDataBinding binding = DataBindingUtil.inflate(
		layoutInflater, viewType, parent, false);
		return new NewsViewHolder((ListItemNewsBinding) binding);	}

	@Override
	public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
		if (getItem(position) != null)
			holder.bind();
	}

	class NewsViewHolder extends RecyclerView.ViewHolder {

		private final ListItemNewsBinding listItemNewsBinding;

		public NewsViewHolder(@NonNull ListItemNewsBinding listItemNewsBinding) {
			super(listItemNewsBinding.getRoot());
			this.listItemNewsBinding = listItemNewsBinding;
		}

		void bind(){
			NewsUI newsUI = getItem(getAdapterPosition());
			listItemNewsBinding.setNews(newsUI);
			listItemNewsBinding.executePendingBindings();
		}
	}

	private static DiffUtil.ItemCallback<NewsUI> DIFF_CALLBACK = new DiffUtil.ItemCallback<NewsUI>() {
		@Override
		public boolean areItemsTheSame(@NonNull NewsUI oldItem, @NonNull NewsUI newItem) {
			return oldItem.getTitle().equals(newItem.getTitle());
		}

		@Override
		public boolean areContentsTheSame(@NonNull NewsUI oldItem, @NonNull NewsUI newItem) {
			return oldItem.equals(newItem);
		}
	};
}
