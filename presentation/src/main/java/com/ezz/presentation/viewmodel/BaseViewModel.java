package com.ezz.presentation.viewmodel;

import android.annotation.SuppressLint;

import androidx.core.util.Preconditions;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Ezz Waleed on 07,March,2019
 */
public class BaseViewModel extends ViewModel {

	private final Scheduler subscribeOn;
	private final Scheduler observeOn;
	private final CompositeDisposable disposables;

	public BaseViewModel(Scheduler subscribeOn, Scheduler observeOn) {
		this.subscribeOn = subscribeOn;
		this.observeOn = observeOn;
		this.disposables = new CompositeDisposable();
	}

	@SuppressLint("RestrictedApi")
	protected <T> void execute(Consumer<Disposable> loadingConsumer, Consumer<T> successConsumer, Consumer<Throwable> throwableConsumer, Observable<T> useCase) {
		Preconditions.checkNotNull(successConsumer);
		Preconditions.checkNotNull(throwableConsumer);
		final Observable<T> observable = useCase
		.doOnSubscribe(loadingConsumer)
		.subscribeOn(subscribeOn)
		.observeOn(observeOn);
		addDisposable(observable.subscribe(successConsumer, throwableConsumer));
	}

	/**
	 * Dispose from current {@link CompositeDisposable}.
	 */
	private void dispose() {
		if (!disposables.isDisposed()) {
			disposables.dispose();
		}
	}

	/**
	 * Dispose from current {@link CompositeDisposable}.
	 */
	@SuppressLint("RestrictedApi")
	private void addDisposable(Disposable disposable) {
		Preconditions.checkNotNull(disposable);
		Preconditions.checkNotNull(disposables);
		disposables.add(disposable);
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		dispose();
	}
}
