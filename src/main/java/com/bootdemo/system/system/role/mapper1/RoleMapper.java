package com.bootdemo.system.system.role.mapper1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bootdemo.system.system.role.entity.RoleEntity;

/**
 * 角色
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity>{

	
	List<RoleEntity> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
}
