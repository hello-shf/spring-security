package com.shf.jwt.security.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 描述：访问未授权资源处理
 *
 * @Author shf
 * @Date 2019/6/28 10:27
 * @Version V1.0
 **/
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        if(e.getMessage().contains("Bad credentials")){
            out.write("{\"status\":\"error\",\"msg\":\"用户名或密码错误\"}");
        }else{
            out.write("{\"status\":\"error\",\"msg\":\"请登录\"}");
        }
        out.flush();
        out.close();
    }
}
