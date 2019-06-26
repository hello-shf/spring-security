package com.shf.role.service;


import com.shf.role.entity.TUser;

/**
 * 描述：用户表服务类
 * @author: shf
 * @date: 2019-04-19 16:24:04
 * @version: V1.0
 */
public interface TUserService{
    /**
     * @param username
     * @return
     */
    public TUser findByName(String username);
}
