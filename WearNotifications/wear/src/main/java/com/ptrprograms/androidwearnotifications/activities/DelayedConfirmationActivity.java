package com.ptrprograms.androidwearnotifications.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.View;
import android.widget.Toast;

import com.ptrprograms.androidwearnotifications.R;

/**
 * Created by Paul on 7/3/15.
 */
public class DelayedConfirmationActivity extends Activity implements DelayedConfirmationView.DelayedConfirmationListener {

    private DelayedConfirmationView mDelayedConfirmationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delayed_confirmation);
        mDelayedConfirmationView = (DelayedConfirmationView) findViewById(R.id.delayed_confirmation);

        mDelayedConfirmationView.setTotalTimeMs(3000);
        mDelayedConfirmationView.setListener(this);
        mDelayedConfirmationView.start();
    }

    @Override
    public void onTimerFinished(View view) {
        Toast.makeText(getApplicationContext(), "Timer finished", Toast.LENGTH_SHORT).show();
        mDelayedConfirmationView.setListener(null);
        finish();
    }

    @Override
    public void onTimerSelected(View view) {
        Toast.makeText(getApplicationContext(), "Timer selected", Toast.LENGTH_SHORT).show();
        mDelayedConfirmationView.setListener(null);
        finish();
    }
}
