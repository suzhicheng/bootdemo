package com.bootdemo.system.system.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("t_user")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 禁用
	 */
	public static final String STATUS_OFF = "off";
	/**
	 * 启用
	 */
	public static final String STATUS_ON = "on";
	/**
	 * 用户ID
	 */
	@TableId(type=IdType.UUID)
	private String id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 用户真实姓名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 状态 
	 */
	private String status;
	/**
	 * 创建用户id
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 修改时间
	 */
	private Date updateDate;
	/**
	 * 角色
	 */
	@TableField(exist=false)
	private List<String> roleIds;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 出身日期
	 */
	private Date birth;
	/**
	 * 图片ID
	 */
	private String picId;
	/**
	 * 现居住地
	 */
	private String liveAddress;
	/**
	 * 爱好
	 */
	private String hobby;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 所在城市
	 */
	private String city;
	/**
	 * 所在地区
	 */
	private String district;
	/**
	 * 用户当前切换的客户端
	 */
	private String indexUserType;
	/**
	 * 最后登录IP
	 */
	private String lastLoginIp;
	/**
	 * 最后登录时间
	 */
	private String lastLoginDate;
	

}
