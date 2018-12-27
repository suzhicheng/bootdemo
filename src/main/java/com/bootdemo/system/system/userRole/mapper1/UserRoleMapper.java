package com.bootdemo.system.system.userRole.mapper1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bootdemo.system.system.userRole.entity.UserRoleEntity;

/**
 * 用户与角色对应关系
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity>{


	List<UserRoleEntity> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	List<String> listRoleId(String userId);

	int removeByUserId(String userId);

	int removeByRoleId(String roleId);

	int batchSave(List<UserRoleEntity> list);

	int batchRemoveByUserId(String[] userIds);
}
