package com.bootdemo.system.system.menu.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_sys_menu")
public class MenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	
	/**
	 *  父菜单ID，一级菜单为""
	 */
	private String parentId;
	/**
	 *  菜单名称
	 */
	private String name;
	/**
	 * 菜单URL
	 */
	private String url;
	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	private String perms;
	/**
	 *  类型 0：目录 1：菜单 2：按钮
	 */
	private String type;
	/**
	 *  菜单图标
	 */
	private String icon;
	/**
	 *  排序
	 */
	private Integer sortBy;
	/**
	 *  创建时间
	 */
	private Date createDate;
	/**
	 *  修改时间
	 */
	private Date updateDate;

}
