package com.mobile.acaziarecruitment.data.api.network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable disposable) {
        addDisposableManager(disposable);
    }

    @Override
    public void onNext(T t) {
        if (t != null) {
            onResponse(t);
        } else {
            onFailure();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) e).response().errorBody();
            String message = "";
            int code = ((HttpException) e).response().code();
            String body;
            if (responseBody != null) {
                try {
                    body = responseBody.string();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        onFailure();
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onResponse(T t);

    protected abstract void onFailure();

    protected abstract void addDisposableManager(Disposable disposable);

}
