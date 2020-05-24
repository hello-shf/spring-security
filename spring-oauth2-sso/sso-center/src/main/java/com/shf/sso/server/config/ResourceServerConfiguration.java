package com.shf.sso.server.config;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSON;
import com.shf.sso.server.security.properties.AuthProperties;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：oauth2资源服务器
 *
 * @Author shf
 * @Date 2019/7/9 11:30
 * @Version V1.0
 **/
@EnableConfigurationProperties(AuthProperties.class)
@Primary
@Slf4j
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    private AuthProperties authProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(authProperties.getPermitAll()).permitAll()
                .anyRequest()
                .authenticated()
                // 短信登录配置
                .and().csrf().disable();

//        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class);;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());
    }
    //自定义token验证异常响应
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) ->{
            Map<String, Object> map = new HashMap<>();
            map.put("code", 401);
            map.put("msg", "非法访问资源,访问此资源需要完全身份验证");
            map.put("path", request.getServletPath());
            map.put("timestamp", System.currentTimeMillis());
            response.setContentType("application/json");
            response.setCharacterEncoding(CharsetUtil.UTF_8);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getOutputStream(), map);
            } catch (Exception e) {
                throw new ServletException();
            }
        };
    }
    //自定义权限不足响应
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return (HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 401);
            map.put("msg", "抱歉,没有权限,请联系管理员");
            map.put("path", request.getServletPath());
            map.put("timestamp", System.nanoTime());
            response.setCharacterEncoding(CharsetUtil.UTF_8);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(JSON.toJSONString(map));
        };
    }
}
