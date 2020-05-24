package com.shf.sso.server.user.service.impl;

import com.shf.sso.server.user.entity.SysUser;
import com.shf.sso.server.user.service.UserService;
import com.shf.sso.server.user.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/12/27 17:58
 * @Version V1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }
}
