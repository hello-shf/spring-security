package com.shf.sso.server.user.repository;

import com.shf.sso.server.user.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/12/27 17:58
 * @Version V1.0
 **/
public interface SysUserRepository extends JpaSpecificationExecutor<SysUser>, JpaRepository<SysUser, Integer> {

    SysUser findByUsername(String username);
}
