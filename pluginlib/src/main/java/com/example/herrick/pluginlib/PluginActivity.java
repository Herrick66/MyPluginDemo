package com.example.herrick.pluginlib;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by herrickxu
 * 2019-07-06 01:40
 */
public class PluginActivity extends Activity implements IPlugin {
    private int mFrom = FROM_INTERNAL;
    public Activity mProxyAcitivity;

    @Override
    public void attach(Activity proxyActivity) {
        mProxyAcitivity = proxyActivity;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        if (saveInstanceState != null) {
            mFrom = saveInstanceState.getInt("FROM");
        }

        if (mFrom == FROM_INTERNAL) {
            super.onCreate(saveInstanceState);
            mProxyAcitivity = this;
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (mFrom == FROM_INTERNAL) {
            super.setContentView(layoutResID);
        } else {
            mProxyAcitivity.setContentView(layoutResID);

        }
    }

    @Override
    public void onResume() {
        if (mFrom == FROM_INTERNAL) {
            super.onResume();
        }
    }
}
