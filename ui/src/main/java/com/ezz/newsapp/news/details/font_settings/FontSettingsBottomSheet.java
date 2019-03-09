package com.ezz.newsapp.news.details.font_settings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ezz.newsapp.R;
import com.ezz.newsapp.databinding.BottomSheetFontSettingsBinding;
import com.ezz.newsapp.news.details.font_settings.di.DaggerFontSettingsSheetComponent;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ezz Waleed on 09,March,2019
 */

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FontSettingsBottomSheet.OnFontSettingsChanged} interface
 * to handle font settings changes.
 */
public class FontSettingsBottomSheet extends BottomSheetDialogFragment implements SeekBar.OnSeekBarChangeListener {

	private FontSettings fontSettings = new FontSettings();

	@Inject
	public FontSettingsDataStore fontSettingsDataStore;

	@BindView(R.id.text_size_seek_bar)
	SeekBar seekBar;

	private OnFontSettingsChanged mListener;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DaggerFontSettingsSheetComponent
		.builder()
		.bindContext(getContext())
		.build().inject(this);

		setFontSettings(fontSettingsDataStore);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		 BottomSheetFontSettingsBinding binding =
		 DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_font_settings, container, false);
		 binding.setFontSettings(fontSettings);
		 binding.setFontSettingsListener(mListener);
		 return binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
		seekBar.setOnSeekBarChangeListener(this);
		setFontSettings(fontSettingsDataStore);
	}

	private void setFontSettings(FontSettingsDataStore dataStore){
		fontSettings.setTextSizeBy(dataStore.getFontSize());
		fontSettings.textStyle.set(dataStore.isBoldStyle());
	}

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		if (context instanceof OnFontSettingsChanged) {
			mListener = (OnFontSettingsChanged) context;
		} else {
			throw new RuntimeException(context.toString()
			+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		mListener.onFontSizeChanged(progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 */
	public interface OnFontSettingsChanged {
		void onFontSizeChanged(int progress);

		void onFontStyleChanged(boolean isBold);
	}
}
