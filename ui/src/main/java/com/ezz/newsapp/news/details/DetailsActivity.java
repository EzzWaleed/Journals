package com.ezz.newsapp.news.details;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ezz.newsapp.databinding.ActivityDetailsBinding;
import com.ezz.newsapp.news.details.di.DaggerDetailsActivityComponent;
import com.ezz.newsapp.news.details.font_settings.FontSettings;
import com.ezz.newsapp.news.details.font_settings.FontSettingsBottomSheet;
import com.ezz.newsapp.news.details.font_settings.FontSettingsDataStore;
import com.ezz.newsapp.util.ShareUtil;
import com.ezz.presentation.model.NewsUI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.adapters.SeekBarBindingAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.ezz.newsapp.R;

import javax.inject.Inject;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener, FontSettingsBottomSheet.OnFontSettingsChanged{

    private static final String NEWS_KEY = "newsKey";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    NewsUI newsUI;

    @Inject
    FontSettingsDataStore fontSettingsDataStore;

    FontSettingsBottomSheet fontSettingsBottomSheet = new FontSettingsBottomSheet();

    private FontSettings fontSettings = new FontSettings();


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

        DaggerDetailsActivityComponent
        .builder()
        .bindContext(this)
        .build().inject(this);

        setFontSettings(fontSettingsDataStore);

        newsUI = getIntent().getParcelableExtra(NEWS_KEY);
        if (newsUI != null){
            binding.setClickListener(this);
            binding.setNews(newsUI);
            binding.setFontSettings(fontSettings);
        }
        else {
            throw new NullPointerException("NewsUI must passed via intent.");
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
            case R.id.font_btn:
                if (!fontSettingsBottomSheet.isAdded()){
                    fontSettingsBottomSheet.show(getSupportFragmentManager(), null);
                }
                break;
        }
    }

    private void setFontSettings(FontSettingsDataStore dataStore){
        fontSettings.setTextSizeBy(dataStore.getFontSize());
        fontSettings.textStyle.set(dataStore.isBoldStyle());
    }

    @Override
    public void onFontSizeChanged(int progress) {
        fontSettings.setTextSizeBy(progress);
        fontSettingsDataStore.setFontSize(progress);
    }

    @Override
    public void onFontStyleChanged(boolean isBold) {
        fontSettings.textStyle.set(isBold);
        fontSettingsDataStore.setIsBold(isBold);
    }

}
