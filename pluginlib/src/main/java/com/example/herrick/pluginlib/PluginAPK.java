package com.example.herrick.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * Created by herrickxu
 * 2019-07-06 00:54
 */
public class PluginAPK {

//    插件的实体对象
    public DexClassLoader mClassLoader;
    public Resources      mResources;
    public PackageInfo    mPackageInfo;
    public AssetManager   mAssetManager;

    public PluginAPK(DexClassLoader classLoader, Resources resources, PackageInfo packageInfo) {
        mClassLoader = classLoader;
        mResources = resources;
        mPackageInfo = packageInfo;
        mAssetManager = mResources.getAssets();
    }
}
