package com.shf.sso.client.handler;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：没有权限 授权失败时返回信息
 *
 * @Author shf
 * @Date 2019/7/9 11:30
 * @Version V1.0
 **/
@Deprecated
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 401);
        map.put("msg", "抱歉,没有权限,请联系管理员李浩东");
        map.put("path", request.getServletPath());
        map.put("timestamp", System.nanoTime());
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(JSON.toJSONString(map));
    }
}
