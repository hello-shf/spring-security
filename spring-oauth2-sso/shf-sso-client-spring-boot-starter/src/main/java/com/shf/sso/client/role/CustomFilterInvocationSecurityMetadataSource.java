package com.shf.sso.client.role;

import com.shf.sso.client.properties.AuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(AuthProperties.class)
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private AuthProperties authProperties;
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
        String[] permitAll = authProperties.getPermitAll();
        for(String permit : permitAll){
            if(matchers(permit, request)){
                return null;
            }
        }
        //静态文件
        if (matchers("/images/**", request)
                || matchers("/api/**", request)
                || matchers("/v2/**", request)
                || matchers("/webjars/**", request)
                || matchers("/swagger-resources/**", request)
                || matchers("/", request)
                || matchers("/*.html", request)
                || matchers("/getVerifyCode", request)
                || matchers("/login_page", request)) {
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
