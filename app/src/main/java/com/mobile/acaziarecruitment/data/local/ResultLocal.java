package com.mobile.acaziarecruitment.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobile.acaziarecruitment.data.model.Result;
import com.mobile.acaziarecruitment.utils.Constant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResultLocal {

    public static List<Result> getData(Context context) {
        Gson gson = new Gson();
        List<Result> productFromShared = new ArrayList<>();
        SharedPreferences sharedPref = context.getSharedPreferences(Constant.RESULT_LIST, Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString(Constant.RESULT_LIST, "");
        Type type = new TypeToken<List<Result>>() {
        }.getType();
        productFromShared = gson.fromJson(jsonPreferences, type);
        return productFromShared;
    }

    public static void setData(Context context, List<Result> curProduct) {
        Gson gson = new Gson();
        String jsonCurProduct = gson.toJson(curProduct);
        SharedPreferences sharedPref = context.getSharedPreferences(Constant.RESULT_LIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constant.RESULT_LIST, jsonCurProduct);
        editor.commit();
    }

    public static void removeAll(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.RESULT_LIST, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }
}
