package com.mobile.acaziarecruitment.data.api.connect;

import com.mobile.acaziarecruitment.data.api.network.AppApi;
import com.mobile.acaziarecruitment.data.api.network.BaseObserver;
import com.mobile.acaziarecruitment.data.api.response.ResultResponse;

public class ResultApi extends AppApi {

    public static void getRandomUser(BaseObserver<ResultResponse> baseObserver) {
        addObservable(resultService().getRandomUser(), baseObserver);
    }
}
