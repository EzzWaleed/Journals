package com.ezz.newsapp.news.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ezz.newsapp.databinding.ActivityDetailsBinding;
import com.ezz.newsapp.util.ShareUtil;
import com.ezz.presentation.model.NewsUI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.View;
import android.widget.ImageView;

import com.ezz.newsapp.R;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String NEWS_KEY = "newsKey";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    NewsUI newsUI;


    public static void startDetailsActivity(Activity activity, NewsUI newsUI, ImageView imageView){
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.putExtra(NEWS_KEY, newsUI);
        ActivityOptionsCompat options = ActivityOptionsCompat.
        makeSceneTransitionAnimation(activity, imageView, activity.getString(R.string.image_transition));
        activity.startActivity(intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        newsUI = getIntent().getParcelableExtra(NEWS_KEY);
        if (newsUI != null){
            binding.setClickListener(this);
            binding.setNews(newsUI);
        }
        else {
            throw new NullPointerException("NewsUI not found");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continue_website:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(newsUI.getUrl()));
                startActivity(i);
                break;
            case R.id.share_btn:
                ShareUtil.shareNews(this, newsUI);
                break;
        }
    }


}
