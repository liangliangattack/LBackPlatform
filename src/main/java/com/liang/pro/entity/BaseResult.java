package com.liang.pro.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 梁波 liangliangattack
 * @date 2019/10/17 10:02
 */
public class BaseResult implements Serializable {

    public static final String RESULT_OK = "ok";
    public static final String RESULT_NOT_OK = "not_ok";
    public static final String SUCCESS = "成功操作";

    private String result;
    private Object data;
    private String success;
    private Cursor cursor;
    private List<Error> errors;

    public static BaseResult ok(){
        return createResult(RESULT_OK,null,SUCCESS,null,null);
    }
    public static BaseResult ok(Object data){
        return createResult(RESULT_OK,data,SUCCESS,null,null);
    }

    public static BaseResult notOk(List<Error> errors){
        return createResult(RESULT_NOT_OK,null,"",null,errors);
    }

    private static BaseResult createResult(String result, Object data, String success , Cursor cursor, List<Error> errors){
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setSuccess(success);
        baseResult.setCursor(cursor);
        baseResult.setErrors(errors);

        return baseResult;
    }

    public static class Cursor {
        private int total;
        private int offset;
        private int limit;
    }

    public static class Error {
        private String field;
        private String message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
