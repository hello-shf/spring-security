package com.shf.sso.server.config;

import com.shf.sso.server.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 描述：授权服务器 主要是配置客户端信息和认证信息
 *
 * @Author shf
 * @Date 2019/7/9 11:30
 * @Version V1.0
 **/
@Order(90)
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    /**
     * password 支持多种编码
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 这个方法是去数据库查询用户的密码，做权限验证
     *
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void config(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //设置UserDetailsService以及密码规则
        authenticationManagerBuilder
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}