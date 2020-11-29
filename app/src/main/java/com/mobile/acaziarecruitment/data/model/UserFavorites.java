package com.mobile.acaziarecruitment.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserFavorites {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("image")
    @Expose
    public String image;

    public UserFavorites(String name, String location, String image) {
        this.name = name;
        this.location = location;
        this.image = image;
    }
}
