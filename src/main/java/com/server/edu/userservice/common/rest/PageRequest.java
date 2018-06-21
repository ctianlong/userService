package com.server.edu.userservice.common.rest;

import java.io.Serializable;

public class PageRequest implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 4184768387903573141L;
    
    private int pageNum = 1;
    
    private int pageSize = 10;
    
    public int getPageNum()
    {
        return pageNum;
    }
    
    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }
    
    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
}
