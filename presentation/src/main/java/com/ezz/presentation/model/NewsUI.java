package com.ezz.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Ezz Waleed on 07,March,2019
 */
public class NewsUI implements Parcelable {

	private String title;

	private String description;

	private String content;

	private String url;

	private String imageUrl;

	private Date publishedDate;

	private String sourceName;

	protected NewsUI(Parcel in) {
		title = in.readString();
		description = in.readString();
		content = in.readString();
		url = in.readString();
		imageUrl = in.readString();
		sourceName = in.readString();
	}

	public static final Creator<NewsUI> CREATOR = new Creator<NewsUI>() {
		@Override
		public NewsUI createFromParcel(Parcel in) {
			return new NewsUI(in);
		}

		@Override
		public NewsUI[] newArray(int size) {
			return new NewsUI[size];
		}
	};

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(description);
		dest.writeString(content);
		dest.writeString(url);
		dest.writeString(imageUrl);
		dest.writeString(sourceName);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NewsUI newsUI = (NewsUI) o;
		return Objects.equals(title, newsUI.title) &&
		Objects.equals(description, newsUI.description) &&
		Objects.equals(publishedDate, newsUI.publishedDate) &&
		Objects.equals(sourceName, newsUI.sourceName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, description, publishedDate, sourceName);
	}
}
