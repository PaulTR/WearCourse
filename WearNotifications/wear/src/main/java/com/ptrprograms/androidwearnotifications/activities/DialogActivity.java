package com.ptrprograms.androidwearnotifications.activities;

import android.support.wearable.view.WearableDialogActivity;
import android.widget.Toast;

/**
 * Created by Paul on 7/3/15.
 */
public class DialogActivity extends WearableDialogActivity {

    @Override
    public CharSequence getPositiveButtonText() {
        return "Positive Button";
    }

    @Override
    public CharSequence getNegativeButtonText() {
        return "Negative Button";
    }

    @Override
    public CharSequence getMessage() {
        return "This is a long message for displaying in the wearable dialog activity";
    }

    @Override
    public CharSequence getAlertTitle() {
        return "Title";
    }

    @Override
    public void onPositiveButtonClick() {
        Toast.makeText( this, "Positive Button", Toast.LENGTH_SHORT ).show();
        super.onPositiveButtonClick();
    }

    @Override
    public void onNegativeButtonClick() {
        Toast.makeText( this, "Negative Button", Toast.LENGTH_SHORT ).show();
        super.onNegativeButtonClick();
    }
}
