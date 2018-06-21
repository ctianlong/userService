package com.server.edu.userservice.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @Description:权限
 * @author:lizhiwei
 * @time:2018年6月6日 10:10:06
 */
public class AuthorityMenu implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private long authId;
    
    private String authNameEn;
    
    private String authNameCh;
    
    private long parentAuthId;
    
    private String urlPath;
    
    private String authCode;
    
    private int type;
    
    private long orderNo;
    
    private String application;
    
    public long getAuthId()
    {
        return authId;
    }
    
    public void setAuthId(long authId)
    {
        this.authId = authId;
    }
    
    public String getAuthNameEn()
    {
        return authNameEn;
    }
    
    public void setAuthNameEn(String authNameEn)
    {
        this.authNameEn = authNameEn;
    }
    
    public String getAuthNameCh()
    {
        return authNameCh;
    }
    
    public void setAuthNameCh(String authNameCh)
    {
        this.authNameCh = authNameCh;
    }
    
    public long getParentAuthId()
    {
        return parentAuthId;
    }
    
    public void setParentAuthId(long parentAuthId)
    {
        this.parentAuthId = parentAuthId;
    }
    
    public String getUrlPath()
    {
        return urlPath;
    }
    
    public void setUrlPath(String urlPath)
    {
        this.urlPath = urlPath;
    }
    
    public String getAuthCode()
    {
        return authCode;
    }
    
    public void setAuthCode(String authCode)
    {
        this.authCode = authCode;
    }
    
    public int getType()
    {
        return type;
    }
    
    public void setType(int type)
    {
        this.type = type;
    }
    
    public long getOrderNo()
    {
        return orderNo;
    }
    
    public void setOrderNo(long orderNo)
    {
        this.orderNo = orderNo;
    }
    
    public String getApplication()
    {
        return application;
    }
    
    public void setApplication(String application)
    {
        this.application = application;
    }
    
    public static void sort(List<AuthorityMenu> result)
    {
        SortHandler c = new SortHandler();
        c.keyMap = new HashMap<Integer, AuthorityMenu>();
        for (AuthorityMenu item : result)
        {
            c.keyMap.put((int)item.getAuthId(), item);
        }
        for (AuthorityMenu vo : result)
        {
            vo.setParent(c.keyMap.get(vo.getParentAuthId()));
        }
        Collections.sort(result, c);
    }
    
    @JsonIgnore
    private AuthorityMenu parent;
    
    @JsonIgnore
    public AuthorityMenu getParent()
    {
        return parent;
    }
    
    @JsonIgnore
    public void setParent(AuthorityMenu parent)
    {
        this.parent = parent;
    }
    
    public static class SortHandler implements Comparator<AuthorityMenu>
    {
        Map<Integer, AuthorityMenu> keyMap;
        
        public int compare(AuthorityMenu o1, AuthorityMenu o2)
        {
            
            AuthorityMenu v1 = o1;
            while (true)
            {
                AuthorityMenu v2 = o2;
                while (true)
                {
                    if (v1.getParent() == v2.getParent())
                    {
                        if (v1.getAuthCode() == null
                            || v2.getAuthCode() == null
                            || v1.getAuthCode().equals(v2.getAuthCode()))
                            return (int)(v1.getAuthId() - v2.getAuthId());
                        else
                            return Integer.parseInt(v1.getAuthCode())
                                - Integer.parseInt(v2.getAuthCode());
                    }
                    else if (v1 == v2.getParent())
                        return -1;
                    else if (v1.getParent() == v2)
                        return 1;
                    else
                        v2 = v2.getParent();
                    if (v2 == null)
                        break;
                }
                v1 = v1.getParent();
                if (v1 == null)
                    break;
            }
            return 0;
        }
    }
    
}
