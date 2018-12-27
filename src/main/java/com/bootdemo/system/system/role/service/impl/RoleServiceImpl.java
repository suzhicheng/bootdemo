package com.bootdemo.system.system.role.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bootdemo.common.utils.UUIDUtils;
import com.bootdemo.system.system.role.entity.RoleEntity;
import com.bootdemo.system.system.role.mapper1.RoleMapper;
import com.bootdemo.system.system.role.service.RoleService;
import com.bootdemo.system.system.roleMenu.entity.RoleMenuEntity;
import com.bootdemo.system.system.roleMenu.mapper1.RoleMenuMapper;
import com.bootdemo.system.system.user.mapper1.UserMapper;
import com.bootdemo.system.system.userRole.mapper1.UserRoleMapper;


@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    
    public List<RoleEntity> list() {
        List<RoleEntity> roles = roleMapper.list(new HashMap<>(16));
        return roles;
    }
    
    public List<RoleEntity> list(String userId) {
        List<String> rolesIds = userRoleMapper.listRoleId(userId);
        List<RoleEntity> roles = roleMapper.list(new HashMap<>(16));
        for (RoleEntity roleDO : roles) {
            roleDO.setRoleSign("false");
            for (String roleId : rolesIds) {
                if (roleId.equals(roleDO.getId())) {
                    roleDO.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }
    @Transactional
    public int save(RoleEntity role) {
    	role.setCreateDate(new Date());
    	role.setUpdateDate(new Date());
        int count = roleMapper.insert(role);
        List<String> menuIds = role.getMenuIds();
        String roleId = role.getId();
        List<RoleMenuEntity> rms = new ArrayList<>();
        for (String menuId : menuIds) {
            RoleMenuEntity rmDo = new RoleMenuEntity();
            rmDo.setId(UUIDUtils.getUUID());
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rmDo.setCreateDate(new Date());
            rms.add(rmDo);
        }
        roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return count;
    }

    @Transactional
    
    public int remove(String id) {
        int count = roleMapper.deleteById(id);
        userRoleMapper.removeByRoleId(id);
        roleMenuMapper.removeByRoleId(id);
        return count;
    }
    
    
    public int update(RoleEntity role) {
        int r = roleMapper.updateById(role);
        List<String> menuIds = role.getMenuIds();
        String roleId = role.getId();
        roleMenuMapper.removeByRoleId(roleId);
        List<RoleMenuEntity> rms = new ArrayList<>();
        for (String menuId : menuIds) {
            RoleMenuEntity rmDo = new RoleMenuEntity();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rmDo.setId(UUIDUtils.getUUID());
            rmDo.setCreateDate(new Date());
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return r;
    }

}
