package com.liang.pro.controller;

import com.liang.pro.annotation.UserLoginToken;
import com.liang.pro.dto.UserDto;
import com.liang.pro.entity.*;
import com.liang.pro.provider.GithubProvider;
import com.liang.pro.service.LUserService;
import com.liang.pro.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * @author 梁波 liangliangattack
 * @date 2019/9/17 8:36
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/author")
public class AuthorizeController {

    private static Logger log = LoggerFactory.getLogger(AuthorizeController.class);

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private LUserService lUserService;
    @Autowired
    private TokenService tokenService;

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(HttpServletResponse response){
        response.setStatus(401);
        return "你已通过验证";
    }

    /**
     * 没有权限的时候 用来返回 401
     * @param response
     * @return
     */
    @GetMapping("/error401")
    public String error401(HttpServletResponse response){
        response.setStatus(401);
        return "401 了解一下";
    }

    /**
     * github 三方登陆
     * https://github.com/login/oauth/authorize?client_id=0e5beec3e6398c0c0ad6&scope=user
     * @param code
     * @return
     */
    @RequestMapping(value = "/callback")
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
        GithubUser githubUser = githubProvider.getUser(access_token);
        if(githubUser != null){
            LUser lUser = new LUser();
            String token = UUID.randomUUID().toString();
            lUser.setToken(token);
            lUser.setName(githubUser.getLogin());
            lUser.setAccountId(String.valueOf(githubUser.getId()));
            lUser.setCreateTime(System.currentTimeMillis());

            lUserService.addUser(lUser);

            response.addCookie(new Cookie("token",token));
//            request.getSession().setAttribute("user",githubUser);
            return "redirect:http://localhost:8080";
        }
        return "redirect:http://localhost:8080";
    }

    @RequestMapping(value = "/login")
    public Map<String,Object> login(@RequestBody UserDto userDto){

        Response2 rep = new Response2();
        Map<String , Object> response = new HashMap<>();
        //user是否存在
        LUser user = lUserService.login(userDto);
        if(user != null){
        //加密
//        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            String password = userDto.getPassword();

            if (password.equals(user.getPassword())) {
                rep.setResult("ok");
                rep.setState("200");
                rep.setVersion("1");
                rep.setMessage("登陆成功");
                List<Object> list = new ArrayList<>();
                list.add(user);
                Map<String, Object> map = new HashMap<>();
                map.put("user", list);
                rep.setData(map);
                rep.setNoPage(true);
                //token -jwt 生成token
                rep.setToken(tokenService.getToken(user));
                rep.putAll(response);
                return response;
            }
        }
        rep.setResult("fail");
        rep.setState("400");
        rep.setVersion("1");
        rep.setMessage("登陆失败");
        rep.setData(null);
        rep.setNoPage(true);
        rep.putAll(response);
        return response;
    }
}
