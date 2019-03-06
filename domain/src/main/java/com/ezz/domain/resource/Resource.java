package com.ezz.domain.resource;

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

	@NonNull public final DataStatus status;
	@Nullable public final T data;
	@Nullable public final String message;

	private Resource(@NonNull DataStatus status, @Nullable T data,
					 @Nullable String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public static <T> Resource<T> success(@NonNull T data) {
		return new Resource<>(DataStatus.SUCCESS, data, null);
	}

	public static <T> Resource<T> error(String msg) {
		return new Resource<>(DataStatus.ERROR, null, msg);
	}

	public static <T> Resource<T> loading() {
		return new Resource<>(DataStatus.LOADING, null, null);
	}

	public static <T> Resource<T> hasLoadedAllItems() {
		return new Resource<>(DataStatus.HAS_LOADED_ALL_ITEMS, null, null);
	}

}
