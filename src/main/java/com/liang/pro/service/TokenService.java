package com.liang.pro.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.liang.pro.entity.LUser;
import org.springframework.stereotype.Service;

/**
 * @author 梁波 liangliangattack
 * @date 2019/10/21 9:41
 */
@Service
public class TokenService {

    /**
     * token 生成
     * @param user
     * @return
     */
    public String getToken(LUser user) {
        String token="";
        token= JWT.create().withAudience(user.getAccountId())// 将 user 名字 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}
