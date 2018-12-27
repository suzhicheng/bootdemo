package com.bootdemo.system.common.log.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bootdemo.common.entity.PageVO;
import com.bootdemo.common.utils.Query;
import com.bootdemo.system.common.log.entity.LogEntity;
import com.bootdemo.system.common.log.mapper1.LogMapper;
import com.bootdemo.system.common.log.service.LogService;


@Service
@Transactional
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogService{
	@Autowired
	LogMapper logMapper;

	@Async
	public void save(LogEntity logDO) {
		 logMapper.insert(logDO);
	}

	
	public PageVO<LogEntity> queryList(Query query) {
		int total = logMapper.count(query);
		List<LogEntity> logs = logMapper.list(query);
		PageVO<LogEntity> page = new PageVO<>();
		page.setTotal(total);
		page.setRows(logs);
		return page;
	}

	/**
	 * 根据用户id删除数据
	 * @param userId
	 * @return
	 */
	public int removeByUserId(String userId) {
		int count = logMapper.removeByUserId(userId);
		return count;
	}
}
