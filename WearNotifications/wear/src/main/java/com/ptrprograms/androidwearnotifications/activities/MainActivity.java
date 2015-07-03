package com.ptrprograms.androidwearnotifications.activities;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ptrprograms.androidwearnotifications.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends WearableActivity implements WearableListView.ClickListener {

    public static boolean isAmbientMode = false;
    private WearableListView mListView;
    private BoxInsetLayout mBoxInsetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAmbientEnabled();

        NotificationManagerCompat.from( this ).cancelAll();

        mBoxInsetLayout = (BoxInsetLayout) findViewById( R.id.parent );
        mListView = (WearableListView) findViewById( R.id.list );

        WearableListAdapter adapter = new WearableListAdapter();
        String[] notifications = getResources().getStringArray(R.array.notifications);
        adapter.setItems(new ArrayList(Arrays.asList(notifications)));
        mListView.setAdapter(adapter);
        mListView.setClickListener(this);
        getMessageText(getIntent());
    }

    private void getMessageText(Intent intent) {
        if( intent == null )
            return;

        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            Toast.makeText( this, remoteInput.getCharSequence("reply").toString(), Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        Toast.makeText( this, ( (TextView)viewHolder.itemView.findViewById( R.id.text )).getText(), Toast.LENGTH_SHORT ).show();
        String item = ( (TextView) viewHolder.itemView.findViewById( R.id.text ) ).getText().toString();

        if( item.equalsIgnoreCase( getString( R.string.notification_basic ) ) ) {
            showBasicNotification();
            finish();
        } else if( item.equalsIgnoreCase( getString( R.string.notification_multipage ) ) ) {
            showMultiPagedNotification();
            finish();
        } else if( item.equalsIgnoreCase( getString( R.string.notification_stacks ) ) ) {
           showStackedNotification();
            finish();
        } else if( item.equalsIgnoreCase( getString( R.string.notification_action ) ) ) {
            showActionNotification();
            finish();
        } else if( item.equalsIgnoreCase( getString( R.string.notification_reply ) ) ) {
            showReplyNotification();
            finish();
        }  else if( item.equalsIgnoreCase( getString( R.string.notification_custom_screen ) ) ) {
            showCustomNotification();
            finish();
        } else if( item.equalsIgnoreCase( getString( R.string.ui_delayed_confirmation ) ) ) {
            showDelayedConfirmationActivity();
        } else if( item.equalsIgnoreCase( getString( R.string.grid_view_pager ) ) ) {
            showGridViewPagerActivity();
        }
        else if( item.equalsIgnoreCase( getString( R.string.wearable_dialog_activity ) ) ) {
            showDialogActivity();
        }
    }

    private void showDelayedConfirmationActivity() {
        Intent intent = new Intent( this, DelayedConfirmationActivity.class );
        startActivity( intent );
    }

    private void showGridViewPagerActivity() {
        Intent intent = new Intent( this, GridViewPagerActivity.class );
        startActivity( intent );
    }

    private void showDialogActivity() {
        Intent intent = new Intent( this, DialogActivity.class );
        startActivity( intent );
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        isAmbientMode = true;
        mListView.setBackgroundColor(getResources().getColor(android.R.color.black));
        mBoxInsetLayout.setBackgroundColor(getResources().getColor(android.R.color.black));
        mListView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onExitAmbient() {
        super.onExitAmbient();
        isAmbientMode = false;
        mListView.setBackgroundColor(getResources().getColor(android.R.color.white));
        mBoxInsetLayout.setBackgroundColor( getResources().getColor( android.R.color.white ) );
        mListView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
    }

    private void showBasicNotification() {

        NotificationCompat.Builder builder = getBaseNotificationBuilder();
        NotificationManagerCompat
                .from(this)
                .notify(1, builder.build());
    }

    private void showMultiPagedNotification() {
        NotificationCompat.Builder builder = getBaseNotificationBuilder();

        Notification notificationPage = getBaseNotificationBuilder().build();
        NotificationManagerCompat
                .from(this)
                .notify(1, builder
                        .extend(new NotificationCompat.WearableExtender()
                                .addPage(notificationPage)
                                .addPage(notificationPage)
                                .addPage(notificationPage)
                                .addPage(notificationPage))
                        .build());
    }

    private void showStackedNotification() {
        NotificationCompat.Builder builder = getBaseNotificationBuilder();
        builder.setGroup("key");

        Notification notification = builder.build();

        NotificationManagerCompat
                .from(this)
                .notify(1, notification);

        builder = getBaseNotificationBuilder();
        builder.setGroup( "key" );
        notification = builder.build();

        NotificationManagerCompat.from(this).notify(2, notification);

        builder = getBaseNotificationBuilder();
        builder.setGroup( "key" );
        notification = builder.build();

        NotificationManagerCompat.from( this ).notify( 3, notification );

        //Only displayed on phones
        builder = getBaseNotificationBuilder()
                .setGroup("key")
                .setGroupSummary( true )
                .setStyle( new NotificationCompat.InboxStyle()
                    .setBigContentTitle( "Summary title" )
                    .setSummaryText("Summary text" )
                    .addLine( "Line 1" )
                    .addLine( "Line 2" ) );

        notification = builder.build();

        NotificationManagerCompat.from( this ).notify( 4, notification );
    }

    private void showActionNotification() {
        NotificationCompat.Builder builder = getBaseNotificationBuilder();

        Intent intent = new Intent( this, ConfirmationActivity.class );
        intent.putExtra(
                ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                ConfirmationActivity.SUCCESS_ANIMATION );
        intent.putExtra(
                ConfirmationActivity.EXTRA_MESSAGE,
                "Success!" );

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT );

        builder.addAction( new NotificationCompat.Action( R.mipmap.ic_launcher, "Action Title", pendingIntent ) );

        NotificationManagerCompat
                .from( this )
                .notify( 1, builder.build() );
    }

    private void showCustomNotification() {
        NotificationCompat.Builder builder = getBaseNotificationBuilder();

        builder.extend(new NotificationCompat.WearableExtender()
                .addPage(getCustomSizeNotificationPage(Notification.WearableExtender.SIZE_XSMALL))
                .addPage(getCustomSizeNotificationPage(Notification.WearableExtender.SIZE_SMALL))
                .addPage(getCustomSizeNotificationPage(Notification.WearableExtender.SIZE_MEDIUM))
                .addPage(getCustomSizeNotificationPage(Notification.WearableExtender.SIZE_LARGE))
                .addPage(getCustomSizeNotificationPage(Notification.WearableExtender.SIZE_FULL_SCREEN))
                .addPage(getCustomSizeNotificationPage(Notification.WearableExtender.SIZE_DEFAULT)));

        NotificationManagerCompat
                .from(this)
                .notify( 1, builder.build() );
    }

    private void showReplyNotification() {
        NotificationCompat.Builder builder = getBaseNotificationBuilder();

        String[] replies = getResources().getStringArray( R.array.quick_reply );

        RemoteInput remoteInput = new RemoteInput.Builder( "reply" )
                .setLabel( "Label" )
                .setChoices( replies )
                .build();

        Intent replyIntent = new Intent(this, MainActivity.class);
        PendingIntent replyPendingIntent =
                PendingIntent.getActivity(this, 0, replyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        builder.extend( new NotificationCompat.WearableExtender().addAction( new NotificationCompat.Action.Builder(
                R.mipmap.ic_launcher,
                "Reply",
                replyPendingIntent )
                .addRemoteInput(remoteInput).build() ) );

        NotificationManagerCompat
                .from( this )
                .notify(1, builder.build());

    }

    private Notification getCustomSizeNotificationPage( int size ) {

        Intent intent = new Intent( this, CustomNotificationActivity.class );

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT );

        NotificationCompat.Builder builder = getBaseNotificationBuilder();

        builder.extend(new NotificationCompat.WearableExtender()
                .setDisplayIntent( pendingIntent )
                .setCustomSizePreset(size));

        return builder.build();
    }

    private NotificationCompat.Builder getBaseNotificationBuilder() {
        return new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle( "Notification Title" )
                        .setContentText( "Notification Text" );
    }

    @Override
    public void onTopEmptyRegionClick() {

    }

    private static final class WearableListAdapter extends WearableListView.Adapter {

        private List<String> mNotificationTypes = new ArrayList<String>();

        public List<String> getItems() {
            return mNotificationTypes;
        }

        public void setItems( List<String> items ) {
            mNotificationTypes = items;
        }

        public void add( String item ) {
            mNotificationTypes.add( item );
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from( viewGroup.getContext() ).inflate(R.layout.view_list_item, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.textView.setText( mNotificationTypes.get( i ) );
            if( MainActivity.isAmbientMode ) {
                holder.textView.setTextColor(holder.textView.getResources().getColor(android.R.color.white));
            } else {
                holder.textView.setTextColor( holder.textView.getResources().getColor( android.R.color.black ));
            }
            holder.itemView.setTag( i );
        }

        @Override
        public void onViewRecycled(WearableListView.ViewHolder holder) {
            super.onViewRecycled(holder);
            ViewHolder viewholder = ((ViewHolder) holder);
            if( MainActivity.isAmbientMode ) {
                viewholder.textView.setTextColor( viewholder.textView.getResources().getColor( android.R.color.white) );
            } else {
                viewholder.textView.setTextColor( viewholder.textView.getResources().getColor( android.R.color.black) );
            }
        }

        @Override
        public int getItemCount() {
            return ( mNotificationTypes == null ) ? 0 : mNotificationTypes.size();
        }
    }

    public static class ViewHolder extends WearableListView.ViewHolder {
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
