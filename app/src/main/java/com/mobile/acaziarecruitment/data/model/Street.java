package com.mobile.acaziarecruitment.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Street implements Serializable {
    @SerializedName("number")
    @Expose
    public Integer number;
    @SerializedName("name")
    @Expose
    public String name;
}
