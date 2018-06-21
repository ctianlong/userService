package com.server.edu.userservice.util;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommonConstant
{
    public final static ObjectMapper mapper = new ObjectMapper();
    
    /**
     * 菜单权限
     */
    public static final int REL_TYPE_1 = 1;
    
    /**
     * 部门
     */
    public static final int REL_TYPE_2 = 2;
    
    /**
     * <生成csv文件之前特殊字符转义>
     * 
     * @param str
     * @return String
     * @exception:
     * @author: lifeiyue
     * @time:2018年5月14日 下午3:32:50
     */
    public static String csvString(String str)
    {
        
        if (str.contains(","))
        {
            str = str.replace("\"", "\"\"");
            str = "\"" + str + "\"";
        }
        
        return str;
    }
    
    /**
     * 
     * <转义正则特殊字符>
     * 
     * @param keyword
     * @return String
     * @exception:
     * @author: lifeiyue
     * @time:2018年5月14日 下午5:23:18
     */
    public static String escapeExprSpecialWord(String keyword)
    {
        if (null != keyword && keyword.equals(""))
        {
            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]",
                "?", "^", "{", "}", "|"};
            for (String key : fbsArr)
            {
                if (keyword.contains(key))
                {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }
    
    /**
     * 判断是否为数字
     * 
     * @param str
     * @return
     */
    public static boolean isInteger(String str)
    {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
    
    public static boolean isEmptyStr(String str)
    {
        if (null == str || str.equals(""))
        {
            return true;
        }
        return false;
    }
    
    public static boolean isEmptyList(List list)
    {
        if (null == list || list.size() == 0)
        {
            return true;
        }
        return false;
    }
    
    public static boolean isEmptyObj(Object obj)
    {
        if (null == obj)
        {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * <获取泛型的Collection Type >
     * @param 泛型的Collection   
     * @param 元素类  
     * @return Java类型   
     * @author: lifeiyue
     * @time:2018年6月8日 上午11:13:43
     */
    public static JavaType getCollectionType(Class<?> collectionClass,
        Class<?>... elementClasses)
    {
        return mapper.getTypeFactory().constructParametricType(collectionClass,
            elementClasses);
    }
}
