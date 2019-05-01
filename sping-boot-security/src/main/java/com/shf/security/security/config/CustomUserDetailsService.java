package com.shf.security.security.config;

import com.shf.security.user.entity.TUser;
import com.shf.security.user.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 描述：自定义UserDetailsService，从数据库读取用户信息，实现登录验证
 *
 * @Author shf
 * @Date 2019/4/21 17:21
 * @Version V1.0
 **/
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private TUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TUser user = userService.findByName(s);
        if (user == null) throw new UsernameNotFoundException("Username " + s + " not found");
        return new CustomUserDetails(user);
    }
}
