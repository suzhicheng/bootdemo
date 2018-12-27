package com.bootdemo.common.controller;

import org.springframework.stereotype.Controller;

import com.bootdemo.common.utils.ShiroUtils;
import com.bootdemo.system.system.user.entity.UserEntity;

@Controller
public class BaseController {
	public UserEntity getUser() {
		return ShiroUtils.getUser();
	}

	public String getUserId() {
		return getUser().getId();
	}

	public String getUserName() {
		return getUser().getUserName();
	}
}