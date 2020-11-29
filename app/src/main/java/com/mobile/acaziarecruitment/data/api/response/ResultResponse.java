package com.mobile.acaziarecruitment.data.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mobile.acaziarecruitment.data.model.Info;
import com.mobile.acaziarecruitment.data.model.Result;

import java.io.Serializable;
import java.util.List;

public class ResultResponse implements Serializable {
    @SerializedName("results")
    @Expose
    public List<Result> results = null;
    @SerializedName("info")
    @Expose
    public Info info;

}
