package com.bootdemo.system.common.file.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bootdemo.common.utils.R;
import com.bootdemo.system.common.file.entity.FileEntity;


public interface FileService extends IService<FileEntity>{
	/**
	 * 修改图片是否使用
	 * @param fileId
	 * @return
	 */
	public R updateQuote(String fileId,String type) ;
	
	public List<FileEntity> list(Map<String, Object> map);
	
	
	public int count(Map<String, Object> map);
	
	
	
	/**
	 * 删除文件
	 * @param id
	 * @return
	 */
	public boolean remove(String id);
	
	/**
	 * 批量删除文件
	 * @param ids
	 * @return
	 */
	public R batchRemove(List<String> fileIds);

    
    public Boolean isExist(String url) ;
}
