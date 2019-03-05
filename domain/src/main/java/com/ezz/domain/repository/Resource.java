package com.ezz.domain.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Ezz Waleed on 06,March,2019
 */

/**
 * A generic class that contains data and status about loading this data.
 * @param <T> data object type
 */
public class Resource<T> {
	@NonNull public final Status status;
	@Nullable public final T data;
	@Nullable public final String message;
	public final boolean hasLoadedAllItems;
	private Resource(@NonNull Status status, @Nullable T data,
					 @Nullable String message, boolean hasLoadedAllItems) {
		this.status = status;
		this.data = data;
		this.message = message;
		this.hasLoadedAllItems = hasLoadedAllItems;
	}

	public static <T> Resource<T> success(@NonNull T data, boolean hasLoadedAllItems) {
		return new Resource<>(Status.SUCCESS, data, null, hasLoadedAllItems);
	}

	public static <T> Resource<T> error(String msg, boolean hasLoadedAllItems) {
		return new Resource<>(Status.ERROR, null, msg, hasLoadedAllItems);
	}

	public static <T> Resource<T> loading() {
		return new Resource<>(Status.LOADING, null, null, false);
	}

	public enum Status { SUCCESS, ERROR, LOADING }
}
