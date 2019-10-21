package com.liang.pro.service.impl;

import com.liang.pro.dto.UserDto;
import com.liang.pro.entity.LUser;
import com.liang.pro.mapper.LUserMapper;
import com.liang.pro.service.LUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 梁波 liangliangattack
 * @date 2019/10/17 16:14
 */
@Service("lUserService")
public class LUserServiceImpl implements LUserService {

    @Autowired
    private LUserMapper lUserMapper;

    @Override
    public void addUser(LUser lUser) {
        lUserMapper.insert(lUser);
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
