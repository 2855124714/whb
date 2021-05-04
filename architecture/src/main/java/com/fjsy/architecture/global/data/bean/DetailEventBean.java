package com.fjsy.architecture.global.data.bean;

public class DetailEventBean {

    /**
     * 详情相关操作
     */
    //点赞
    public static final String DoLike = "DoLike";
    //收藏
    public static final String Collect = "Collect";
    //删除
    public static final String Delete = "Delete";

    //类型：从那个位置进入的详情
    public String type;
    //详情在列表的位置
    public int position;
    //详情的操作：点赞，收藏，删除
    public String operation;
    //操作的状态
    public boolean operationState;

    public DetailEventBean(String type, String operation, int position, boolean operationState) {
        this.type = type;
        this.position = position;
        this.operation = operation;
        this.operationState = operationState;
    }
}
