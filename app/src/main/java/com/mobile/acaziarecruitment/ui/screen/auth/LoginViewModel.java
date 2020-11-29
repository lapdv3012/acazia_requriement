package com.mobile.acaziarecruitment.ui.screen.auth;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobile.acaziarecruitment.data.api.network.BaseObserver;
import com.mobile.acaziarecruitment.data.model.User;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> validError = new MutableLiveData<>();
    public MutableLiveData<User> userValid = new MutableLiveData<>();

    public void validationLogin(String phone, String password) {
        User user = new User(phone, password);
        Observable.just(user).filter(user1 -> {
            if (!user.isValidPhone()) {
                validError.setValue("Please enter phone number, minimum 8 characters!");
                return false;
            } else if (!user.isValidPassword()) {
                validError.setValue("Password Should be Minimum 8 Characters");
                return false;
            } else {
                return true;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<User>() {
                    @Override
                    protected void onResponse(User user) {
                        userValid.setValue(user);
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
