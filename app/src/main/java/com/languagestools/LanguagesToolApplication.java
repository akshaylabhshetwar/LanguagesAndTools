package com.languagestools;

import android.app.Application;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class LanguagesToolApplication extends Application {
    public static final String TAG = "languagesTools";

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(800, 800) // default = device screen dimensions
                .diskCacheExtraOptions(800, 800, null)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(5 * 1024 * 1024))
                .memoryCacheSize(5 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .writeDebugLogs()
                .threadPoolSize(12);

        ImageLoader.getInstance().init(builder.build());

    }
}
