package com.fjsy.architecture.global.route.chat;

public class ChatActivityPath {

    public static final String NewFriend = "/chat/new_friend";

    public static final String UserDetails = "/chat/user/detail";
    public static final String ChatComplaint = "/chat/complaint";
    public static final String Chat = "/chat/chat";
    public static final String MobileContacts = "/chat/friend/MobileContacts";
    public static final String ViewUser = "/chat/ViewUser";

    public static class Params {
        public static final String Content = "content";
        public static final String Id = "id";
        public static final String HeadUrl = "head";
        public static final String Nickname = "nickname";
        public static final String FriendPermission = "FriendPermission";
        public static final String Remark = "Remark";
        public static final String Type = "type";
        public static final String Group = "group";
        public static final String User = "user";
        public static final String SayHello = "SayHello";
        public static final String ChatInfo = "chatInfo";
        public static final String GroupInfo = "groupInfo";
    }
}