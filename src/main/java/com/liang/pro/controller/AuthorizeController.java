package com.liang.pro.controller;

import com.liang.pro.entity.AccessTokenDTO;
import com.liang.pro.entity.GithubUser;
import com.liang.pro.provider.GithubProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author 梁波 liangliangattack
 * @date 2019/9/17 8:36
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/author")
public class AuthorizeController {

    private static Logger log = LoggerFactory.getLogger(AuthorizeController.class);

    @Autowired
    private GithubProvider githubProvider;

    /**
     * https://github.com/login/oauth/authorize?client_id=0e5beec3e6398c0c0ad6&scope=user
     * @param code
     * @return
     */
    @RequestMapping("/callback")
    public String callback(@RequestParam("code") String code ,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("0e5beec3e6398c0c0ad6");
        accessTokenDTO.setClient_secret("4d957f24bf8b55f4e95ec6209942b09d30d45766");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8230/author/callback");
//        accessTokenDTO.setState(state);
        String access_token = githubProvider.getAccessTokenDTO(accessTokenDTO);
        GithubUser user = githubProvider.getUser(access_token);
        if(user != null){

        }
        return "redirect:http://localhost:8080";
    }
}
