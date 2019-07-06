package com.example.herrick.myplugindemo;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.herrick.pluginlib.PluginManager;
import com.example.herrick.pluginlib.ProxyActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().init(this);
        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apkPath = Utils.copyAssetAndWrite(MainActivity.this, "ass.apk");
                PluginManager.getInstance().loadApk(apkPath);
            }
        });

        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProxyActivity.class);
                intent.putExtra("className", "com.example.herrick.neteasepluginapk.NEPluginActivity");
                startActivity(intent);
            }
        });
    }
}
