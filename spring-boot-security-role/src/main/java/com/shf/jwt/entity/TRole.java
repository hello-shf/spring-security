package com.shf.jwt.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/6/24 14:02
 * @Version V1.0
 **/
@Entity
@Table(name = "t_role")
@Data
public class TRole {
    @Id
    private Long id;

    private String available;

    private String description;

    private String role;

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="t_role_permission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<TPermission> permissions;

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name="t_user_role",joinColumns={@JoinColumn(name="id")},inverseJoinColumns={@JoinColumn(name="roleId")})
    private List<TUser> userInfos;// 一个角色对应多个用户
}
