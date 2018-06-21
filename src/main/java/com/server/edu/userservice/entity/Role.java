package com.server.edu.userservice.entity;

import com.server.edu.userservice.common.rest.PageRequest;

/**
 * 
 * @Description:角色
 * @author:lifeiyue
 * @time:2018年6月1日 上午11:07:42
 */
public class Role extends PageRequest
{
    /**
     * 
     */
    private static final long serialVersionUID = -7330666209149898860L;
    
    private long roleId;
    
    private String roleName;
    
    private String description;
    
    private long createDate;
    
    private String createBy;
    
    private long lastUpdateDate;
    
    private String lastUpdateBy;
    
    private long startTime;
    
    private long endTime;
    
    private int status;
    
    public long getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(long roleId)
    {
        this.roleId = roleId;
    }
    
    public String getRoleName()
    {
        return roleName;
    }
    
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
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
    
    public long getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }
    
    public long getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(long endTime)
    {
        this.endTime = endTime;
    }
    
    public int getStatus()
    {
        return status;
    }
    
    public void setStatus(int status)
    {
        this.status = status;
    }
    
}
