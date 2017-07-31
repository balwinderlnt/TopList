package app.com.toplist.Presenter.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import app.com.toplist.Constants.Constant;
import app.com.toplist.Presenter.communication.IUIEventListner;
import app.com.toplist.Presenter.fragment.BaseFragment;
import app.com.toplist.Presenter.fragment.FragmentTopList;
import app.com.toplist.R;
import app.com.toplist.Utility.Utility;


/**
 * Created by Balwinder on 27/07/2017.
 * Copyright (c) 2017 M800 inc. All rights reserved.
 */

public class MasterScreen extends ActionBaseActivity implements IUIEventListner, View.OnClickListener {
    private static final String TAG = "MasterScreen";
    private BroadcastReceiver broadcastReceiver;
    MenuItem actionRefresh;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        Utility.intiallizeApp(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialiseActionBar();
        BaseFragment baseFragment = new FragmentTopList();
        replaceFragment(baseFragment);


    }

    @Override
    protected void onStart() {
        super.onStart();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String receivedIntent = intent.getAction();
                if (TextUtils.equals(receivedIntent,
                        Constant.Receivers.BROADCAST_CONNECTIVITY_CHANGE)) {
                    onConnectionChange(Utility.isNetworkConnected(MasterScreen.this));
                }

            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.Receivers.BROADCAST_CONNECTIVITY_CHANGE);
        this.registerReceiver(broadcastReceiver, filter);
    }


    @Override
    public void onBackPressed() {
        //  getFragmetinStack();
        if (getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {

        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(broadcastReceiver);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        actionRefresh = menu.findItem(R.id.action_refresh);
        return false;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    void onConnectionChange(boolean is_connected) {
        if (actionRefresh == null)
            return;
        if (is_connected)
            actionRefresh.setVisible(true);
        else
            actionRefresh.setVisible(false);
    }

    @Override
    public void handleSuccessEvent(int action, Object data, Object packet) {

    }

    @Override
    public void handleFailureEvent(int action, Object data) {

    }

    @Override
    public void handleErrorEvent(int action, Object data) {

    }

    @Override
    public void handleRetrialEvent(int action, Object data) {

    }
}

