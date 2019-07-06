package com.example.herrick.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by herrickxu
 * 2019-07-06 00:56
 */
public class PluginManager {
    private static final PluginManager instance = new PluginManager();
    private Context mContext;
    private PluginAPK mPluginAPK;

    public static PluginManager getInstance() {
        return instance;
    }

    private PluginManager() {

    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public PluginAPK getPluginApk() {
        return mPluginAPK;
    }


    /**
     * 加载apk 文件
     * @param apkPath
     */
    public void loadApk(String apkPath) {
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        if (packageInfo == null) {
            return;
        }

        DexClassLoader classLoader = createDexClassLoader(apkPath);
        AssetManager am = createAssetManager(apkPath);
        Resources resources = createResources(am);
        mPluginAPK = new PluginAPK(classLoader,resources,packageInfo);

    }

    private Resources createResources(AssetManager am) {
        Resources res = mContext.getResources();
        return new Resources(am,res.getDisplayMetrics(),res.getConfiguration());
    }

    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getMethod("addAssetPath", String.class);
            method.invoke(am, apkPath);
            return am;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private DexClassLoader createDexClassLoader(String apkPath) {
        File file = mContext.getDir("dex", Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath,file.getAbsolutePath(),null,mContext.getClassLoader());
    }
}
