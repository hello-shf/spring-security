package com.shf.sso.client.handler;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：自定义Token异常信息
 *
 * @Author shf
 * @Date 2019/7/9 11:30
 * @Version V1.0
 **/
@Deprecated
public class CustomAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    /**
     * token错误时进入到这里
     *
     * @param request
     * @param response
     * @param authException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException {

        Map<String, Object> map = new HashMap<>();
        map.put("code", 401);
        map.put("msg", "非法访问资源,访问此资源需要完全身份验证");
        map.put("path", request.getServletPath());
        map.put("timestamp", System.currentTimeMillis());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
