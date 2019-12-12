package com.liang.pro.mapper;

import com.liang.pro.dto.QuestionDto;
import com.liang.pro.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.MyMapper;

@Mapper
@Component
public interface QuestionMapper extends MyMapper<Question> {
    void addQuestion(@Param("questionDto") QuestionDto questionDto);
}