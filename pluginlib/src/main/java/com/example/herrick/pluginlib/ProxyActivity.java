package com.example.herrick.pluginlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by herrickxu
 * 2019-07-06 01:55
 */
public class ProxyActivity extends Activity {

    private String mClassName;
    private PluginAPK mPluginAPK;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        mPluginAPK = PluginManager.getInstance().getPluginApk();

        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if (mPluginAPK == null) {
            Log.d("==>", "Loading apk file first please");
        }
        try {
            Class<?> clazz = mPluginAPK.mClassLoader.loadClass(mClassName);
            Object object = clazz.newInstance();
            if (object instanceof IPlugin) {
                IPlugin iPlugin = (IPlugin) object;
                iPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM", IPlugin.FROM_EXTERNAL);
                iPlugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public Resources getResources() {
        return mPluginAPK!=null?mPluginAPK.mResources:super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mPluginAPK!=null?mPluginAPK.mAssetManager:super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginAPK!=null?mPluginAPK.mClassLoader :super.getClassLoader();
    }
}
