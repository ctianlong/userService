package com.server.edu.userservice.controller;

import java.io.IOException;
import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.edu.userservice.common.rest.PageResult;
import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.entity.Department;
import com.server.edu.userservice.service.DepartmentService;

@RestSchema(schemaId = "DepartmentController")
@RequestMapping("/dept")
public class DepartmentController
{
    private static Logger LOG = LoggerFactory.getLogger(
        "com.server.edu.userservice.controller.DepartmentController");
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private DepartmentService departmentService;
    
    /**
     * 
     * <新增部门>
     * @param dept_code,dept_name,parent_dept_code
     * @return String
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月8日 上午11:43:45
     */
    @RequestMapping(value = "/addDept", method = RequestMethod.POST)
    @ResponseBody
    public Result<Department> addDept(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("addDept.start  requestParam：    " + requestParam);
        /*  JavaType javaType = CommonConstant.getCollectionType(ArrayList.class, Department.class);
        List<Department> deptlist =  (List<Department>)mapper.readValue(requestParam, javaType); 
        */
        //List<Department> deptlist = JSON.parseArray(requestParam, Department.class);
        // Department deptList = JSON.parseObject(requestParam, Department.class);
        Department Dept = mapper.readValue(requestParam, Department.class);
        return departmentService.addDept(Dept);
    }
    
    /**
     * 
     * <修改部门>
     * @param requestParam
     * @return
     * @throws IOException
     * @author: lifeiyue
     * @time:2018年6月8日 下午2:35:21
     */
    @RequestMapping(value = "/updateDept", method = RequestMethod.PUT)
    @ResponseBody
    public Result<Department> updateDept(@RequestBody String requestParam)
        throws IOException
    {
        LOG.info("updateDept.start  requestParam：    " + requestParam);
        Department Dept = mapper.readValue(requestParam, Department.class);
        return departmentService.updateDept(Dept);
    }
    
    /**
     * 
     * <删除部门>
     * @param requestParam
     * @return
     * @throws IOException
     * @author: lifeiyue
     * @time:2018年6月8日 下午2:35:31
     */
    @RequestMapping(value = "/deleteDept", method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Department> deleteDept(@RequestBody String requestParam)
        throws IOException
    {
        LOG.info("deleteDept.start  requestParam：    " + requestParam);
        Department dept = mapper.readValue(requestParam, Department.class);
        return departmentService.deleteDept(dept.getDeptId());
    }
    
    /**
     * 
     * <获取部门详情>
     * @param DeptId
     * @return
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月8日 下午2:35:42
     */
    @RequestMapping(value = "/getDept/{deptId}", method = RequestMethod.GET)
    @ResponseBody
    public Result<Department> getDept(@PathVariable("deptId") Long deptId)
        throws Exception
    {
        LOG.info("getDept.start  deptId  ：" + deptId);
        return departmentService.getDept(deptId);
    }
    
    /**
     * 
     * <查询部门列表,一级部门>
     * @param requestParam
     * @return String
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月8日 下午2:36:06
     */
    @RequestMapping(value = "/findDeptList", method = RequestMethod.POST)
    @ResponseBody
    public Result<PageResult<Department>> findDeptList(
        @RequestBody String requestParam)
        throws Exception
    {
        LOG.info("findDeptList.start  requestParam：    " + requestParam);
        Department dept = mapper.readValue(requestParam, Department.class);
        return departmentService.findDeptList(dept);
    }
    
    /**
     * 
     * <获取当前部门的孩子>
     * @param deptId
     * @return String
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月8日 下午3:40:21
     */
    @RequestMapping(value = "/getDeptChild/{parentDeptId}", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Department>> getDeptChilds(
        @PathVariable("parentDeptId") long parentDeptId)
        throws Exception
    {
        LOG.info("getDeptChilds.start   deptId ： " + parentDeptId);
        Result<List<Department>> result =
            departmentService.findDeptChilds(parentDeptId);
        return result;
    }
    
}
