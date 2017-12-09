package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by linyang on 2017/9/9.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return  new CrimeListFragment();
    }
}
