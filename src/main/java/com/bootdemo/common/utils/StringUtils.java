package com.bootdemo.common.utils;

public class StringUtils extends org.apache.commons.lang3.StringUtils{
	 /**
     * 字符串反转
     * 
     * @param result
     * @return
     */
    public static String reverse(String result)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(result);
        return sb.reverse().toString();
    }
}
