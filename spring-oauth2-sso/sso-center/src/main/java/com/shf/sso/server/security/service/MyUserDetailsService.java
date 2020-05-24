package com.shf.sso.server.security.service;

import com.alibaba.fastjson.JSON;
import com.shf.sso.server.security.entity.MyUser;
import com.shf.sso.server.user.entity.SysPermission;
import com.shf.sso.server.user.entity.SysUser;
import com.shf.sso.server.user.service.PermissionService;
import com.shf.sso.server.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 描述：自定义userdetailsService用于从数据库读取用户信息
 *
 * @Author shf
 * @Date 2019/7/9 11:30
 * @Version V1.0
 **/
@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getByUsername(username);
        if (null == sysUser) {
            log.warn("用户{}不存在", username);
            throw new UsernameNotFoundException(username);
        }
        List<SysPermission> permissionList = permissionService.findByUserId(sysUser.getId());
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(permissionList)) {
            for (SysPermission sysPermission : permissionList) {
                authorityList.add(new SimpleGrantedAuthority(sysPermission.getUri()));
            }
        }

        User myUser = new User(sysUser.getUsername(), passwordEncoder.encode(sysUser.getPassword()), authorityList);

        log.info("登录成功！用户: {}", JSON.toJSONString(myUser));

        return myUser;
    }
}
