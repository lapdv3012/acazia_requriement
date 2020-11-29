package com.mobile.acaziarecruitment.data.model;

import android.util.Patterns;

import com.mobile.acaziarecruitment.ui.screen.main.MainApplication;
import com.mobile.acaziarecruitment.utils.SPUtils;

public class User {

    public String phone;
    public String password;

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public boolean isValidPassword() {
        if (password != null && password.length() >= 8) {
            return true;
        }
        return false;
    }

    public boolean isValidPhone() {
        return phone != null && phone.length() >= 8 && Patterns.PHONE.matcher(phone).matches();
    }
}
