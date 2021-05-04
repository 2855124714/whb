package com.fjsy.architecture.global.data.bean;

import android.text.TextUtils;

import androidx.databinding.BaseObservable;

import com.fjsy.architecture.data.response.bean.BaseBean;

public class SettingExtendIndexBean extends BaseBean {

    public int search_by_userid;
    public int search_by_mobile;
    public int check_for_addfriend;
    public int receive_newmsg_push;
    public int recommend_mobile_list;
    public HideBean hide_family;
    public HideBean hide_clan;

    public boolean getSearch_by_userid() {
        notifyChange();
        return search_by_userid == 1;
    }

    public boolean getSearch_by_mobile() {
        notifyChange();
        return search_by_mobile == 1;
    }

    public boolean getCheck_for_addfriend() {
        notifyChange();
        return check_for_addfriend == 1;
    }

    public boolean getReceive_newmsg_push() {
        notifyChange();
        return receive_newmsg_push == 1;
    }

    public boolean getRecommend_mobile_list() {
        notifyChange();
        return recommend_mobile_list == 1;
    }

    public void setSearch_by_userid(boolean search_by_userid) {
        this.search_by_userid = search_by_userid ? 1 : 0;
    }

    public void setSearch_by_mobile(boolean search_by_mobile) {
        this.search_by_mobile = search_by_mobile ? 1 : 0;
    }

    public void setCheck_for_addfriend(boolean check_for_addfriend) {
        this.check_for_addfriend = check_for_addfriend ? 1 : 0;
    }

    public void setReceive_newmsg_push(boolean receive_newmsg_push) {
        this.receive_newmsg_push = receive_newmsg_push ? 1 : 0;
    }

    public void setRecommend_mobile_list(boolean recommend_mobile_list) {
        this.recommend_mobile_list = recommend_mobile_list ? 1 : 0;
    }

    public static class HideBean extends BaseObservable {
        //公历
        public String solar;
        //农历
        public String lunar;
        //时辰
        public String time;
        //个人视频
        public String video;
        //联系方式
        public String mobile;
        //工作/发展地点
        public String work;
        //现居
        public String current;
        //荣誉
        public String honour;
        //配偶/婚姻
        public String marriage;
        //儿子
        public String son;
        //女儿
        public String girl;
        //兄弟姐妹
        public String siblings;
        //儿媳妇
        public String sonwife;
        //女婿
        public String soninlaw;

        public boolean getSolarState() {
            notifyChange();
            return "1".equals(solar) || TextUtils.isEmpty(solar);
        }

        public void setSolarState(boolean solar) {
            notifyChange();
            this.solar = solar ? "1" : "0";
        }

        public boolean getLunarState() {
            notifyChange();
            return "1".equals(lunar) || TextUtils.isEmpty(lunar);
        }

        public void setLunarState(boolean lunar) {
            notifyChange();
            this.lunar = lunar ? "1" : "0";
        }

        public boolean getTimeState() {
            notifyChange();
            return "1".equals(time)|| TextUtils.isEmpty(time);
        }

        public void setTimeState(boolean time) {
            notifyChange();
            this.time = time ? "1" : "0";
        }

        public boolean getVideoState() {
            notifyChange();
            return "1".equals(video)|| TextUtils.isEmpty(video);
        }

        public void setVideoState(boolean video) {
            notifyChange();
            this.video = video ? "1" : "0";
        }

        public boolean getMobileState() {
            notifyChange();
            return "1".equals(mobile)|| TextUtils.isEmpty(mobile);
        }

        public void setMobileState(boolean mobile) {
            notifyChange();
            this.mobile = mobile ? "1" : "0";
        }

        public boolean getWorkState() {
            notifyChange();
            return "1".equals(work)|| TextUtils.isEmpty(work);
        }

        public void setWorkState(boolean work) {
            notifyChange();
            this.work = work ? "1" : "0";
        }

        public boolean getCurrentState() {
            notifyChange();
            return "1".equals(current)|| TextUtils.isEmpty(current);
        }

        public void setCurrentState(boolean current) {
            notifyChange();
            this.current = current ? "1" : "0";
        }

        public boolean getHonourState() {
            notifyChange();
            return "1".equals(honour)|| TextUtils.isEmpty(honour);
        }

        public void setHonourState(boolean honour) {
            notifyChange();
            this.honour = honour ? "1" : "0";
        }

        public boolean getMarriageState() {
            notifyChange();
            return "1".equals(marriage)|| TextUtils.isEmpty(marriage);
        }

        public void setMarriageState(boolean marriage) {
            notifyChange();
            this.marriage = marriage ? "1" : "0";
        }

        public boolean getSonState() {
            notifyChange();
            return "1".equals(son)|| TextUtils.isEmpty(son);
        }

        public void setSonState(boolean son) {
            notifyChange();
            this.son = son ? "1" : "0";
        }

        public boolean getGirlState() {
            notifyChange();
            return "1".equals(girl)|| TextUtils.isEmpty(girl);
        }

        public void setGirlState(boolean girl) {
            notifyChange();
            this.girl = girl ? "1" : "0";
        }

        public boolean getSiblingsState() {
            notifyChange();
            return "1".equals(siblings)|| TextUtils.isEmpty(siblings);
        }

        public void setSiblingsState(boolean siblings) {
            notifyChange();
            this.siblings = siblings ? "1" : "0";
        }

        public boolean getSonwifeState() {
            notifyChange();
            return "1".equals(sonwife)|| TextUtils.isEmpty(sonwife);
        }

        public void setSonwifeState(boolean sonwife) {
            notifyChange();
            this.sonwife = sonwife ? "1" : "0";
        }

        public boolean getSoninlawState() {
            notifyChange();
            return "1".equals(soninlaw)|| TextUtils.isEmpty(soninlaw);
        }

        public void setSoninlawState(boolean soninlaw) {
            notifyChange();
            this.soninlaw = soninlaw ? "1" : "0";
        }
    }

}
