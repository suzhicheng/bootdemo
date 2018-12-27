package com.bootdemo.system.common.log.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdemo.common.entity.PageVO;
import com.bootdemo.common.utils.Query;
import com.bootdemo.common.utils.R;
import com.bootdemo.system.common.log.entity.LogEntity;
import com.bootdemo.system.common.log.service.LogService;

@RequestMapping("/common/log")
@Controller
public class LogController {
	@Autowired
	LogService logService;
	String prefix = "common/log";

	@GetMapping()
	String log() {
		return prefix + "/log";
	}

	@ResponseBody
	@GetMapping("/list")
	PageVO<LogEntity> list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageVO<LogEntity> page = logService.queryList(query);
		return page;
	}
	
	@ResponseBody
	@PostMapping("/remove")
	R remove(String id) {
		if (logService.deleteById(id)) {
			return R.ok();
		}
		return R.error();
	}

	@ResponseBody
	@PostMapping("/batchRemove")
	R batchRemove(@RequestParam("ids[]") String[] ids) {
		if (logService.deleteBatchIds(Arrays.asList(ids))) {
			return R.ok();
		}
		return R.error();
	}
}
