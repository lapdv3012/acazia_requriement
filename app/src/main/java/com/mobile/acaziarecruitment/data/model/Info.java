package com.mobile.acaziarecruitment.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Info implements Serializable {
    @SerializedName("seed")
    @Expose
    public String seed;
    @SerializedName("results")
    @Expose
    public int results;
    @SerializedName("page")
    @Expose
    public int page;
    @SerializedName("version")
    @Expose
    public String version;
}
