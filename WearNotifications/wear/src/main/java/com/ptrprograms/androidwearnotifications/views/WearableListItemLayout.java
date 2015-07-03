package com.ptrprograms.androidwearnotifications.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ptrprograms.androidwearnotifications.R;

/**
 * Created by Paul on 6/28/15.
 */
public class WearableListItemLayout extends LinearLayout {
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
}
