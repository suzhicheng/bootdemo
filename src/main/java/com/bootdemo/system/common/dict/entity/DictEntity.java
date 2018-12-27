package com.bootdemo.system.common.dict.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * 字典表
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_sys_dict")
public class DictEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 一级分类
	 */
	public static final String TYPE_COURSECLASSIFYONE="courseClassifyOne";
	/**
	 * 二级分类
	 */
	public static final String TYPE_COURSECLASSIFYTWO="courseClassifyTwo";
	/**
	 * 省
	 */
	public static final String TYPE_PROVINCE="province";
	/**
	 * 市
	 */
	public static final String TYPE_CITY="city";
	/**
	 * 区县
	 */
	public static final String TYPE_DISTRICT="district";
	/**
	 * 热门搜索
	 */
	public static final String TYPE_HOTSEARCH="hotSearch";
	/**
	 * 性别
	 */
	public static final String TYPE_SEX="sex";
	/**
	 * 编号
	 */
	private String id;
	/**
	 * 标签名
	 */
	private String name;
	/**
	 * 数据值
	 */
	private String value;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 排序（升序）
	 */
	private BigDecimal sortBy;
	/**
	 * 父级编号
	 */
	private String parentId;
	/**创建者
	 * 
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 更新者
	 */
	private String updateUser;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 删除标记
	 */
	private String delFlag;
	//非数据库字段
	/**
	 * 子词典
	 */
	@TableField(exist=false)
	private List<DictEntity> dicts;
	
}
