package com.mobile.acaziarecruitment.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Dob implements Serializable {
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("age")
    @Expose
    public int age;
}
