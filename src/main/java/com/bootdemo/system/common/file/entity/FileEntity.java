package com.bootdemo.system.common.file.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件上传
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_sys_file")
public class FileEntity implements Serializable {
	
    private static final long serialVersionUID = 1L;
    /**
     * 图片
     */
    public static final String FILETYPE_IMG="img";
    /**
     * 文档
     */
	public static final String FILETYPE_DOCUMENT="document";
	/**
	 * 视频
	 */
	public static final String FILETYPE_VIDEO="video";
	/**
	 * 音频
	 */
	public static final String FILETYPE_MUSIC="music";
	/**
	 * 机构图片
	 */
	public static final String UPLOAD_TYPE_COMPANYPIC="companyPic";
	/**
	 * 机构Logo
	 */
	public static final String UPLOAD_TYPE_COMPANYLOGO="companyLogo";
	/**
	 * 头像
	 */
	public static final String UPLOAD_TYPE_HEADIMG="headImg";
	/**
	 * 发现图片
	 */
	public static final String UPLOAD_TYPE_FINDER="finder";
	/**
	 * 课程图片
	 */
	public static final String UPLOAD_TYPE_COMPANYCOURSE="companyCourse";
	/**
	 * 营业执照
	 */
	public static final String UPLOAD_TYPE_BUSINESSLICENCE="businessLicence";
	/**
	 * 广告
	 */
	public static final String UPLOAD_TYPE_AD="ad";
    /**
     * id
     */
    private String id;
    /**
     * 文件类型
     */
    private String type;
    /**
     * URL地址
     */
    private String url;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
	 * 是否引用
	 */
	private String isQuote;
    
}
