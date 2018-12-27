package com.bootdemo.system.system.user.mapper1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bootdemo.system.system.user.entity.UserEntity;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity>{

	
	List<UserEntity> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	List<UserEntity> listByPhone(Map<String, Object> params);
}
