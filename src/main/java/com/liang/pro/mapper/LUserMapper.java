package com.liang.pro.mapper;

import com.liang.pro.entity.LUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.MyMapper;

@Mapper
@Component("lUserMapper")
public interface LUserMapper extends MyMapper<LUser> {
    LUser findUserByAccountId(@Param("accountId") String accountId);
}