package com.demo.cloudmessagingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pax.market.android.app.sdk.BaseApiService;
import com.pax.market.android.app.sdk.StoreSdk;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="CloudMessagingDemo";
    private Helper helper=new Helper();

    TextView tv_SDKInit;
    TextView tv_message;
    BroadcastReceiver br;
    Handler handler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_SDKInit=findViewById(R.id.tv_initSuccessFail);
        tv_message=findViewById(R.id.tv_message);
        br=new PushMessageReceiver(handler,tv_message);

        initPaxStoreSdk();
    }

    private void initPaxStoreSdk() {
        StoreSdk.getInstance().init(getApplicationContext(), helper.getAppKey(), helper.getAppSecret(), Build.SERIAL, new BaseApiService.Callback() {
            @Override
            public void initSuccess() {
                tv_SDKInit.setText("Success");
                Log.d(TAG,"SDK init successful");
            }

            @Override
            public void initFailed(RemoteException e) {
                tv_SDKInit.setText("Fail");
                Log.d(TAG,"SDK init failed, exception:"+e);
            }
        });
    }

    public void registerBroadcast(View view) {
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.paxstore.mpush.NOTIFY_DATA_MESSAGE_RECEIVED");
        filter.addAction("com.paxstore.mpush.DATA_MESSAGE_RECEIVED");
        filter.addAction("com.paxstore.mpush.NOTIFICATION_MESSAGE_RECEIVED");
        filter.addAction("com.paxstore.mpush.NOTIFICATION_CLICK");
        registerReceiver(br,filter);
    }

    public void unregisterBroadcast(View view) {
        unregisterReceiver(br);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(br);
        super.onDestroy();
    }

}
