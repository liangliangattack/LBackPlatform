package com.liang.pro.util;

/**
 * 获取表名
 */
public class DynamicTN {

    public static String getDynamicTN(String companyId,String tableName){
        return companyId.equals("0") ? tableName : tableName+"_"+companyId ;
    }
}
