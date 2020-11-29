package com.mobile.acaziarecruitment.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Name implements Serializable {
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("first")
    @Expose
    public String first;
    @SerializedName("last")
    @Expose
    public String last;
}
