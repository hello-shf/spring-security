package com.shf.jwt.security.role;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/6/25 10:39
 * @Version V1.0
 **/
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    /**
     * 判定是否拥有权限的决策方法
     * @param authentication CustomUserDetailsService类loadUserByUsername()方法中返回值
     * @param o 包含客户端发起的请求的request信息。
     * @param collection CustomFilterInvocationSecurityMetadataSource类的getAttribute()方法返回值
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        String url;
        for (GrantedAuthority ga : authentication.getAuthorities()) {
             url = ga.getAuthority();
             if(url.equals(request.getRequestURI())){
                return;
             }
        }
        throw new AccessDeniedException("没有权限访问");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
