package com.bootdemo.system.system.role.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 角色实体
 * @author suzhicheng
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_sys_role")
public class RoleEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2303103845786345123L;
	/**
	 * 角色ID
	 */
	private String id;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色签名
	 */
	private String roleSign;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建用户
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
	//非数据库字段
	/**
	 * 菜单
	 */
	@TableField(exist=false)
	private List<String> menuIds;

	
}
