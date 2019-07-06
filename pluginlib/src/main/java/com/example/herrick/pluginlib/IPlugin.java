package com.example.herrick.pluginlib;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by herrickxu
 * 2019-07-06 01:36
 */
public interface IPlugin {

    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;

    void attach(Activity proxyActivity);

    void onCreate(Bundle saveInstanceState);

    void onResume();
}
