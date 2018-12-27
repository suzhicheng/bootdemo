package com.bootdemo.system.common.file.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdemo.common.config.SystemConfig;
import com.bootdemo.common.controller.BaseController;
import com.bootdemo.common.enums.CommonEnum;
import com.bootdemo.common.utils.FileType;
import com.bootdemo.common.utils.FileUtil;
import com.bootdemo.common.utils.PageUtils;
import com.bootdemo.common.utils.Query;
import com.bootdemo.common.utils.R;
import com.bootdemo.common.utils.UUIDUtils;
import com.bootdemo.system.common.file.entity.FileEntity;
import com.bootdemo.system.common.file.service.FileService;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
@Controller
@RequestMapping("/common/sysFile")
public class FileController extends BaseController {

	@Autowired
	private FileService sysFileService;

	@Autowired
	private SystemConfig systemConfig;

	@GetMapping()
	@RequiresPermissions("common:sysFile:sysFile")
	String sysFile(Model model) {
		Map<String, Object> params = new HashMap<>(16);
		return "common/file/file";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:sysFile:sysFile")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<FileEntity> sysFileList = sysFileService.list(query);
		int total = sysFileService.count(query);
		PageUtils pageUtils = new PageUtils(sysFileList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	// @RequiresPermissions("common:bComments")
	String add() {
		return "common/sysFile/add";
	}

	@GetMapping("/edit")
	// @RequiresPermissions("common:bComments")
	String edit(String id, Model model) {
		FileEntity sysFile = sysFileService.selectById(id);
		model.addAttribute("sysFile", sysFile);
		return "common/sysFile/edit";
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("common:info")
	public R info(@PathVariable("id") String id) {
		FileEntity sysFile = sysFileService.selectById(id);
		return R.ok().put("sysFile", sysFile);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:save")
	public R save(FileEntity sysFile) {
		if (sysFileService.insert(sysFile)) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("common:update")
	public R update(@RequestBody FileEntity sysFile) {
		sysFileService.updateById(sysFile);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	// @RequiresPermissions("common:remove")
	public R remove(String id, HttpServletRequest request) {
		if (sysFileService.remove(id) ) {
			return R.ok();
		} else {
			return R.error();
		}
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:remove")
	public R remove(@RequestParam("ids[]") String[] ids) {
		sysFileService.batchRemove(Arrays.asList(ids));
		return R.ok();
	}

	@ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file,@RequestParam("type") String type,HttpServletRequest request) {
		
		if(StringUtils.isBlank(type)) {
			return R.error("上传类型不能为空");
		}
		String prefix="";
		if(FileEntity.UPLOAD_TYPE_HEADIMG.equals(type)) {
			prefix="user/headImg/";
		}else if(FileEntity.UPLOAD_TYPE_COMPANYLOGO.equals(type)){
			prefix="company/logo/";
		}else if(FileEntity.UPLOAD_TYPE_COMPANYPIC.equals(type)){
			prefix="company/pic/";
		}else if(FileEntity.UPLOAD_TYPE_FINDER.equals(type)){
			prefix="finder/";
		}else if(FileEntity.UPLOAD_TYPE_COMPANYCOURSE.equals(type)){
			prefix="company/coures/";
		}else if(FileEntity.UPLOAD_TYPE_AD.equals(type)){
			prefix="ad/";
		}else if(FileEntity.UPLOAD_TYPE_BUSINESSLICENCE.equals(type)) {
			prefix="company/businesslicence/";
		}
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		FileEntity sysFile = new FileEntity(null,FileType.fileType(fileName), "/files/" + prefix+ fileName, new Date() ,CommonEnum.NO.getCode());
		try {
			FileUtil.uploadFile(file.getBytes(), systemConfig.getUploadPath()+prefix, fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.insert(sysFile)) {
			return R.ok().put("data",sysFile);
		}
		return R.error();
	}


}
