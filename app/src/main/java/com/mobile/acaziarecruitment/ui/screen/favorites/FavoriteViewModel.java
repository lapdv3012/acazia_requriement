package com.mobile.acaziarecruitment.ui.screen.favorites;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobile.acaziarecruitment.data.api.network.BaseObserver;
import com.mobile.acaziarecruitment.data.local.ResultLocal;
import com.mobile.acaziarecruitment.data.model.Result;
import com.mobile.acaziarecruitment.ui.screen.main.MainApplication;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class FavoriteViewModel extends ViewModel {

    public MutableLiveData<List<Result>> resultLiveData = new MutableLiveData<>();

    public void getResultLocal() {
        Observable.just(ResultLocal.getData(MainApplication.getInstance()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<Result>>() {
                    @Override
                    protected void onResponse(List<Result> results) {
                        resultLiveData.setValue(results);
                    }

                    @Override
                    protected void onFailure() {

                    }

                    @Override
                    protected void addDisposableManager(Disposable disposable) {

                    }
                });
    }

}
