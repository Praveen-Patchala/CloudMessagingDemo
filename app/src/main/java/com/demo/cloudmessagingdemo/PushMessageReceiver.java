package com.demo.cloudmessagingdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import static com.pax.market.android.app.sdk.PushConstants.ACTION_DATA_MESSAGE_RECEIVED;
import static com.pax.market.android.app.sdk.PushConstants.ACTION_NOTIFICATION_CLICK;
import static com.pax.market.android.app.sdk.PushConstants.ACTION_NOTIFICATION_MESSAGE_RECEIVED;
import static com.pax.market.android.app.sdk.PushConstants.ACTION_NOTIFY_DATA_MESSAGE_RECEIVED;
import static com.pax.market.android.app.sdk.PushConstants.EXTRA_MESSAGE_CONTENT;
import static com.pax.market.android.app.sdk.PushConstants.EXTRA_MESSAGE_DATA;
import static com.pax.market.android.app.sdk.PushConstants.EXTRA_MESSAGE_NID;
import static com.pax.market.android.app.sdk.PushConstants.EXTRA_MESSAGE_TITLE;

public class PushMessageReceiver extends BroadcastReceiver {

    private static String TAG="CloudMessagingDemo";
    private final Handler handler;
    private final TextView tv_message;

    public PushMessageReceiver(android.os.Handler handler, TextView tv_message) {
        this.handler=handler;
        this.tv_message=tv_message;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if(ACTION_NOTIFY_DATA_MESSAGE_RECEIVED.equals(intent.getAction())){
            Log.i(TAG,"NOTIFY_DATA_MESSAGE_RECEIVED Begin");
            final String title=intent.getStringExtra(EXTRA_MESSAGE_TITLE);
            final String content=intent.getStringExtra(EXTRA_MESSAGE_CONTENT);
            final String dataJson=intent.getStringExtra(EXTRA_MESSAGE_DATA);
            Log.d(TAG,"Notification title:"+title+"\nNotitication content:"+content+"\nData:"+dataJson);
            Log.d(TAG,"NOTIFY_DATA_MESSAGE_RECEIVED End");
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tv_message.setText("NOTIFY_DATA_MESSAGE_RECEIVED Begin\nNotification title:"+title+"\nNotitication content:"+content+"\nData:"+dataJson+"\nNOTIFY_DATA_MESSAGE_RECEIVED End");
                }
            });
        }

        else if (ACTION_DATA_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG,"NOTIFY_DATA_MESSAGE_RECEIVED Begin");
            final String dataJson=intent.getStringExtra(EXTRA_MESSAGE_DATA);
            Log.d(TAG,"Data:"+dataJson);
            Log.d(TAG,"NOTIFY_DATA_MESSAGE_RECEIVED End");
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tv_message.setText("NOTIFY_DATA_MESSAGE_RECEIVED Begin\nData:"+dataJson+"\nNOTIFY_DATA_MESSAGE_RECEIVED End");
                }
            });
        }

        else if (ACTION_NOTIFICATION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG,"NOTIFICATION_MESSAGE_RECEIVED Begin");
            final String title = intent.getStringExtra(EXTRA_MESSAGE_TITLE);
            final String content = intent.getStringExtra(EXTRA_MESSAGE_CONTENT);
            Log.d(TAG,"Notification title:"+title+"\nNotification content:"+content);
            Log.d(TAG,"NOTIFICATION_MESSAGE_RECEIVED End");
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tv_message.setText("NOTIFICATION_MESSAGE_RECEIVED Begin\nNotification title:"+title+"\nNotification content:"+content+"\nNOTIFICATION_MESSAGE_RECEIVED End");
                }
            });
        }

        else if (ACTION_NOTIFICATION_CLICK.equals(intent.getAction())) {
            Log.d(TAG,"ACTION_NOTIFICATION_CLICK Begin");
            final int nid = intent.getIntExtra(EXTRA_MESSAGE_NID, 0);
            final String title = intent.getStringExtra(EXTRA_MESSAGE_TITLE);
            final String content = intent.getStringExtra(EXTRA_MESSAGE_CONTENT);
            final String dataJson = intent.getStringExtra(EXTRA_MESSAGE_DATA);
            Log.d(TAG,"Notification id:"+nid+"Notification title:"+title+"\nNotitication content:"+content+"\nData:"+dataJson);
            Log.d(TAG,"ACTION_NOTIFICATION_CLICK End");
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tv_message.setText("ACTION_NOTIFICATION_CLICK Begin\nNotification id:"+nid+"Notification title:"+title+"\nNotitication content:"+content+"\nData:"+dataJson+"\nACTION_NOTIFICATION_CLICK End");
                }
            });
        }
    }
}
