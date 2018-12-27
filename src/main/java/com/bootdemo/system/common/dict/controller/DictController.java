package com.bootdemo.system.common.dict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdemo.common.controller.BaseController;
import com.bootdemo.common.utils.PageUtils;
import com.bootdemo.common.utils.Query;
import com.bootdemo.common.utils.R;
import com.bootdemo.system.common.dict.entity.DictEntity;
import com.bootdemo.system.common.dict.service.DictService;

/**
 * 字典表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */

@Controller
@RequestMapping("/common/sysDict")
public class DictController extends BaseController {
	@Autowired
	private DictService dictService;

	@GetMapping()
	@RequiresPermissions("common:sysDict:sysDict")
	public String dict() {
		return "common/dict/dict";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:sysDict:sysDict")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<DictEntity> dictList = dictService.list(query);
		int total = dictService.count(query);
		PageUtils pageUtils = new PageUtils(dictList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("common:sysDict:add")
	public String add() {
		return "common/dict/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:sysDict:edit")
	public String edit(@PathVariable("id") String id, Model model) {
		DictEntity dict = dictService.get(id);
		model.addAttribute("dict", dict);
		return "common/dict/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:sysDict:add")
	public R save(DictEntity dict) {
		dict.setCreateUser(getUserId());
		dict.setUpdateUser(getUserId());
		if (dictService.save(dict) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:sysDict:edit")
	public R update(DictEntity dict) {
		dict.setUpdateUser(getUserId());
		dictService.update(dict);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("common:sysDict:remove")
	public R remove(String id) {
		if (dictService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:sysDict:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids) {
		dictService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/type")
	@ResponseBody
	public List<DictEntity> listType() {
		return dictService.listType();
	};

	/**
	 *  类别已经指定增加
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/add/{id}")
	@RequiresPermissions("common:sysDict:add")
	public String addD(Model model, @PathVariable("id") String id) {
		model.addAttribute("parentId", id);
		return "common/dict/add";
	}
	/**
	 * 查询类别
	 * @param type
	 * @return
	 */
	@ResponseBody
	@GetMapping("/list/{type}")
	public List<DictEntity> listByType(@PathVariable("type") String type) {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", type);
		List<DictEntity> dictList = dictService.list(map);
		return dictList;
	}
	/**
	 * 根据父节点查询字典数据
	 * @param dict
	 * @return
	 */
	@RequestMapping(value = "/listByParentId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<DictEntity> listByParentId(@RequestBody DictEntity dict) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("parentId", dict.getParentId());
		map.put("type", dict.getType());
		List<DictEntity> dictList = dictService.list(map);
		return dictList;
	}
}
