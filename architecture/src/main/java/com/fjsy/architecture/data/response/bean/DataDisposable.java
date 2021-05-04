package com.fjsy.architecture.data.response.bean;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public final class DataDisposable<T> implements Observer<T>, Disposable {
    private ModelLiveData<T> modelLiveData;
    private Disposable disposable;

    DataDisposable(ModelLiveData<T> modelLiveData) {
        this.modelLiveData = modelLiveData;
        if (modelLiveData == null) {
            throw new RuntimeException("modelLiveData can't be null!");
        }
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        ModelLiveData.LiveDataWrapper liveDataWrapper;
        try {
            this.disposable = disposable;
            liveDataWrapper = new ModelLiveData.LiveDataWrapper<T>()
                    .dataStart();
            this.modelLiveData.setValue(liveDataWrapper);
        } catch (Exception e){
            e.printStackTrace();
            liveDataWrapper = new ModelLiveData.LiveDataWrapper<T>()
                    .dataError(e);
            this.modelLiveData.setValue(liveDataWrapper);
        }
    }

    @Override
    public void onNext(T basicBean) {
        ModelLiveData.LiveDataWrapper liveDataWrapper;
        try {
            liveDataWrapper = new ModelLiveData.LiveDataWrapper<T>().dataStop();
            this.modelLiveData.setValue(liveDataWrapper);

            liveDataWrapper = new ModelLiveData.LiveDataWrapper<T>()
                    .dataSuccess(basicBean);
            this.modelLiveData.setValue(liveDataWrapper);
        } catch (Exception e){
            e.printStackTrace();
            liveDataWrapper = new ModelLiveData.LiveDataWrapper<T>()
                    .dataError(e);
            this.modelLiveData.setValue(liveDataWrapper);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        ModelLiveData.LiveDataWrapper liveDataWrapper;
        try {
            throwable.printStackTrace();

            liveDataWrapper = new ModelLiveData.LiveDataWrapper<T>().dataStop();
            this.modelLiveData.setValue(liveDataWrapper);

            liveDataWrapper = new ModelLiveData.LiveDataWrapper<T>()
                    .dataError(throwable);
            this.modelLiveData.setValue(liveDataWrapper);
        } catch (Exception e){
            e.printStackTrace();
            liveDataWrapper = new ModelLiveData.LiveDataWrapper<T>()
                    .dataError(e);
            this.modelLiveData.setValue(liveDataWrapper);
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void dispose() {
        if (this.disposable != null) {
            this.disposable.dispose();
        }
    }

    @Override
    public boolean isDisposed() {
        return this.disposable != null && this.disposable.isDisposed();
    }
}
