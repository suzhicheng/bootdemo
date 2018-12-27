package com.bootdemo.system.system.roleMenu.mapper1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bootdemo.system.system.roleMenu.entity.RoleMenuEntity;

/**
 * 角色与菜单对应关系
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenuEntity>{

	
	List<RoleMenuEntity> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	List<String> listMenuIdByRoleId(String roleId);
	
	int removeByRoleId(String roleId);

	int removeByMenuId(String menuId);
	
	int batchSave(List<RoleMenuEntity> list);
}
