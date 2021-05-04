package com.fjsy.architecture.global.data.bean;

import com.blankj.utilcode.util.StringUtils;
import com.fjsy.architecture.R;
import com.fjsy.architecture.data.response.bean.BaseBean;

import java.io.Serializable;

public class ReceivePaperBean implements Serializable {

    public static final String RED_ENVELOPES = "red_envelopes";
    public static final String CONDOLENCE_RED_ENVELOPES = "condolence_red_envelopes";

    public UserBean receive_user;
    public UserBean from_user;

    public String receive_type;

    public String red_envelopes_id;

    public boolean isRedEnvelopes(){
        return RED_ENVELOPES.equals(receive_type);
    }

    public ReceivePaperBean() {

    }

    public ReceivePaperBean(UserBean receive_user, UserBean from_user, String receive_type, String red_envelopes_id) {
        this.receive_user = receive_user;
        this.from_user = from_user;
        this.receive_type = receive_type;
        this.red_envelopes_id = red_envelopes_id;
    }

    public static class UserBean implements Serializable {
        public String user_id;
        public String nickname;

        public UserBean() {
        }

        public UserBean(String user_id, String nickname) {
            this.user_id = user_id;
            this.nickname = nickname;
        }

        public String getNickname() {
            return user_id.equals(UserManager.getInstance().getUser().getId()) ? StringUtils.getString(R.string.you) : nickname;
        }

    }

}
