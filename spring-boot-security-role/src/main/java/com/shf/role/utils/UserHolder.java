package com.shf.role.utils;

import com.shf.role.entity.TUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 描述：
 *
 * @Author shf
 * @Description TODO
 * @Date 2019/4/21 15:24
 * @Version V1.0
 **/
public class UserHolder {
    public static TUser getUserDetail(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        TUser user = (TUser) auth.getPrincipal();
        return user;
    }
    public static int getUserId(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        TUser user = (TUser) auth.getPrincipal();
        return user.getId();
    }
}
