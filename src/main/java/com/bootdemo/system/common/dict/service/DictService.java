package com.bootdemo.system.common.dict.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootdemo.common.enums.CommonEnum;
import com.bootdemo.common.exception.BDException;
import com.bootdemo.system.common.dict.entity.DictEntity;
import com.bootdemo.system.common.dict.mapper1.DictMapper;
import com.bootdemo.system.system.user.entity.UserEntity;

public interface DictService {

    /**
     * 根据id查询词典
     * @param id
     * @return
     */
    public DictEntity get(String id) ;
    /**
     * 根据值和类型获取词典
     * @param map
     * @return
     */
    public DictEntity getByValueAndType(DictEntity dict);

    /**
     * 获取词典列表数据
     * @param map
     * @return
     */
    public List<DictEntity> list(Map<String, Object> map);
    /**
     * 根据code查询所有子节点
     * 只限用于地区代码
     * @param map
     * @return
     */
    public List<DictEntity> listByCode(Map<String, Object> map) ;
    /**
     * 统计数量
     * @param map
     * @return
     */
    public int count(Map<String, Object> map);
    
    /**
     * 新增词典
     * @param dict
     * @return
     */
    public int save(DictEntity dict) ;

    /**
     * 修改词典
     * @param dict
     * @return
     */
    public int update(DictEntity dict) ;
    /**
     * 删除词典
     * @param id
     * @return
     */
    public int remove(String id) ;

    /**
     * 批量删除词典
     * @param ids
     * @return
     */
    public int batchRemove(String[] ids) ;

    
    /**
     * 获取所有词典分类
     * @return
     */
    public List<DictEntity> listType() ;

    /**
     * 根据值和类型查询名称
     * @param type
     * @param value
     * @return
     */
    public String getName(String type, String value) ;

    /**
     * 获取兴趣爱好
     * @param userDO
     * @return
     */
    public List<DictEntity> getHobbyList(UserEntity userDO) ;

    /**
     * 获取性别词典数据
     * @return
     */
    public List<DictEntity> getSexList() ;

    /**
     * 查询词典分类
     * @param type
     * @return
     */
    public List<DictEntity> listByType(String type) ;

    /**
     * 查询课程分类数据
     * @return
     */
	public List<DictEntity> listCourseClassify() ;
	/**
	 * 根据ids查询数据
	 * @param ids
	 * @return
	 */
	public List<DictEntity> listByIds(String[] ids);

}
