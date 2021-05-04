//package com.fjsy.architecture.global.file;
//
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.RequestManager;
//import com.bumptech.glide.request.RequestOptions;
//import com.zp.z_file.listener.ZFileImageListener;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.io.File;
//
///**
// * Created by ipy on 2021/3/5 15:43
// */
//class MyFileImageListener extends ZFileImageListener {
//
//    @Override
//    public void loadImage(@NotNull ImageView imageView, @NotNull File file) {
//        Glide.with(imageView.getContext())
//                .load(file)
//                .apply(RequestOptions().apply {
//            placeholder(R.drawable.ic_zfile_other)
//            error(R.drawable.ic_zfile_other)
//        })
//            .into(imageView)
//    }
//}