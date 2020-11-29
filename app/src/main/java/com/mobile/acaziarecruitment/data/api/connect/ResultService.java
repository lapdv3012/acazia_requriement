package com.mobile.acaziarecruitment.data.api.connect;

import com.mobile.acaziarecruitment.data.api.response.ResultResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ResultService {

    @GET("api")
    Observable<ResultResponse> getRandomUser();
}
