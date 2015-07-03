package com.ptrprograms.androidwearnotifications.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;

import com.ptrprograms.androidwearnotifications.R;
import com.ptrprograms.androidwearnotifications.adapters.GridViewPagerAdapter;

/**
 * Created by Paul on 7/3/15.
 */
public class GridViewPagerActivity extends Activity {

    private GridViewPager mGridViewPager;
    private GridViewPagerAdapter mAdapter;
    private DotsPageIndicator mPageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_pager);

        mGridViewPager = (GridViewPager) findViewById( R.id.pager );
        mPageIndicator = (DotsPageIndicator) findViewById( R.id.page_indicator );
        mAdapter = new GridViewPagerAdapter( this, getFragmentManager() );
        mGridViewPager.setAdapter( mAdapter );
        mPageIndicator.setPager( mGridViewPager );
    }
}
