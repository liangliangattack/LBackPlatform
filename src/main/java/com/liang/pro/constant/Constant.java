package com.liang.pro.constant;

public class Constant {

    //班次调度
    public static final int NO_ACHIEVE = -2;//时间未到
    public static final int NO_TASK = -1;
    public static final int NO_SHIFT = -3;
//    public static final int MORNING_SHIFT = 6; //早班
//    public static final int MIDDLE_SHIFT = 2;//中班
//    public static final int EVENING_NOSHIFT = 7;//晚班


    public static int HANDLE_SUCCESS = 1;//操作成功
    public static int HANDLE_FAIL = -1;//操作失败
    public static int HANDLE_OTHER = -2;//操作不成功，存在其他原因

    //MacAlarmDetail的比较使用
    public static int greater = 1;//大
    public static int EQUAL = 0;//相等
    public static int smaller = -1;//小
}
