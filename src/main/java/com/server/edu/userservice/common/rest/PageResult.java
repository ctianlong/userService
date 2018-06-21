package com.server.edu.userservice.common.rest;

import java.io.Serializable;

import com.github.pagehelper.Page;

public class PageResult<T> implements Serializable
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 8705055252190710351L;
    
    private int pageNum = 1;
    
    private int pageSize = 10;
    
    private long total;
    
    private Object list;
    
    public PageResult(Page<T> page)
    {
        this.total = page.getTotal();
        this.list = page.getResult();
    }
    
    public PageResult(int pageNum, int pageSize, Page<T> page)
    {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = page.getTotal();
        this.list = page.getResult();
    }
    
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
    
    public long getTotal()
    {
        return total;
    }
    
    public void setTotal(long total)
    {
        this.total = total;
    }
    
    public Object getList()
    {
        return list;
    }
    
    public void setList(Object list)
    {
        this.list = list;
    }
    
}
