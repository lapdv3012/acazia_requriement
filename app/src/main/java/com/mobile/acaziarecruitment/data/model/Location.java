package com.mobile.acaziarecruitment.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {
    @SerializedName("street")
    @Expose
    public Street street;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("postcode")
    @Expose
    public Integer postcode;
    @SerializedName("coordinates")
    @Expose
    public Coordinates coordinates;
    @SerializedName("timezone")
    @Expose
    public Timezone timezone;
}
