package com.fjsy.architecture.ui.binding_adapter;

import android.database.Observable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableChar;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fjsy.architecture.R;
import com.fjsy.architecture.ui.glide.CustomGlideTransform;
import com.fjsy.architecture.ui.glide.GlideImageLoader;

public class CommonViewBinding {

    @BindingAdapter(value = {"imageUrl", "placeholder"}, requireAll = false)
    public static void loadImageWithPlaceholder(ImageView imageView, String url, int placeholder) {
        GlideImageLoader.getInstance().load(imageView, url, placeholder);
    }

    @BindingAdapter(value = {"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        GlideImageLoader.getInstance().load(imageView, url);
    }

    @BindingAdapter(value = {"imageResId"})
    public static void mipmapSrc(ImageView imageView, int imageResId) {
        GlideImageLoader.getInstance().load(imageView, imageResId);
    }

    @BindingAdapter(value = {"imageUrl", "circle"})
    public static void loadImage(ImageView imageView, String url, boolean circle) {
        if (circle) {
            GlideImageLoader.getInstance().loadCircle(imageView, url);
        } else {
            GlideImageLoader.getInstance().load(imageView, url);
        }
    }
    @BindingAdapter(value = {"imageUrl","placeholder", "circle"})
    public static void loadImage(ImageView imageView, String url,int placeholder, boolean circle) {
        if (circle) {
            GlideImageLoader.getInstance().loadCircle(imageView, url,placeholder);
        } else {
            GlideImageLoader.getInstance().load(imageView, url,placeholder);
        }
    }



    @BindingAdapter(value = {"imageUrl", "radius"},requireAll = true)
    public static void loadImage(ImageView imageView, String url, int radius) {
        GlideImageLoader.getInstance().load(imageView, url,
                new CustomGlideTransform(false, radius, 0, 0));
    }

    @BindingAdapter(value = {"imageUrl", "radius", "stroke", "strokeColor"},requireAll = true)
    public static void loadImage(ImageView imageView, String url, int radius, int stroke, int strokeColor) {
        GlideImageLoader.getInstance().load(imageView, url,
                new CustomGlideTransform(false, radius, stroke, strokeColor));
    }


    @BindingAdapter(value = {"textDrawableLeft", "textDrawableTop", "textDrawableRight", "textDrawableBottom"}, requireAll = true)
    public static void textViewDrawable(TextView textView, int textDrawableLeft, int textDrawableTop, int textDrawableRight, int textDrawableBottom) {
        Drawable dl = null;
        Drawable dt = null;
        Drawable dr = null;
        Drawable db = null;
        if (textDrawableLeft != 0) {
            dl = textView.getContext().getResources().getDrawable(textDrawableLeft);
            dl.setBounds(0, 0, dl.getMinimumWidth(), dl.getMinimumHeight());
        }

        if (textDrawableTop != 0) {
            dt = textView.getContext().getResources().getDrawable(textDrawableLeft);
            dt.setBounds(0, 0, dt.getMinimumWidth(), dt.getMinimumHeight());
        }

        if (textDrawableRight != 0) {
            dr = textView.getContext().getResources().getDrawable(textDrawableLeft);
            dr.setBounds(0, 0, dr.getMinimumWidth(), dr.getMinimumHeight());
        }

        if (textDrawableBottom != 0) {
            db = textView.getContext().getResources().getDrawable(textDrawableLeft);
            db.setBounds(0, 0, db.getMinimumWidth(), db.getMinimumHeight());
        }
        textView.setCompoundDrawables(dl, dt, dr, db);
    }

    @BindingAdapter(value = {"textCanScroll"}, requireAll = false)
    public static void textCanScroll(View view, boolean textCanScroll) {
        if (view instanceof TextView) {
            ((TextView) (view)).setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }

    @BindingAdapter(value = {"backgroundResourceId"}, requireAll = false)
    public static void backgroundResourceId(View view, int backgroundResourceId) {
        if (backgroundResourceId != 0)
            view.setBackground(DrawableCompat.wrap(view.getResources().getDrawable(backgroundResourceId)));
        else
            view.setBackground(DrawableCompat.wrap(view.getResources().getDrawable(R.drawable.btn_click)));
    }

    @BindingAdapter(value = {"drawableLeft"}, requireAll = false)
    public static void drawableLeft(TextView textView, int drawableLeft) {
        if (drawableLeft != 0) {
            Drawable dl = textView.getContext().getResources().getDrawable(drawableLeft);
            dl.setBounds(0, 0, dl.getMinimumWidth(), dl.getMinimumHeight());
            textView.setCompoundDrawables(dl, null, null, null);
        }
    }


    @BindingAdapter(value = {"itemDrawableLeft"}, requireAll = false)
    public static void itemDrawableLeft(TextView textView, int itemDrawableLeft) {
        Drawable dl = textView.getContext().getResources().getDrawable(itemDrawableLeft);
        dl.setBounds(0, 0, dl.getMinimumWidth(), dl.getMinimumHeight());
        Drawable dr = textView.getContext().getResources().getDrawable(R.mipmap.ic_enter);
        dr.setBounds(0, 0, dr.getMinimumWidth(), dr.getMinimumHeight());
        textView.setCompoundDrawables(dl, null, dr, null);
    }

    @BindingAdapter(value = {"itemDrawableRight"}, requireAll = false)
    public static void itemDrawableRight(TextView textView, int itemDrawableRight) {
        Drawable dr = textView.getContext().getResources().getDrawable(itemDrawableRight);
        dr.setBounds(0, 0, dr.getMinimumWidth(), dr.getMinimumHeight());
        textView.setCompoundDrawables(null, null, dr, null);
    }

    @BindingAdapter(value = {"applyGlobalDebouncing"}, requireAll = false)
    public static void applyGlobalDebouncing(View view, View.OnClickListener clickListener) {
        ClickUtils.applyGlobalDebouncing(view, clickListener);
    }

    @BindingAdapter(value = {"applySingleDebouncing"}, requireAll = false)
    public static void applySingleDebouncing(View view, View.OnClickListener clickListener) {
        ClickUtils.applySingleDebouncing(view,500, clickListener);
    }

    @BindingAdapter(value = {"applyGlobalDebouncing", "applyGlobalDebouncingDuration"})
    public static void applyGlobalDebouncing(View view, View.OnClickListener clickListener, long duration) {
        ClickUtils.applyGlobalDebouncing(view, duration, clickListener);
    }

    @BindingAdapter(value = {"afterTextChangedStr"})
    public static void afterTextChangedStr(TextView textView, MutableLiveData<String> afterTextChangedStr) {
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (afterTextChangedStr != null)
                    afterTextChangedStr.setValue(s.toString());
            }
        });
    }

    @BindingAdapter(value = {"afterTextChangedStr"})
    public static void afterTextChangedStr(TextView textView, ObservableField<String> afterTextChangedStr) {
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (afterTextChangedStr != null)
                    afterTextChangedStr.set(s.toString());
            }
        });
    }

}
