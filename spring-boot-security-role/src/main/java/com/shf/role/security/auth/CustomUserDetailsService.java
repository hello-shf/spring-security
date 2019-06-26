package com.shf.role.security.auth;

import com.shf.role.entity.TPermission;
import com.shf.role.entity.TRole;
import com.shf.role.entity.TUser;
import com.shf.role.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 认证过程中 - 根据登录信息获取用户详细信息
     *
     * @param s 登录用户输入的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户输入的用户信息，查询数据库中已注册用户信息
        TUser user = userService.findByName(s);
        //如果用户不存在直接抛出UsernameNotFoundException异常
        if (user == null) throw new UsernameNotFoundException("用户名为" + s + "的用户不存在");
        List<TRole> roles = user.getRoleList();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (TRole role:roles){
            List<TPermission> permissions = role.getPermissions();
            for (TPermission permission:permissions){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getUrl());
                // 此处将权限信息添加到 GrantedAuthority 对象中，在后面进行权限验证时会使用GrantedAuthority 对象
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return new CustomUserDetails(user, grantedAuthorities);
    }
}
