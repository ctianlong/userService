package com.server.edu.userservice.entity;

import com.alibaba.fastjson.JSON;
import com.server.edu.userservice.common.rest.PageRequest;

/**
 * 
 * @Description:部门
 * @author:lifeiyue
 * @time:2018年6月7日 上午11:32:36
 */
public class Department extends PageRequest
{
    /**
     * 
     */
    private static final long serialVersionUID = 7957883640610824223L;
    
    private long deptId;
    
    private String deptCode;
    
    private String deptName;
    
    private String description;
    
    private long parentDeptId;
    
    private long createDate;
    
    private String createBy;
    
    private long lastUpdateDate;
    
    private String lastUpdateBy;
    
    public long getDeptId()
    {
        return deptId;
    }
    
    public void setDeptId(long deptId)
    {
        this.deptId = deptId;
    }
    
    public long getParentDeptId()
    {
        return parentDeptId;
    }
    
    public void setParentDeptId(long parentDeptId)
    {
        this.parentDeptId = parentDeptId;
    }
    
    public String getDeptName()
    {
        return deptName;
    }
    
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public long getCreateDate()
    {
        if (createDate == 0)
        {
            return System.currentTimeMillis();
        }
        return createDate;
    }
    
    public void setCreateDate(long createDate)
    {
        this.createDate = createDate;
    }
    
    public String getCreateBy()
    {
        return createBy;
    }
    
    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }
    
    public long getLastUpdateDate()
    {
        if (createDate == 0)
        {
            return System.currentTimeMillis();
        }
        return lastUpdateDate;
    }
    
    public void setLastUpdateDate(long lastUpdateDate)
    {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    public String getLastUpdateBy()
    {
        return lastUpdateBy;
    }
    
    public void setLastUpdateBy(String lastUpdateBy)
    {
        this.lastUpdateBy = lastUpdateBy;
    }
    
    public String getDeptCode()
    {
        return deptCode;
    }
    
    public void setDeptCode(String deptCode)
    {
        this.deptCode = deptCode;
    }
    
    @Override
    public String toString()
    {
        return JSON.toJSONString(this);
    }
    
}
