package com.shf.role.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/6/24 14:07
 * @Version V1.0
 **/
@Data
@Entity
@Table(name = "t_permission")
public class TPermission {
    @Id
    private long id;

    private String name;

    private String permission;

    private String url;
}
