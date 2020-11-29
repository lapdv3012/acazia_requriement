package com.mobile.acaziarecruitment.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Id implements Serializable {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("value")
    @Expose
    public Object value;
}
