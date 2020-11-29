package com.mobile.acaziarecruitment.data.api.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobile.acaziarecruitment.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {

    private static final int DEFAULT_TIMEOUT = 30;
    private static AppClient appClient;

    public static AppClient getInstance() {
        synchronized (AppClient.class) {
            if (appClient == null) {
                appClient = new AppClient();
            }
            return appClient;
        }
    }

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, String url) {
        OkHttpClient okHttpClient;
        if (BuildConfig.DEBUG) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addNetworkInterceptor(
                    new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            builder.addInterceptor(authorizationInterceptor());
            builder.retryOnConnectionFailure(true);
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            builder.connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS));//.cache(cache)
            okHttpClient = builder
                    .build();
        } else {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(authorizationInterceptor())
                    .retryOnConnectionFailure(true)
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                    .build();
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url != null ? url : BuildConfig.BASE_URL)
                .build();
        return retrofit.create(serviceClass);
    }

    private static Interceptor authorizationInterceptor() {
        return chain -> {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body());
            Response response = null;
            try {
                response = chain.proceed(builder.build());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        };
    }

}