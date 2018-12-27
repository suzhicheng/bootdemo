package com.bootdemo.system.system.userRole.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色实体
 * @author suzhicheng
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_sys_user_role")
public class UserRoleEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7096481369307238733L;
	/**
	 * id
	 */
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 创建时间
     */
    private Date createDate;

}
