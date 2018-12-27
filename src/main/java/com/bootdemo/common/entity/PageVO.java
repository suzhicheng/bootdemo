package com.bootdemo.common.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class PageVO<T> {

	private int offset=0;
	private int limit=10;
	private int total=1;
	private Map<String, Object> params;
	private String param;
	private List<T> rows;
}
