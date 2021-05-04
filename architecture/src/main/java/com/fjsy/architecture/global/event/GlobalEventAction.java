package com.fjsy.architecture.global.event;

import com.fjsy.architecture.event.ClanEventAction;

public enum GlobalEventAction implements ClanEventAction {

    //发布文章
    PublishArticle,
    //微信支付
    WxPay,
    TrendsDoLike,
    TrendsCollect,
    TrendsDelete,
    ArticleDoLike,
    ArticleCollect,
    Trends,
    Article,
    DoLike,
    Collect,
    Download,
    LoginOut,
    UnReadMessage,
    //添加、删除好友
    FriendShipAddMessage,
    //聊天消息通知条数
    UnHandlerNoticeMessage,
    ClearChatRecord
    ;

}
