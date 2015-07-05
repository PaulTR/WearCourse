package com.ptrprograms.androidwearnotifications.views;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ptrprograms.androidwearnotifications.R;
import com.ptrprograms.androidwearnotifications.activities.MainActivity;

/**
 * Created by Paul on 6/28/15.
 */
public class WearableListItemLayout extends LinearLayout implements WearableListView.OnCenterProximityListener {
    private TextView mText;

    public WearableListItemLayout(Context context) {
        this(context, null);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mText = (TextView) findViewById( R.id.text );
    }

    @Override
    public void onCenterPosition(boolean b) {
        if( !MainActivity.isAmbientMode )
           mText.setTextColor( getResources().getColor( android.R.color.black ) );
    }

    @Override
    public void onNonCenterPosition(boolean b) {
        if( !MainActivity.isAmbientMode )
            mText.setTextColor( getResources().getColor( android.R.color.darker_gray ) );
    }
}
