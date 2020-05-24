package com.shf.sso.server.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/12/25 16:41
 * @Version V1.0
 **/
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter(value = "/**")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        System.out.println(httpRequest.getRequestURL());
        Map<String, String[]> requestMap = httpRequest.getParameterMap();
        System.out.println("参数：" + JSON.toJSONString(requestMap));
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }

}
