package com.bootdemo.system.system.roleMenu.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色菜单实体
 * 
 * @author suzhcihegn
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_sys_role_menu")
public class RoleMenuEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4873111676569082037L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 角色ID
	 */
	private String roleId;
	/**
	 * 菜单ID
	 */
	private String menuId;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	 
}
