package com.bootdemo.system.system.menu.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.cache.annotation.Cacheable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bootdemo.common.entity.Tree;
import com.bootdemo.system.system.menu.entity.MenuEntity;

public interface MenuService extends IService<MenuEntity>{

	/**
	 * @param
	 * @return 树形菜单
	 */
	@Cacheable
	public Tree<MenuEntity> getSysMenuTree(String userId) ;

	
	public List<MenuEntity> list(Map<String, Object> params) ;

	public Tree<MenuEntity> getTree() ;

	
	public Tree<MenuEntity> getTree(String id) ;

	
	public Set<String> listPerms(String userId) ;

	
	public List<Tree<MenuEntity>> listMenuTree(String id) ;

}
