package com.server.edu.userservice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.server.edu.userservice.common.rest.PageResult;
import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.dao.DepartmentDao;
import com.server.edu.userservice.entity.Department;
import com.server.edu.userservice.service.DepartmentService;
import com.server.edu.userservice.util.CommonConstant;

/**
 * 
 * @Description:部门
 * @author:lifeiyue
 * @time:2018年6月8日 下午3:11:30
 */
@SuppressWarnings("all")
@Service
public class DepartmentServiceImpl implements DepartmentService
{
    private static Logger LOG = LoggerFactory.getLogger(
        "com.server.edu.userservice.service.impl.DepartmentServiceImpl");
    
    @Autowired
    private DepartmentDao departmentDao;
    
    @Override
    @Transactional
    public Result<Department> addDept(Department dept)
    {
        // 入参校验
        try
        {
            if (CommonConstant.isEmptyStr(dept.getDeptName()))
            {
                return Result.fail("Request parameter is error.");
            }
            
            Department dept1 = departmentDao.findDeptByName(dept.getDeptName());
            if (null != dept1)
            {
                return Result.fail("Department already exists.");
            }
            departmentDao.createOrEidtDept(dept);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData(dept);
    }
    
    @Override
    @Transactional
    public Result<Department> updateDept(Department dept)
    {
        try
        {
            if (dept.getDeptId() == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            Department dept1 = departmentDao.findDeptById(dept.getDeptId());
            if (null == dept1)
            {
                return Result.fail("Department does not exist.");
            }
            departmentDao.createOrEidtDept(dept);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData(dept);
    }
    
    @Override
    @Transactional
    public Result<Department> deleteDept(long deptId)
    {
        try
        {
            if (deptId == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            Department dept = departmentDao.findDeptById(deptId);
            if (null == dept)
            {
                return Result.fail("Department does not exist");
            }
            departmentDao.deleteDept(deptId);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData();
    }
    
    @Override
    @Transactional
    public Result<Department> getDept(Long deptId)
    {
        Department dept;
        try
        {
            if (deptId == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            dept = departmentDao.findDeptById(deptId);
            if (null == dept)
            {
                return Result.fail("Department does not exist");
            }
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData(dept);
    }
    
    @Override
    public Result<PageResult<Department>> findDeptList(Department dept)
    {
        PageResult pageResult;
        try
        {
            if (dept.getPageNum() == 0 || dept.getPageSize() == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            PageHelper.startPage(dept.getPageNum(), dept.getPageSize());
            Page<Department> page =
                (Page<Department>)departmentDao.findDetpList(dept);
            pageResult =
                new PageResult(dept.getPageNum(), dept.getPageSize(), page);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData(pageResult);
    }
    
    @Override
    public Result<List<Department>> findDeptChilds(long parentDeptId)
    {
        List<Department> deptList;
        try
        {
            if (parentDeptId == 0)
            {
                // 不传的时候取所有一级节点
                parentDeptId = -1l;
            }
            else
            {
                Department dept = departmentDao.findDeptById(parentDeptId);
                if (null == dept)
                {
                    return Result.fail("Department does not exist.");
                }
            }
            deptList = departmentDao.findDeptChilds(parentDeptId);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        
        return Result.successData(deptList);
    }
    
}
