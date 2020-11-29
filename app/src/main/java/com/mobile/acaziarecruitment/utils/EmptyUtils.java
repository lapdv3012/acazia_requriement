package com.mobile.acaziarecruitment.utils;

import android.os.Build;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public final class EmptyUtils {

    public static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(final Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isValidation(View view, String error) {
        if (view instanceof TextView) {
            if (isEmpty(((TextView) view).getText().toString().trim())) {
                view.requestFocus();
                ((TextView) view).setError(error);
                return false;
            }
        } else if (view instanceof EditText) {
            if (isEmpty(((EditText) view).getText().toString().trim())) {
                view.requestFocus();
                ((EditText) view).setError(error);
                return false;
            }
        }
        return true;
    }
}