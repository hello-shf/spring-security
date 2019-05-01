package com.shf.security.user.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 描述：用户表实体
 * @author: shf
 * @date: 2019-04-19 16:24:04
 * @version: V1.0
 */
@Data
@Entity
@Table(name = "t_user")
public class TUser {
	/**
	 * 主键
	 */
	@Id
	private Integer id;
	/**
	 * 用户编码
	 */

	private String code;
	/**
	 * 注册时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否删除 0：删除 1：未删除
	 */
	private Integer isDelete;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户角色
	 */
	private String role;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 邮箱
	 */
	private String email;
}
