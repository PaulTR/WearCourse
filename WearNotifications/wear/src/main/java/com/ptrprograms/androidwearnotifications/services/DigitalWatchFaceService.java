package com.ptrprograms.androidwearnotifications.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;
import android.text.format.Time;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowInsets;

import com.ptrprograms.androidwearnotifications.R;

/**
 * Created by Paul on 7/4/15.
 */
public class DigitalWatchFaceService extends CanvasWatchFaceService {

    @Override
    public Engine onCreateEngine() {
        Log.e( "Watch Face", "onCreateEngine" );
        return new Engine();
    }

    private class Engine extends CanvasWatchFaceService.Engine {
        private Typeface WATCH_TEXT_TYPEFACE = Typeface.create( Typeface.SERIF, Typeface.NORMAL );

        private Paint mBackgroundColorPaint;
        private Paint mTextColorPaint;

        private Time mTime;

        private int mBackgroundColor = Color.parseColor( "green" );
        private int mTextColor = Color.parseColor("red");


        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);

            setWatchFaceStyle(new WatchFaceStyle.Builder(DigitalWatchFaceService.this)
                            .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
                            .setCardPeekMode(WatchFaceStyle.PEEK_MODE_VARIABLE)
                            .setShowSystemUiTime(false)
                            .build()
            );

            initBackground();
            initDisplayText();

            mTime = new Time();
        }

        @Override
        public void onTimeTick() {
            super.onTimeTick();
            Log.e( "Watch Face", "onTimeTick" );
            invalidate();
        }

        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            Log.e( "Watch Face", "onDraw" );
            super.onDraw(canvas, bounds);
            mTime.setToNow();

            drawBackground(canvas, bounds);
            drawTime(canvas);

        }

        private void drawBackground( Canvas canvas, Rect bounds ) {
            canvas.drawRect(0, 0, bounds.width(), bounds.height(), mBackgroundColorPaint);
        }

        private void drawTime( Canvas canvas ) {
            String timeText = getHourString() + ":" + String.format( "%02d", mTime.minute );
            timeText += ( mTime.hour < 12 ) ? "AM" : "PM";
            canvas.drawText(timeText, 75, 150, mTextColorPaint);
        }

        private String getHourString() {
            if( mTime.hour % 12 == 0 )
                return "12";
            else if( mTime.hour <= 12 )
                return String.valueOf( mTime.hour );
            else
                return String.valueOf( mTime.hour - 12 );
        }

        private void initBackground() {
            mBackgroundColorPaint = new Paint();
            mBackgroundColorPaint.setColor(mBackgroundColor);
        }

        private void initDisplayText() {
            mTextColorPaint = new Paint();
            mTextColorPaint.setColor(mTextColor);
            mTextColorPaint.setTypeface(WATCH_TEXT_TYPEFACE);
            mTextColorPaint.setAntiAlias(true);
            mTextColorPaint.setTextSize( getResources().getDimension( R.dimen.digital_watch_face_text_size ) );
        }

        /////////

        //User tilts their wrist to view their watch
        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
        }

        //Switching to low bit ambient/burn in protection
        @Override
        public void onPropertiesChanged(Bundle properties) {
            super.onPropertiesChanged(properties);
        }

        //Clicking the side button/timing out to enter ambient mode
        @Override
        public void onAmbientModeChanged(boolean inAmbientMode) {
            super.onAmbientModeChanged(inAmbientMode);
        }

        //Changing notification types between muted, priority or all
        @Override
        public void onInterruptionFilterChanged(int interruptionFilter) {
            super.onInterruptionFilterChanged(interruptionFilter);
        }

        //Initial padding
        @Override
        public void onApplyWindowInsets(WindowInsets insets) {
            super.onApplyWindowInsets(insets);
        }
    }
}
