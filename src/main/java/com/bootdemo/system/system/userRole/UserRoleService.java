package com.bootdemo.system.system.userRole;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdemo.system.system.userRole.entity.UserRoleEntity;
import com.bootdemo.system.system.userRole.mapper1.UserRoleMapper;
/**
 * 用户角色Service
 * @author suzhicheng
 *
 */
@Service
public class UserRoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;
	/**
	 * 根据用户ID删除数据
	 * @param userId
	 * @return
	 */
	public int removeByUserId(String userId) {
		return userRoleMapper.removeByUserId(userId);
	}
	/**
	 * 批量删除根据用户ids
	 * @param userIds
	 */
	public int batchRemoveByUserId(String[] userIds) {
		return userRoleMapper.batchRemoveByUserId(userIds);
	}
	/**
	 * 根据用户ID查询用户角色列表
	 * @param userId
	 * @return
	 */
	public List<String> listRoleId(String userId) {
		return userRoleMapper.listRoleId(userId);
	}
	/**
	 * 批量保存用户角色
	 * @param list
	 */
	public void batchSave(List<UserRoleEntity> list) {
		// TODO Auto-generated method stub
		
	}
}
