package com.fjsy.architecture.ui.binding_adapter;

import android.text.TextUtils;
import androidx.databinding.BindingAdapter;
import com.fjsy.architecture.ui.widget.BadgeImageView;

public class BadgeBindingAdapter {

    @BindingAdapter(value = {"textBadge"})
    public static void BadgeConfig(BadgeImageView view, String textBadge) {
//        Badge badge = new QBadgeView(view.getContext()).bindTarget(view)
//                .setBadgeText(textBadge).setBadgeTextSize(7,true)
//                .setBadgeTextColor(Color.WHITE).setBadgeBackgroundColor(ColorUtils.getColor(R.color.c_FB2E2A)).setBadgeGravity(Gravity.END | Gravity.TOP).setBadgePadding(2,true);
        view.showTextBadge(textBadge);
        if (!TextUtils.isEmpty(textBadge)&& !textBadge.equals("0")){

        } else {
            view.hiddenBadge();
        }
    }

}
