package com.ezz.newsapp.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ezz.newsapp.R;

import com.ezz.newsapp.databinding.ListItemNewsBinding;
import com.ezz.newsapp.util.ShareUtil;
import com.ezz.presentation.model.NewsUI;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Ezz Waleed on 08,March,2019
 */
public class NewsAdapter extends PagedListAdapter<NewsUI, NewsAdapter.NewsViewHolder> {

	@Nullable
	private NewsClickListener clickListener;

	@Inject
	public NewsAdapter() {
		super(DIFF_CALLBACK);
	}

	@NonNull
	@Override
	public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater =
		LayoutInflater.from(parent.getContext());
		ListItemNewsBinding itemBinding =
		ListItemNewsBinding.inflate(layoutInflater, parent, false);
		return new NewsViewHolder(itemBinding);
	}

	@Override
	public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
		if (getItem(position) != null)
			holder.bind();
	}

	class NewsViewHolder extends RecyclerView.ViewHolder implements ShareClickListener{

		private final ListItemNewsBinding listItemNewsNewBinding;

		@BindView(R.id.news_image)
		ImageView imageView;

		NewsViewHolder(@NonNull ListItemNewsBinding listItemNewsNewBinding) {
			super(listItemNewsNewBinding.getRoot());
			this.listItemNewsNewBinding = listItemNewsNewBinding;
			ButterKnife.bind(this, listItemNewsNewBinding.getRoot());
		}

		void bind(){
			NewsUI newsUI = getItem(getAdapterPosition());
			listItemNewsNewBinding.setNews(newsUI);
			listItemNewsNewBinding.setOnShareClick(this::onClick);
			listItemNewsNewBinding.executePendingBindings();
			itemView.setOnClickListener(v -> {
				if (clickListener != null) {
					clickListener.onItemClick(newsUI, imageView);
				}
			});
		}

		@Override
		public void onClick(NewsUI newsUI) {
			ShareUtil.shareNews(itemView.getContext(), newsUI);
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

	public void setClickListener(@Nullable NewsClickListener clickListener) {
		this.clickListener = clickListener;
	}

	public interface NewsClickListener{
		/**
		 * onItemClickListener invokes when news item clicked.
		 * @param newsUI clicked news item.
		 * @param imageView clicked news imageView.
		 */
		void onItemClick(NewsUI newsUI, ImageView imageView);
	}

	public interface ShareClickListener{
		void onClick(NewsUI newsUI);
	}
}
