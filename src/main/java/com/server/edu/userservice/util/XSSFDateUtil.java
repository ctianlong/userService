package com.server.edu.userservice.util;

import java.util.Calendar;

import org.apache.poi.ss.usermodel.DateUtil;

/**
 * 
 * @Description:自定义xssf日期工具类
 * @author:lifeiyue
 * @time:2018年5月31日 下午5:44:07
 */
class XSSFDateUtil extends DateUtil
{
    protected static int absoluteDay(Calendar cal, boolean use1904windowing)
    {
        return DateUtil.absoluteDay(cal, use1904windowing);
    }
}