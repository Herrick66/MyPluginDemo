package com.example.herrick.neteasepluginapk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.herrick.pluginlib.PluginActivity;
import com.example.herrick.pluginlib.PluginManager;
import com.example.herrick.pluginlib.ProxyActivity;

public class NEPluginActivity extends PluginActivity {

    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neplugin);
        mProxyAcitivity.findViewById(R.id.tv_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PluginManager.getInstance().init(mProxyAcitivity);
                Intent intent = new Intent();
                intent.setClass(mProxyAcitivity, ProxyActivity.class);
                intent.putExtra("className", "com.example.herrick.neteasepluginapk.SecondActivity");
                mProxyAcitivity.startActivity(intent);
            }
        });
    }
}
