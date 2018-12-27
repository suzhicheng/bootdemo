package com.bootdemo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="system-config")
@Data
public class SystemConfig {
	/**
	 * 上传路径
	 */
	private String uploadPath;

}
