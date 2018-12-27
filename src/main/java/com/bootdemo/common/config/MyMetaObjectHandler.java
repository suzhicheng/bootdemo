package com.bootdemo.common.config;

import java.util.UUID;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.bootdemo.common.utils.UUIDUtils;

@Component
public class MyMetaObjectHandler extends MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		this.setFieldValByName("id", UUIDUtils.getUUID(), metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		// TODO Auto-generated method stub
		
	}
	
}
