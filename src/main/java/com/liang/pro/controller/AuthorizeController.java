package com.liang.pro.controller;

import com.google.common.collect.Lists;
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
@Controller
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
    @ResponseBody
    public String getMessage(HttpServletResponse response){
        return "你已通过验证";
    }

    /**
     * 没有权限的时候 用来返回 401
     * @param response
     * @return
     */
    @GetMapping("/error401")
    @ResponseBody
    public String error401(HttpServletResponse response){
        response.setStatus(401);
        return "401 了解一下";
    }

    /**
     * github 三方登陆
     * 1.前端请求地址 https://github.com/login/oauth/authorize?client_id=0e5beec3e6398c0c0ad6
     * 2.github上已经设置回调的地址为当前接口
     * 3.通过回调回来的信息去获取 access_token
     * 4.通过 access_token 去获取 user信息
     * 5.重定向请求在后台发生，那github登录的token如何返回给前台知晓？
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

            //拿到用户之后 走登录或者注册流程
            UserDto userDto = new UserDto();
            userDto.setUserName(lUser.getAccountId());
            LUser user = lUserService.login(userDto);//user是否存在
            if(user != null){

            }else {
                lUserService.addUser(lUser);
            }

            request.getSession().setAttribute("user",githubUser);
//            response.addCookie(new Cookie("token",token));
            return "redirect:http://localhost:8080";
        }
        return "redirect:http://localhost:8080";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public BaseResult login(@RequestBody UserDto userDto){
        LUser user = lUserService.login(userDto);//user是否存在
        if(user != null){
            String password = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes());//md5
            if (password.equals(user.getPassword())) {
                return BaseResult.loginOk(user,tokenService.getToken(user));//token -jwt 生成token
            }
        }
        return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("login","登录失败")));
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public BaseResult register(@RequestBody UserDto userDto){
        LUser checkUser = lUserService.login(userDto);//user是否存在
        if(checkUser != null){
            return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("register","注册失败,账户已存在")));
        }
        LUser user = new LUser(null,userDto.getUserName(),userDto.getPassword(),userDto.getUserName(),UUID.randomUUID().toString(),
                System.currentTimeMillis(),null);
        if(lUserService.addUser(user) == 1){
            return BaseResult.ok(userDto.getUserName()+"->注册成功");
        }
        return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("register","注册失败")));
    }
}
