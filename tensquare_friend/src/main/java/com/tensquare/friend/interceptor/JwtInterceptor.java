package com.tensquare.friend.interceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tank
 * @create 2019/01/19 15:10
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        //无论如何都放行。具体能不能操作还是在具体的操作中去判断
        //拦截器只是复杂吧头请求头中包含token的令牌进行解析认证
        String header = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(header)) {
            //如果有包含head为Authorization的头进行，就对其进行解析
            if (header.startsWith("Bearer ")) {
                //得到token
                String token = header.substring(7);
                //对令牌进行验证
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (!StringUtils.isEmpty(roles) && roles.equals("admin")) {
                        request.setAttribute("claims_admin", claims);
                    }
                    if (!StringUtils.isEmpty(roles) && roles.equals("user")) {
                        request.setAttribute("claims_user", claims);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确！");
                }
            }
        }
        return true;
    }
}
