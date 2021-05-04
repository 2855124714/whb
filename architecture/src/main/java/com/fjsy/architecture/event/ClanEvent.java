package com.fjsy.architecture.event;

public class ClanEvent {
        public ClanEventAction clanEventAction;
        public Object[] data;

        public ClanEvent(ClanEventAction key, Object... data) {
            this.clanEventAction = key;
            this.data = data;
        }
    }