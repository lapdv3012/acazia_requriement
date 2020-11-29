package com.mobile.acaziarecruitment.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Picture implements Serializable {
    @SerializedName("large")
    @Expose
    public String large;
    @SerializedName("medium")
    @Expose
    public String medium;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;
}
