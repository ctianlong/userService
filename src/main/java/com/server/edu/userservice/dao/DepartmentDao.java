package com.server.edu.userservice.dao;

import java.util.List;

import com.github.pagehelper.Page;
import com.server.edu.userservice.entity.Department;

public interface DepartmentDao
{
    
    Department findDeptById(long deptId);
    
    Department findDeptByName(String deptName);
    
    void createOrEidtDept(Department dept);
    
    void deleteDept(long deptId);
    
    Page<Department> findDetpList(Department dept);
    
    List<Department> findDeptChilds(Long deptId);
    
}
