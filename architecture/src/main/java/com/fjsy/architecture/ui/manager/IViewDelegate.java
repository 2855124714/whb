package com.fjsy.architecture.ui.manager;

import android.view.View;

import androidx.annotation.Keep;
import androidx.fragment.app.Fragment;

/**
 * <p>类说明</p>
 *
 * @author 张华洋 2018/1/4 22:10
 * @version V2.8.3
 * @name IFragmentDelegate
 */
@Keep
public interface IViewDelegate {

    Fragment getFragment(String name);

    View getView(String name);

}
