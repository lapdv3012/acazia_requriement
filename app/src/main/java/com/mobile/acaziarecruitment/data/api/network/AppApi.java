package com.mobile.acaziarecruitment.data.api.network;

import com.mobile.acaziarecruitment.data.api.connect.ResultService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppApi {
    protected static <M> void addObservable(Observable<M> observable, Observer<M> subscription) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscription);
    }

    public static ResultService resultService() {
        return AppClient.createService(ResultService.class);
    }
}
