package com.liang.pro.entity;

import java.util.Map;

public class Response2 {

    private String result;
    private String state;
    private String version;
    private String message;
    private String token;//用户信息
//    private List<Object> data;
    private Map<String,Object> data;
    private int page;
    private int pageNum;
    private int totalDataNum;
    private int pageSize;

    private boolean noPage;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void putAll(Map<String, Object> responese) {
        responese.put("result",getResult());
        responese.put("state",getState());
        responese.put("version",getVersion());
        responese.put("message",getMessage());
        responese.put("token",getToken());

        if(!noPage) {
            responese.put("page", getPage());
            responese.put("pageNum", getPageNum());
            responese.put("totalDataNum", getTotalDataNum());
            responese.put("pageSize", getPageSize());
        }
        responese.put("data",getData());
    }
}
