package com.languagestools.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.languagestools.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class LanguageToolViewModel extends BaseObservable {
    private LanguageTool languageTool;

    public LanguageToolViewModel(LanguageTool languageTool) {
        this.languageTool = languageTool;
    }

    @Bindable
    public String getName() {
        return languageTool.getName();
    }

    public String getIcon() {
        return languageTool.getIcon();
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String url) {
        ImageLoader.getInstance().displayImage(url, view, imageDisplayOptionsGray());
    }

    private static DisplayImageOptions imageDisplayOptionsGray() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.drawable.image_place_holder)
                .build();
    }
}
