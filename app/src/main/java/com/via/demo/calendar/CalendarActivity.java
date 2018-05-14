/*
 * 1312424234234
 */
package com.via.demo.calendar;

import android.os.Bundle;

import com.via.demo.base.BaseActivity;

public class CalendarActivity extends BaseActivity {
    private CalendarFragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void loadChildrenFragment() {
        if (mFragment == null) {
            mFragment = new CalendarFragment();
        }
        addFragment(mFragment,mFragment.getClass().toString());
    }
}
