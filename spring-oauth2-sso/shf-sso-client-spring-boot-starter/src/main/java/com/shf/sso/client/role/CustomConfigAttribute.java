package com.shf.sso.client.role;

import org.springframework.security.access.ConfigAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/6/24 17:01
 * @Version V1.0
 **/
public class CustomConfigAttribute implements ConfigAttribute {

    private final HttpServletRequest httpServletRequest;

    public CustomConfigAttribute(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public String getAttribute() {
        return null;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
}