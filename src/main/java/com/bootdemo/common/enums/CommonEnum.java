package com.bootdemo.common.enums;

public enum CommonEnum {
	/**
	 * 是
	 */
	YES("Y", "是"), 
	/**
	 * 否
	 */
	NO("N", "否"),
	/**
	 * 男
	 */
	MAN("M", "男"),
	/**
	 * 女
	 */
	WOMEN("W", "女"),
	/**
	 * 女
	 */
	C("C", "C端"),
	/**
	 * 男
	 */
	B("B", "B端");
	
	private String code;
	private String name;

	private CommonEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.code;
	}

	/**
	 * 通过code取得类型
	 * 
	 * @param code
	 * @return
	 */
	public static CommonEnum getType(String code) {
		for (CommonEnum type : CommonEnum.values()) {
			if (type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
