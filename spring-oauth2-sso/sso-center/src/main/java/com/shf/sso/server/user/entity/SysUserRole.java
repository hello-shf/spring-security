package com.shf.sso.server.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/12/27 17:58
 * @Version V1.0
 **/
@Data
@Entity
@Table(schema = "permission", name = "sys_user_role")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -1810195806444298544L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_id")
    private Integer roleId;
}
