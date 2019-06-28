package com.shf.jwt.security.role;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/6/25 11:32
 * @Version V1.0
 **/
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    /**
     * 判定用户请求的url是否在权限表中，如果在权限表中，则返回给CustomAccessDecisionManager类的decide方法，用来判定用户是否有此权限。
     * 如果不在则返回null，跳过角色管理(decide方法)，直接访问。
     * 当然也可以在decide方法中判断该请求是否需要权限判定。
     *
     * 如果我们只有极个别的请求不需要鉴权，就不需要去查permission表了。如下所示
     * @param o 从该参数中能获取到请求的url，request对象
     * @return null 跳过decide方法
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) o).getRequest();
        //如果匹配到以下url，则不需要进行角色判断。
        if (matchers("/images/**", request)
                || matchers("/js/**", request)
                || matchers("/css/**", request)
                || matchers("/fonts/**", request)
                || matchers("/", request)
                || matchers("/login", request)
                || matchers("/getVerifyCode", request)) {
            return null;
        }
        Set<ConfigAttribute> allAttributes = new HashSet<>();
        ConfigAttribute configAttribute = new CustomConfigAttribute(request);
        allAttributes.add(configAttribute);
        return allAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
    private boolean matchers(String url, HttpServletRequest request) {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        if (matcher.matches(request)) {
            return true;
        }
        return false;
    }
}
