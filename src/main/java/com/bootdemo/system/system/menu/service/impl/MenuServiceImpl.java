package com.bootdemo.system.system.menu.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bootdemo.common.entity.Tree;
import com.bootdemo.common.utils.BuildTree;
import com.bootdemo.system.system.menu.entity.MenuEntity;
import com.bootdemo.system.system.menu.mapper1.MenuMapper;
import com.bootdemo.system.system.menu.service.MenuService;
import com.bootdemo.system.system.roleMenu.mapper1.RoleMenuMapper;

@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService{
	@Autowired
	MenuMapper menuMapper;
	@Autowired
	RoleMenuMapper roleMenuMapper;

	/**
	 * @param
	 * @return 树形菜单
	 */
	@Cacheable
	public Tree<MenuEntity> getSysMenuTree(String userId) {
		List<Tree<MenuEntity>> trees = new ArrayList<Tree<MenuEntity>>();
		List<MenuEntity> menuDOs = menuMapper.listMenuByUserId(userId);
		for (MenuEntity sysMenuDO : menuDOs) {
			Tree<MenuEntity> tree = new Tree<MenuEntity>();
			tree.setId(sysMenuDO.getId());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuEntity> t = BuildTree.build(trees);
		return t;
	}

	
	public List<MenuEntity> list(Map<String, Object> params) {
		List<MenuEntity> menus = menuMapper.list(params);
		return menus;
	}

	public Tree<MenuEntity> getTree() {
		List<Tree<MenuEntity>> trees = new ArrayList<Tree<MenuEntity>>();
		List<MenuEntity> menuDOs = menuMapper.list(new HashMap<>(16));
		for (MenuEntity sysMenuDO : menuDOs) {
			Tree<MenuEntity> tree = new Tree<MenuEntity>();
			tree.setId(sysMenuDO.getId());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuEntity> t = BuildTree.build(trees);
		return t;
	}

	
	public Tree<MenuEntity> getTree(String id) {
		// 根据roleId查询权限
		List<MenuEntity> menus = menuMapper.list(new HashMap<String, Object>(16));
		List<String> menuIds = roleMenuMapper.listMenuIdByRoleId(id);
		List<String> temp = menuIds;
		for (MenuEntity menu : menus) {
			if (temp.contains(menu.getParentId())) {
				menuIds.remove(menu.getParentId());
			}
		}
		List<Tree<MenuEntity>> trees = new ArrayList<Tree<MenuEntity>>();
		List<MenuEntity> menuDOs = menuMapper.list(new HashMap<String, Object>(16));
		for (MenuEntity sysMenuDO : menuDOs) {
			Tree<MenuEntity> tree = new Tree<MenuEntity>();
			tree.setId(sysMenuDO.getId());
			tree.setParentId(sysMenuDO.getParentId());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> state = new HashMap<>(16);
			String menuId = sysMenuDO.getId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuEntity> t = BuildTree.build(trees);
		return t;
	}

	
	public Set<String> listPerms(String userId) {
		List<String> perms = menuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	
	public List<Tree<MenuEntity>> listMenuTree(String id) {
		List<Tree<MenuEntity>> trees = new ArrayList<Tree<MenuEntity>>();
		List<MenuEntity> menuDOs = menuMapper.listMenuByUserId(id);
		for (MenuEntity sysMenuDO : menuDOs) {
			Tree<MenuEntity> tree = new Tree<MenuEntity>();
			tree.setId(sysMenuDO.getId());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<MenuEntity>> list = BuildTree.buildList(trees, "");
		return list;
	}

}
