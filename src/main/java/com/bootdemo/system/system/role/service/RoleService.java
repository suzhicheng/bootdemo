package com.bootdemo.system.system.role.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bootdemo.system.system.role.entity.RoleEntity;


public interface RoleService extends IService<RoleEntity>{


    
    public List<RoleEntity> list() ;
    public List<RoleEntity> list(String userId) ;
    public int save(RoleEntity role) ;
    public int remove(String id) ;
    public int update(RoleEntity role);

}
