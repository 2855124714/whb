package com.fjsy.architecture.data.response.bean;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

	private CompositeDisposable mCompositeDisposable;
	
	protected void registerDisposable(Disposable d) {
		if (this.mCompositeDisposable == null || this.mCompositeDisposable.isDisposed()) {
			this.mCompositeDisposable = new CompositeDisposable();
		}
		this.mCompositeDisposable.add(d);
	}
	
	@Override
	protected void onCleared() {
		super.onCleared();
		if (this.mCompositeDisposable != null) {
			this.mCompositeDisposable.dispose();
		}
	}
}
