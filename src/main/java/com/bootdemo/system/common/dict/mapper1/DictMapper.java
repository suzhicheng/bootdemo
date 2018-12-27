package com.bootdemo.system.common.dict.mapper1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bootdemo.system.common.dict.entity.DictEntity;

/**
 * 字典Mapper
 * @author suzhicheng
 *
 */
@Mapper
public interface DictMapper extends BaseMapper<DictEntity>{
	/**
	 * 根据值和类型查询
	 * @param map
	 * @return
	 */
	DictEntity getByValueAndType(Map<String, Object> map);
	/**
	 * 查询字典列表
	 * @param map
	 * @return
	 */
	List<DictEntity> list(Map<String, Object> map);
	/**
	 * 统计字典列表
	 * @param map
	 * @return
	 */
	int count(Map<String, Object> map);
	/**
	 * 获取字典类型
	 * @return
	 */
	List<DictEntity> listType();
	/**
	 * 根据ids查询数据
	 * @param ids
	 * @return
	 */
	List<DictEntity> listByIds(String[] ids);
	
	
}
