package com.mobile.acaziarecruitment.ui.screen.main;

import android.app.Application;

import com.mobile.acaziarecruitment.data.api.network.AppClient;

public class MainApplication extends Application {

    private static MainApplication sInstance;

    public static MainApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppClient.getInstance();
        sInstance = this;
    }
}
