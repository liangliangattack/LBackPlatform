package com.liang.pro.service;

import com.liang.pro.dto.UserDto;
import com.liang.pro.entity.LUser;

/**
 * @author 梁波 liangliangattack
 * @date 2019/10/17 16:13
 */
public interface LUserService {
    int addUser(LUser lUser);

    boolean checkUser(UserDto userDto);

    LUser login(UserDto userDto);

    LUser findUserByAccountId(String accountId);
}
