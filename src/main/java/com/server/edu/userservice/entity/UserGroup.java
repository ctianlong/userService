package com.server.edu.userservice.entity;

import com.server.edu.userservice.common.rest.PageRequest;

/**
 * 
 * @Description:用户组
 * @author:lifeiyue
 * @time:2018年5月29日 下午4:59:06
 */
public class UserGroup extends PageRequest
{
    /**
     * 
     */
    private static final long serialVersionUID = 8350096996573723669L;
    
    private long groupId;
    
    private String groupName;
    
    private String description;
    
    private int status;
    
    private long createDate;
    
    private String createBy;
    
    public long getGroupId()
    {
        return groupId;
    }
    
    public void setGroupId(long groupId)
    {
        this.groupId = groupId;
    }
    
    public String getGroupName()
    {
        return groupName;
    }
    
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public int getStatus()
    {
        return status;
    }
    
    public void setStatus(int status)
    {
        this.status = status;
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
    
}
