package com.liang.pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁波 liangliangattack
 * @date 2019/10/18 13:55
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/token")
public class TokenTestController {

    @RequestMapping(value = "getXX",method = RequestMethod.POST)
    public String getXX(){
        return "XXX";
    }
}
