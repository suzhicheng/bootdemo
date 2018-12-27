package com.bootdemo.system.common.file.mapper1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bootdemo.system.common.file.entity.FileEntity;

/**
 * 文件上传
 */
@Mapper
public interface FileMapper extends BaseMapper<FileEntity>{

	List<FileEntity> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
}
