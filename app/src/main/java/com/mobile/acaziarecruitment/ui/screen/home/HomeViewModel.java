package com.mobile.acaziarecruitment.ui.screen.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobile.acaziarecruitment.data.api.connect.ResultApi;
import com.mobile.acaziarecruitment.data.api.network.BaseObserver;
import com.mobile.acaziarecruitment.data.api.response.ResultResponse;
import com.mobile.acaziarecruitment.data.local.ResultLocal;
import com.mobile.acaziarecruitment.data.model.Result;
import com.mobile.acaziarecruitment.ui.screen.main.MainApplication;
import com.mobile.acaziarecruitment.utils.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class HomeViewModel extends ViewModel {

    public MutableLiveData<List<Result>> resultLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> error = new MutableLiveData<>();

    public void getRandomUser() {
        isLoading.setValue(true);
        ResultApi.getRandomUser(new BaseObserver<ResultResponse>() {
            @Override
            protected void onResponse(ResultResponse response) {
                if (EmptyUtils.isNotEmpty(response.results)) {
                    resultLiveData.setValue(response.results);
                }
                isLoading.setValue(false);
                error.setValue(false);
            }

            @Override
            protected void onFailure() {
                isLoading.setValue(true);
                error.setValue(true);
            }

            @Override
            protected void addDisposableManager(Disposable disposable) {

            }
        });
    }

    public void saveFavoriteUser(Result result) {
        Observable.just(ResultLocal.getData(MainApplication.getInstance()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<Result>>() {
                    @Override
                    protected void onResponse(List<Result> results) {
                        List<Result> resultList = results;
                        if (resultList == null) {
                            resultList = new ArrayList<>();
                        }
                        resultList.add(result);
                        ResultLocal.setData(MainApplication.getInstance(), resultList);
                    }

                    @Override
                    protected void onFailure() {

                    }

                    @Override
                    protected void addDisposableManager(Disposable disposable) {

                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
