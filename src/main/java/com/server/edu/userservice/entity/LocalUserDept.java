package com.server.edu.userservice.entity;

import java.io.Serializable;

/**
 * 
 * @Description:本地用户与部门关系
 * @author:lizhiwei
 * @time:2018年6月12日 10:10:06
 */
public class LocalUserDept implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private long userId;
    
    private long deptId;
    
    private Object dept;
    
    public long getUserId()
    {
        return userId;
    }
    
    public void setUserId(long userId)
    {
        this.userId = userId;
    }
    
    public long getDeptId()
    {
        return deptId;
    }
    
    public void setDeptId(long deptId)
    {
        this.deptId = deptId;
    }
    
    public Object getDept()
    {
        return dept;
    }
    
    public void setDept(Object dept)
    {
        this.dept = dept;
    }
    
}
