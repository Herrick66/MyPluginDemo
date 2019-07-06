package com.example.herrick.neteasepluginapk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.herrick.pluginlib.PluginActivity;

public class SecondActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
