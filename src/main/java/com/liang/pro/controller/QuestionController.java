package com.liang.pro.controller;

import com.liang.pro.annotation.PassToken;
import com.liang.pro.annotation.UserLoginToken;
import com.liang.pro.dto.QuestionDto;
import com.liang.pro.entity.BaseResult;
import com.liang.pro.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 梁波 liangliangattack
 * @date 2019/12/12 14:31
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ques")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @UserLoginToken
    @RequestMapping(value="/addQuestion",method = RequestMethod.POST)
    public BaseResult addQuestion(@RequestBody QuestionDto questionDto , HttpServletResponse response){
        questionService.addQuestion(questionDto);
        return BaseResult.ok("发起成功");
    }
}
