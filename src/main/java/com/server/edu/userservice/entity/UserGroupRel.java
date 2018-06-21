package com.server.edu.userservice.entity;

import java.util.List;

/**
 * 
 * @Description:用户和用户组的关系
 * @author:lifeiyue
 * @time:2018年5月29日 下午4:58:45
 */
public class UserGroupRel
{
    private long userId;
    
    private long groupId;
    
    private int userType;
    
    private List<User> userList;
    
    public int getUserType()
    {
        return userType;
    }

    public void setUserType(int userType)
    {
        this.userType = userType;
    }

    public long getUserId()
    {
        return userId;
    }
    
    public void setUserId(long userId)
    {
        this.userId = userId;
    }
    
    public long getGroupId()
    {
        return groupId;
    }
    
    public void setGroupId(long groupId)
    {
        this.groupId = groupId;
    }
    
    public List<User> getUserList()
    {
        return userList;
    }
    
    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }
    
}
