package com.bootdemo.system.common.file.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bootdemo.common.config.SystemConfig;
import com.bootdemo.common.utils.FileUtil;
import com.bootdemo.common.utils.R;
import com.bootdemo.system.common.file.entity.FileEntity;
import com.bootdemo.system.common.file.mapper1.FileMapper;
import com.bootdemo.system.common.file.service.FileService;


@Service
@Transactional
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity> implements FileService {
	@Autowired
	private FileMapper fileMapper;

	@Autowired
	private SystemConfig systemConfig;
	/**
	 * 修改图片是否使用
	 * @param fileId
	 * @return
	 */
	public R updateQuote(String fileId,String type) {
		FileEntity sysFile=new FileEntity();
		sysFile.setId(fileId);
		sysFile.setIsQuote(type);
		int num = fileMapper.updateById(sysFile);
		return R.ok();
	}
	
	public List<FileEntity> list(Map<String, Object> map){
		return fileMapper.list(map);
	}
	
	
	public int count(Map<String, Object> map){
		return fileMapper.count(map);
	}
	
	
	/**
	 * 删除文件
	 * @param id
	 * @return
	 */
	public boolean remove(String id){
		FileEntity file = fileMapper.selectById(id);
		String fileName=null;
		boolean flag=false;
		if(file != null) {
			fileName=systemConfig.getUploadPath() + file.getUrl().replace("/files/", "");
		}
		if (fileMapper.deleteById(id) > 0) {
			FileUtil.deleteFile(fileName);
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 批量删除文件
	 * @param ids
	 * @return
	 */
	public R batchRemove(List<String> fileIds) {
		try {
			for(String fileId : fileIds) {
				String fileName = systemConfig.getUploadPath() + fileMapper.selectById(fileId).getUrl().replace("/files/", "");
				if (fileMapper.deleteById(fileId) > 0) {
					FileUtil.deleteFile(fileName);
				}
			}
			return R.ok();
		}catch(Exception e) {
			return R.error("批量删除文件失败");
		}
	}
    
    public Boolean isExist(String url) {
		Boolean isExist = false;
		if (!StringUtils.isEmpty(url)) {
			String filePath = url.replace("/files/", "");
			filePath = systemConfig.getUploadPath() + filePath;
			File file = new File(filePath);
			if (file.exists()) {
				isExist = true;
			}
		}
		return isExist;
	}
	}
