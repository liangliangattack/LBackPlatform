package com.liang.pro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 梁波 liangliangattack
 * @date 2019/10/17 10:02
 */
@Data
public class BaseResult implements Serializable {

    public static final String RESULT_OK = "ok";
    public static final String RESULT_FAIL = "fail";

    private String result;
    private Object data;
    private String successMessage;
    private String token;//登录需要返回的token
    private Cursor cursor;
    private List<Error> errors;

    public static BaseResult loginOk(Object data,String token){
        return createLoginResult(RESULT_OK,data,"登录成功",token,null,null);
    }
    public static BaseResult ok(String successMessage){
        return createResult(RESULT_OK,null,successMessage,null,null);
    }
    public static BaseResult ok(Object data,String successMessage){
        return createResult(RESULT_OK,data,successMessage,null,null);
    }

    public static BaseResult notOk(List<Error> errors){
        return createResult(RESULT_FAIL,null,"",null,errors);
    }

    private static BaseResult createResult(String result, Object data, String successMessage , Cursor cursor, List<Error> errors){
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setSuccessMessage(successMessage);
        baseResult.setCursor(cursor);
        baseResult.setErrors(errors);

        return baseResult;
    }

    private static BaseResult createLoginResult(String result, Object data, String successMessage , String token , Cursor cursor, List<Error> errors){
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setSuccessMessage(successMessage);
        baseResult.setToken(token);
        baseResult.setCursor(cursor);
        baseResult.setErrors(errors);

        return baseResult;
    }

    @Data
    @AllArgsConstructor
    public static class Cursor {
        private int total;
        private int offset;
        private int limit;
    }

    @Data
    @AllArgsConstructor
    public static class Error {
        private String field;
        private String message;
    }
}
