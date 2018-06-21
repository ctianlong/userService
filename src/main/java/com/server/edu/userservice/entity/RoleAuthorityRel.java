package com.server.edu.userservice.entity;

import java.io.Serializable;

/**
 * 
 * @Description:角色权限关联表
 * @author:lizhiwei
 * @time:2018年6月6日 10:10:06
 */
public class RoleAuthorityRel implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private long roleId;
    
    private long valueId;
    
    private int type;
    
    private Object auth;
    
    public Object getAuth()
    {
        return auth;
    }
    
    public void setAuth(Object auth)
    {
        this.auth = auth;
    }
    
    public long getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(long roleId)
    {
        this.roleId = roleId;
    }
    
    public int getType()
    {
        return type;
    }
    
    public void setType(int type)
    {
        this.type = type;
    }
    
    public long getValueId()
    {
        return valueId;
    }
    
    public void setValueId(long valueId)
    {
        this.valueId = valueId;
    }
    
}
