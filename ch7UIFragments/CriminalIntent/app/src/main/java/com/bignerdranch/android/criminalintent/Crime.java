package com.bignerdranch.android.criminalintent;

import java.util.UUID;

/**
 * Created by linyang on 2017/9/3.
 */

public class Crime {
    private UUID mId;
    private String mTitle;
    public Crime() {
        // Generate unique identifier
        mId = UUID.randomUUID();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
