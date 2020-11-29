package com.mobile.acaziarecruitment.data.constant;

import androidx.annotation.IntDef;

@IntDef({PositionTab.FAVORITES, PositionTab.HOME, PositionTab.LOGOUT})
public @interface PositionTab {
    int FAVORITES = 0;
    int HOME = 1;
    int LOGOUT = 2;
}
