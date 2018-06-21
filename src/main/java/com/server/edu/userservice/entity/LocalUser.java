package com.server.edu.userservice.entity;

import java.io.Serializable;

/**
 * 
 * @Description:本地用户
 * @author:lizhiwei
 * @time:2018年6月12日 10:10:06
 */
public class LocalUser implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private long userId;
    
    private String userName;
    
    private String userPwd;
    
    private String email;
    
    private String mobilePhone;
    
    private String createBy;
    
    private long createDate;
    
    private String lastUpdateBy;
    
    private long lastUpdateDate;
    
    private String comment;
    
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
    
    public String getUserPwd()
    {
        return userPwd;
    }
    
    public void setUserPwd(String userPwd)
    {
        this.userPwd = userPwd;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getMobilePhone()
    {
        return mobilePhone;
    }
    
    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }
    
    public String getCreateBy()
    {
        return createBy;
    }
    
    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }
    
    public long getCreateDate()
    {
        return createDate;
    }
    
    public void setCreateDate(long createDate)
    {
        this.createDate = createDate;
    }
    
    public String getLastUpdateBy()
    {
        return lastUpdateBy;
    }
    
    public void setLastUpdateBy(String lastUpdateBy)
    {
        this.lastUpdateBy = lastUpdateBy;
    }
    
    public long getLastUpdateDate()
    {
        return lastUpdateDate;
    }
    
    public void setLastUpdateDate(long lastUpdateDate)
    {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    public String getComment()
    {
        return comment;
    }
    
    public void setComment(String comment)
    {
        this.comment = comment;
    }
    
}
