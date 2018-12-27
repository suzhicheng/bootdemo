package com.bootdemo.system.common.log.mapper1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bootdemo.system.common.log.entity.LogEntity;

/**
 * 系统日志
 */
@Mapper
public interface LogMapper extends BaseMapper<LogEntity>{

	
	List<LogEntity> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	/**
	 * 根据用户id删除数据
	 * @param userId
	 * @return
	 */
	int removeByUserId(String userId);
}
