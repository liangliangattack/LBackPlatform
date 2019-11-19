package com.liang.pro.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liang.pro.entity.AccessTokenDTO;
import com.liang.pro.entity.GithubUser;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 梁波 liangliangattack
 * @date 2019/9/17 8:36
 */
@Component
public class GithubProvider {

    private Logger log = LoggerFactory.getLogger(GithubProvider.class);

    public static final MediaType mediaType = MediaType.get("application/json; charset=utf-8");

    public String getAccessTokenDTO(AccessTokenDTO accessTokenDTO){
        //post请求
        OkHttpClient client = new OkHttpClient();
        String strToken = JSON.toJSONString(accessTokenDTO);
        RequestBody body = RequestBody.create(mediaType, strToken);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
                String responseData = response.body().string();
                String[] response2 = responseData.split("&");
                String[] response3 = response2[0].split("=");
                return response3[1];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //https://github.com/login/oauth/authorize?client_id=0e5beec3e6398c0c0ad6

    public GithubUser getUser(String accessToken){
        //GET请求
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            log.error("成功获取github user信息");
            log.error(response+"");
            String str = response.body().string();
            GithubUser githubUser = JSON.parseObject(str, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
