package com.server.edu.userservice.service;

import java.util.List;

import com.server.edu.userservice.common.rest.PageResult;
import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.entity.Department;

public interface DepartmentService
{
    Result<Department> addDept(Department deptList);
    
    Result<Department> updateDept(Department dept);
    
    Result<Department> deleteDept(long deptId);
    
    Result<Department> getDept(Long deptId);
    
    Result<PageResult<Department>> findDeptList(Department dept);
    
    Result<List<Department>> findDeptChilds(long parentDeptId);
    
}
