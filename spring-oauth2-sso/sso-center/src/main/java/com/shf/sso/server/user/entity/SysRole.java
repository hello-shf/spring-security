package com.shf.sso.server.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/12/27 17:58
 * @Version V1.0
 **/
@Data
@Entity
@Table(schema = "permission", name = "sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = -7136537864183138269L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "role_description")
    private String roleDescription;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    private Date updateTime;
}
