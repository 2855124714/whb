package com.fjsy.architecture.utils.launchstater.task;

/**
 * Created by leo
 * on 2020/4/29.
 */
public abstract class MainTask extends Task {

    @Override
    public boolean runOnMainThread() {
        return true;
    }
}
