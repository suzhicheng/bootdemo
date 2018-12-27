package com.bootdemo.system.common.fonts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fontIcon")
public class FontIconController {
	
	@RequestMapping("FontIcoList")
	public String FontIcoList() {
		return "FontIcoList";
	}
}
