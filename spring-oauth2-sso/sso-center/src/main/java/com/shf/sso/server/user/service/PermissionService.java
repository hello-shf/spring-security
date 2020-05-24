package com.shf.sso.server.user.service;

import com.shf.sso.server.user.entity.SysPermission;

import java.util.List;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/12/27 17:58
 * @Version V1.0
 **/
public interface PermissionService {

    List<SysPermission> findByUserId(Integer userId);

}
