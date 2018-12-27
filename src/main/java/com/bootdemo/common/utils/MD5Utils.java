package com.bootdemo.common.utils;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
 * MD5 算法
 */
public class MD5Utils
{
    private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    // 全局数组
    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
        "a", "b", "c", "d", "e", "f"};

    public MD5Utils()
    {}

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte)
    {
        int iRet = bByte;
        if (iRet < 0)
        {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte)
    {
        int iRet = bByte;
        logger.info("iRet1=" + iRet);
        if (iRet < 0)
        {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte)
    {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++ )
        {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    /**
     * 加密
     * 
     * @param strObj
     *            待加密字符
     * @return 加密后的密文字符串
     */
    public static String getMD5Sign(String strObj, String encoding)
    {
        String resultString = null;
        try
        {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组

            resultString = byteToString(md.digest(strObj.getBytes(encoding)));
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException ex)
        {
            logger.error("MD5Util:[getMD5Sign-NoSuchAlgorithmException][" + ex.getMessage() + "]");
        }
        return resultString;
    }

    /**
     * 加密
     * 
     * @param strObj
     *            待加密字符
     * @return 加密后的密文字符串
     */
    public static String getMD5Sign(String strObj)
    {
        String resultString = null;
        try
        {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        }
        catch (NoSuchAlgorithmException ex)
        {
            logger.error("MD5Util:[getMD5Sign-NoSuchAlgorithmException][" + ex.getMessage() + "]");
        }
        return resultString;

    }

    public static String getMD5SignBeforeEncoding(String strObj, String encoding)
    {
        String resultString = null;
        try
        {
            resultString = new String(strObj);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strObj.getBytes(encoding));
            String result = "";
            byte[] temp;
            temp = md5.digest();
            for (int i = 0; i < temp.length; i++ )
            {
                result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
            }
            return result;
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException ex)
        {
            logger.error("MD5Util:[getMD5SignBeforeEncoding-NoSuchAlgorithmException]["
                         + ex.getMessage() + "]");
        }
        return resultString;
    }

    // ------------------------
    public static final String encode(String s)
    {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F'};
        try
        {
            if ((s == null) || ("".equals(s)))
            {
                return null;
            }

            byte[] strTemp = s.getBytes("UTF-8");

            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++ )
            {
                byte b = md[i];

                str[(k++ )] = hexDigits[(b >> 4 & 0xF)];
                str[(k++ )] = hexDigits[(b & 0xF)];
            }
            return new String(str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static final String encode(String s, String charset)
    {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F'};
        try
        {
            if ((s == null) || ("".equals(s)))
            {
                return null;
            }

            byte[] strTemp = s.getBytes(charset);

            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++ )
            {
                byte b = md[i];

                str[(k++ )] = hexDigits[(b >> 4 & 0xF)];
                str[(k++ )] = hexDigits[(b & 0xF)];
            }
            return new String(str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    // ------------------------------
    public static void main(String[] args) {
		System.out.println(MD5Utils.encode("123456"));
	}
}