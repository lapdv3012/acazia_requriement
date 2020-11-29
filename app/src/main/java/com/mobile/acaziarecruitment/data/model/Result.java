package com.mobile.acaziarecruitment.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("name")
    @Expose
    public Name name;
    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("login")
    @Expose
    public Login login;
    @SerializedName("dob")
    @Expose
    public Dob dob;
    @SerializedName("registered")
    @Expose
    public Registered registered;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("cell")
    @Expose
    public String cell;
    @SerializedName("id")
    @Expose
    public Id id;
    @SerializedName("picture")
    @Expose
    public Picture picture;
    @SerializedName("nat")
    @Expose
    public String nat;
}
