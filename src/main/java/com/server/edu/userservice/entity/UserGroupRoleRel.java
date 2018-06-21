package com.server.edu.userservice.entity;

import java.util.List;

/**
 * 
 * @Description:用户组和角色的关系
 * @author:lifeiyue
 * @time:2018年5月29日 下午4:58:45
 */
public class UserGroupRoleRel
{
    private long groupId;
    
    private long roleId;
    
    private List<Role> roleList;
    
    public List<Role> getRoleList()
    {
        return roleList;
    }
    
    public void setRoleList(List<Role> roleList)
    {
        this.roleList = roleList;
    }
    
    public long getGroupId()
    {
        return groupId;
    }
    
    public void setGroupId(long groupId)
    {
        this.groupId = groupId;
    }
    
    public long getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(long roleId)
    {
        this.roleId = roleId;
    }
    
}
