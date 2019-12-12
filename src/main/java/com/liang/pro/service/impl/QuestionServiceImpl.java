package com.liang.pro.service.impl;

import com.liang.pro.dto.QuestionDto;
import com.liang.pro.mapper.QuestionMapper;
import com.liang.pro.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 梁波 liangliangattack
 * @date 2019/12/12 14:41
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void addQuestion(QuestionDto questionDto) {
        questionMapper.addQuestion(questionDto);
    }
}
