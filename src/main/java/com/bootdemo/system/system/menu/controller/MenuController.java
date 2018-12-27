package com.bootdemo.system.system.menu.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdemo.common.annotation.Log;
import com.bootdemo.common.config.Constant;
import com.bootdemo.common.controller.BaseController;
import com.bootdemo.common.entity.Tree;
import com.bootdemo.common.utils.R;
import com.bootdemo.system.system.menu.entity.MenuEntity;
import com.bootdemo.system.system.menu.service.MenuService;

@RequestMapping("/sys/menu")
@Controller
public class MenuController extends BaseController {
	String prefix = "system/menu";
	@Autowired
	MenuService menuService;

	@RequiresPermissions("sys:menu:menu")
	@GetMapping()
	String menu(Model model) {
		return prefix+"/menu";
	}

	@RequiresPermissions("sys:menu:menu")
	@RequestMapping("/list")
	@ResponseBody
	List<MenuEntity> list(@RequestParam Map<String, Object> params) {
		List<MenuEntity> menus = menuService.list(params);
		return menus;
	}

	@Log("添加菜单")
	@RequiresPermissions("sys:menu:add")
	@GetMapping("/add")
	String add(Model model, @RequestParam("pId") String pId) {
		model.addAttribute("pId", pId);
		if ("".equals(pId)) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.selectById(pId).getName());
		}
		return prefix + "/add";
	}

	@Log("编辑菜单")
	@RequiresPermissions("sys:menu:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") String id) {
		MenuEntity mdo = menuService.selectById(id);
		String pId = mdo.getParentId();
		model.addAttribute("pId", pId);
		if ("".equals(pId)) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.selectById(pId).getName());
		}
		model.addAttribute("menu", mdo);
		return prefix+"/edit";
	}

	@Log("保存菜单")
	@RequiresPermissions("sys:menu:add")
	@PostMapping("/save")
	@ResponseBody
	R save(MenuEntity menu) {
		if (menuService.insert(menu)) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("更新菜单")
	@RequiresPermissions("sys:menu:edit")
	@PostMapping("/update")
	@ResponseBody
	R update(MenuEntity menu) {
		if (menuService.updateById(menu)) {
			return R.ok();
		} else {
			return R.error(1, "更新失败");
		}
	}

	@Log("删除菜单")
	@RequiresPermissions("sys:menu:remove")
	@PostMapping("/remove")
	@ResponseBody
	R remove(String id) {
		if (menuService.deleteById(id)) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}

	@GetMapping("/tree")
	@ResponseBody
	Tree<MenuEntity> tree() {
		Tree<MenuEntity>  tree = menuService.getTree();
		return tree;
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	Tree<MenuEntity> tree(@PathVariable("roleId") String roleId) {
		Tree<MenuEntity> tree = menuService.getTree(roleId);
		return tree;
	}
}
