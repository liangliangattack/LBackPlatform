package com.liang.pro.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.liang.pro.annotation.PassToken;
import com.liang.pro.annotation.UserLoginToken;
import com.liang.pro.entity.LUser;
import com.liang.pro.service.LUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author 梁波 liangliangattack
 * @date 2019/10/21 9:45
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    LUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    //为啥不能直接 丢错？
//                    throw new HttpResponseException(HttpStatusCode.Unauthorized);
                    //需要前台拦截 401 请求要求用户登录 浏览器的确显示 401 但是前段 catch error的时候catch不到。。
//                    httpServletResponse.setStatus(401);
                    //重定向
                    httpServletRequest.getRequestDispatcher("/author/error401").forward(httpServletRequest,httpServletResponse);

                    return false;
                }else {
                    // 获取 token 中的 user id
                    String accountId = "";
                    try {
                        accountId = JWT.decode(token).getAudience().get(0);
                    } catch (JWTDecodeException j) {
//                    throw new RuntimeException("401");
//                        httpServletResponse.setStatus(401);
                        //重定向
                        httpServletRequest.getRequestDispatcher("/author/error401").forward(httpServletRequest,httpServletResponse);
                        return false;
                    }
                    if (accountId != null && !StringUtils.isEmpty(accountId)) {
                        LUser user = userService.findUserByAccountId(accountId);
                        if (user == null) {
                            throw new RuntimeException("用户不存在，请重新登录");
                        }
                        // 验证 token
                        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                        try {
                            jwtVerifier.verify(token);
                        } catch (JWTVerificationException e) {
//                            httpServletResponse.setStatus(401);
                            //重定向
                            httpServletRequest.getRequestDispatcher("/author/error401").forward(httpServletRequest,httpServletResponse);
                            return false;
                        }
                    }
                }
                return true;

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
