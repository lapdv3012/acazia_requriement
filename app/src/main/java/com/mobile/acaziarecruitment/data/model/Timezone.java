package com.mobile.acaziarecruitment.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Timezone implements Serializable {
    @SerializedName("offset")
    @Expose
    public String offset;
    @SerializedName("description")
    @Expose
    public String description;
}
