package com.fjsy.architecture.utils;

import com.fjsy.architecture.event.ClanEvent;
import com.fjsy.architecture.event.ClanEventAction;

import org.greenrobot.eventbus.EventBus;

public class EventUtils {

    public static EventBus getDefaultBus() {
        return EventBus.getDefault();
    }

    public static void register(Object obj) {
        if (!getDefaultBus().isRegistered(obj)) {
            getDefaultBus().register(obj);
        }
    }

    public static void unregister(Object obj) {
        getDefaultBus().unregister(obj);
    }


    public static void post(ClanEventAction clanEventAction) {
        getDefaultBus().post(new ClanEvent(clanEventAction));
    }

    public static void postSticky(ClanEventAction clanEventAction) {
        getDefaultBus().postSticky(new ClanEvent(clanEventAction));
    }

    public static void postData(ClanEventAction clanEventAction, Object... data) {
        getDefaultBus().post(new ClanEvent(clanEventAction, data));
    }
}
