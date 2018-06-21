package com.server.edu.userservice.entity;

import java.io.Serializable;

/**
 * 
 * @Description:用户
 * @author:lifeiyue
 * @time:2018年5月28日 下午3:05:08
 */
public class User implements Serializable
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private long id;
    
    private long userId;
    
    private String userName;
    
    private String realName;
    
    private String address;
    
    private String aliaName;
    
    private String headPortRait;
    
    private int sex;
    
    private long telephone;
    
    private String identity;
    
    private long birthday;
    
    private String email;
    
    private int status;
    
    private String remarks;
    
    public long getId()
    {
        return id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public long getUserId()
    {
        return userId;
    }
    
    public void setUserId(long userId)
    {
        this.userId = userId;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getRealName()
    {
        return realName;
    }
    
    public void setRealName(String realName)
    {
        this.realName = realName;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getAliaName()
    {
        return aliaName;
    }
    
    public void setAliaName(String aliaName)
    {
        this.aliaName = aliaName;
    }
    
    public String getHeadPortRait()
    {
        return headPortRait;
    }
    
    public void setHeadPortRait(String headPortRait)
    {
        this.headPortRait = headPortRait;
    }
    
    public int getSex()
    {
        return sex;
    }
    
    public void setSex(int sex)
    {
        this.sex = sex;
    }
    
    public long getTelephone()
    {
        return telephone;
    }
    
    public void setTelephone(long telephone)
    {
        this.telephone = telephone;
    }
    
    public String getIdentity()
    {
        return identity;
    }
    
    public void setIdentity(String identity)
    {
        this.identity = identity;
    }
    
    public long getBirthday()
    {
        return birthday;
    }
    
    public void setBirthday(long birthday)
    {
        this.birthday = birthday;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public int getStatus()
    {
        return status;
    }
    
    public void setStatus(int status)
    {
        this.status = status;
    }
    
    public String getRemarks()
    {
        return remarks;
    }
    
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }
    
}
