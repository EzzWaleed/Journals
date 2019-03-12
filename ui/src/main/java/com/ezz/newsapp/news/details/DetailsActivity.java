package com.ezz.newsapp.news.details;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startActivity(intent, options.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreenMode();
        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        ButterKnife.bind(this);



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

    /**
     * Enters full screen mode.
     */
    private void fullScreenMode() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
