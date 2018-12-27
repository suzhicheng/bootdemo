package com.bootdemo.system.common.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bootdemo.common.entity.PageVO;
import com.bootdemo.common.utils.Query;
import com.bootdemo.system.common.log.entity.LogEntity;


public interface LogService extends IService<LogEntity>{
	
	public void save(LogEntity logDO);
	
	public PageVO<LogEntity> queryList(Query query);

	/**
	 * 根据用户id删除数据
	 * @param userId
	 * @return
	 */
	public int removeByUserId(String userId);
}
