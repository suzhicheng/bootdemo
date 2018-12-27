package com.bootdemo.system.common.dict.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bootdemo.common.enums.CommonEnum;
import com.bootdemo.common.exception.BDException;
import com.bootdemo.system.common.dict.entity.DictEntity;
import com.bootdemo.system.common.dict.mapper1.DictMapper;
import com.bootdemo.system.common.dict.service.DictService;
import com.bootdemo.system.system.user.entity.UserEntity;


@Service
@Transactional
public class DictServiceImpl extends ServiceImpl<DictMapper, DictEntity> implements DictService{
    @Autowired
    private DictMapper dictMapper;

    /**
     * 根据id查询词典
     * @param id
     * @return
     */
    public DictEntity get(String id) {
        return dictMapper.selectById(id);
    }
    /**
     * 根据值和类型获取词典
     * @param map
     * @return
     */
    public DictEntity getByValueAndType(DictEntity dict){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("type", dict.getType());
    	map.put("value", dict.getValue());
    	return dictMapper.getByValueAndType(map);
    }

    /**
     * 获取词典列表数据
     * @param map
     * @return
     */
    public List<DictEntity> list(Map<String, Object> map) {
        return dictMapper.list(map);
    }
    /**
     * 根据code查询所有子节点
     * 只限用于地区代码
     * @param map
     * @return
     */
    public List<DictEntity> listByCode(Map<String, Object> map) {
    	DictEntity dict=dictMapper.getByValueAndType(map);
    	if(null == dict) {
    		throw new BDException("数据异常");
    	}
    	map.put("parentId", dict.getId());
    	if(map.get("type").toString().equals("province")) {
    		map.put("type", "city");
    	}else if(map.get("type").toString().equals("city")) {
    		map.put("type", "district");
    	}else if(map.get("type").toString().equals("courseClassifyOne")) {
    		map.put("type", "courseClassifyTwo");
    	}
        return dictMapper.list(map);
    }
    /**
     * 统计数量
     * @param map
     * @return
     */
    public int count(Map<String, Object> map) {
        return dictMapper.count(map);
    }
    
    /**
     * 新增词典
     * @param dict
     * @return
     */
    public int save(DictEntity dict) {
    	dict.setCreateDate(new Date());
    	dict.setUpdateDate(new Date());
    	dict.setDelFlag(CommonEnum.NO.getCode());
        return dictMapper.insert(dict);
    }

    /**
     * 修改词典
     * @param dict
     * @return
     */
    public int update(DictEntity dict) {
    	dict.setUpdateDate(new Date());
        return dictMapper.updateById(dict);
    }

    /**
     * 删除词典
     * @param id
     * @return
     */
    public int remove(String id) {
        return dictMapper.deleteById(id);
    }

    /**
     * 批量删除词典
     * @param ids
     * @return
     */
    public int batchRemove(String[] ids) {
        return dictMapper.deleteBatchIds(Arrays.asList(ids));
    }

    
    /**
     * 获取所有词典分类
     * @return
     */
    public List<DictEntity> listType() {
        return dictMapper.listType();
    }

    /**
     * 根据值和类型查询名称
     * @param type
     * @param value
     * @return
     */
    public String getName(String type, String value) {
        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("type", type);
        param.put("value", value);
        String rString = dictMapper.list(param).get(0).getName();
        return rString;
    }

    /**
     * 获取兴趣爱好
     * @param userDO
     * @return
     */
    public List<DictEntity> getHobbyList(UserEntity userDO) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "hobby");
        List<DictEntity> hobbyList = dictMapper.list(param);

        if (StringUtils.isNotEmpty(userDO.getHobby())) {
            String userHobbys[] = userDO.getHobby().split(";");
            for (String userHobby : userHobbys) {
                for (DictEntity hobby : hobbyList) {
                    if (!Objects.equals(userHobby, hobby.getId().toString())) {
                        continue;
                    }
                    hobby.setRemark("true");
                    break;
                }
            }
        }

        return hobbyList;
    }

    /**
     * 获取性别词典数据
     * @return
     */
    public List<DictEntity> getSexList() {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "sex");
        return dictMapper.list(param);
    }

    /**
     * 查询词典分类
     * @param type
     * @return
     */
    public List<DictEntity> listByType(String type) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", type);
        return dictMapper.list(param);
    }

    /**
     * 查询课程分类数据
     * @return
     */
	public List<DictEntity> listCourseClassify() {
		List<DictEntity> allList = new ArrayList<DictEntity>();
		Map<String,Object> courseClassifyOneParam=new HashMap<String,Object>();
		courseClassifyOneParam.put("type", "courseClassifyOne");
		List<DictEntity> dicts=dictMapper.list(courseClassifyOneParam);
		for(DictEntity dict : dicts) {
			allList.add(dict);
			Map<String,Object> courseClassifyTwoParam=new HashMap<String,Object>();
			courseClassifyTwoParam.put("type", "courseClassifyTwo");
			courseClassifyTwoParam.put("parentId", dict.getId());
			List<DictEntity> children=dictMapper.list(courseClassifyTwoParam);
			for(DictEntity dt : children) {
				allList.add(dt);
			}
		}
		return allList;
	}
	/**
	 * 根据ids查询数据
	 * @param ids
	 * @return
	 */
	public List<DictEntity> listByIds(String[] ids){
		return dictMapper.listByIds(ids);
	}

}
