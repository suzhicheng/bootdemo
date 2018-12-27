package com.bootdemo.system.system.menu.mapper1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bootdemo.system.system.menu.entity.MenuEntity;

/**
 * 菜单管理
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuEntity>{
	
	List<MenuEntity> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	List<MenuEntity> listMenuByUserId(String userId);
	
	List<String> listUserPerms(String id);
}
