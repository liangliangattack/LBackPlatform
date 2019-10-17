package com.liang.pro.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ResponseC {

    private String result;
    private String state;
    private String version;
    private String message;
    private List<Object> data;
    private List<Object> orderList;
//    private Map<String,Object> data;
    private int page;
    private int pageNum;
    private int totalDataNum;
    private int pageSize;

    private boolean noPage;
    private boolean XX;

    public List<Object> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Object> orderList) {
        this.orderList = orderList;
    }

    public boolean isXX() {
        return XX;
    }

    public void setXX(boolean XX) {
        this.XX = XX;
    }

    public boolean isNoPage() {
        return noPage;
    }

    public void setNoPage(boolean noPage) {
        this.noPage = noPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalDataNum() {
        return totalDataNum;
    }

    public void setTotalDataNum(int totalDataNum) {
        this.totalDataNum = totalDataNum;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public void putAll(Map<String, Object> responese , String tt ) {
        responese.put("result",getResult());
        responese.put("status",getState());
        responese.put("message",getMessage());

        Map<String ,Object> m1 = new HashMap<>();
        //菜单放前面
        if (XX) m1.put("orderList",orderList);
        if(!noPage) {
            m1.put("page", getPage());
            m1.put("pageNum", getPageNum());
            m1.put("totalDataNum", getTotalDataNum());
            m1.put("pageSize", getPageSize());
        }
        m1.put(tt,getData());

        responese.put("data",m1);

        responese.put("version",getVersion());

    }
}
