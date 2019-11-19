package com.liang.pro.service.impl;

import com.liang.pro.dto.UserDto;
import com.liang.pro.entity.LUser;
import com.liang.pro.mapper.LUserMapper;
import com.liang.pro.service.LUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author 梁波 liangliangattack
 * @date 2019/10/17 16:14
 */
@Service("lUserService")
public class LUserServiceImpl implements LUserService {

    @Autowired
    private LUserMapper lUserMapper;

    @Override
    public int addUser(LUser lUser) {
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());//github登录统一123456为密码
        lUser.setPassword(password);
        return lUserMapper.insert(lUser);
    }

    @Override
    public boolean checkUser(UserDto userDto) {
        return false;
    }

    @Override
    public LUser login(UserDto userDto) {
        return lUserMapper.findUserByAccountId(userDto.getUserName());
    }

    @Override
    public LUser findUserByAccountId(String accountId) {
        return lUserMapper.findUserByAccountId(accountId);
    }

}
