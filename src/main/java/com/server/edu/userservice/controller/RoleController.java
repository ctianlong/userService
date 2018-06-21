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
import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.entity.Role;
import com.server.edu.userservice.entity.RoleAuthorityRel;
import com.server.edu.userservice.entity.UserGroupRoleRel;
import com.server.edu.userservice.service.RoleService;

@RestSchema(schemaId = "RoleController")
@RequestMapping("/role")
public class RoleController
{
    
    private static Logger LOG = LoggerFactory
        .getLogger("com.server.edu.userservice.controller.RoleController");
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private RoleService roleService;
    
    /**
     * 
     * <新增角色>
     * @param role_name，description，start_time，end_time，status
     * @return String
     * @throws IOException
     * @author: lifeiyue
     * @time:2018年6月5日 下午4:34:38
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public Result<Role> addRole(@RequestBody String requestParam)
        throws IOException
    {
        LOG.info("addRole.start  requestParam:  " + requestParam);
        Role role = mapper.readValue(requestParam, Role.class);
        return roleService.addRole(role);
    }
    
    /**
     * 
     * <修改角色>
     * @param role_id,start_time，end_time，description，status
     * @return String
     * @throws IOException
     * @author: lifeiyue
     * @time:2018年6月5日 下午4:39:38
     */
    @RequestMapping(value = "/updateRole", method = RequestMethod.PUT)
    @ResponseBody
    public Result<Role> updateRole(@RequestBody String requestParam)
        throws IOException
    {
        LOG.info("updateRole.start  requestParam:   " + requestParam);
        Role role = mapper.readValue(requestParam, Role.class);
        return roleService.updateRole(role);
    }
    
    /**
     * 
     * <删除角色>
     * @param role_id
     * @return String
     * @throws IOException
     * @author: lifeiyue
     * @time:2018年6月5日 下午4:40:07
     */
    @RequestMapping(value = "/deleteRole", method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteRole(@RequestBody String requestParam)
        throws IOException
    {
        LOG.info("deleteRole.start  requestParam：    " + requestParam);
        Role role = mapper.readValue(requestParam, Role.class);
        return roleService.deleteRole(role.getRoleId());
    }
    
    /**
     * 
     * <查询单个角色的基本信息>
     * @param role_id
     * @return String
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月6日 上午11:19:20
     */
    @RequestMapping(value = "/getRole/{role_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<Role> getRole(@PathVariable("role_id") Long roleId)
        throws Exception
    {
        LOG.info("getRole.start  roleId  ：" + roleId);
        return roleService.findRole(roleId);
    }
    
    /**
     * 
     * <TODO>
     * @param requestParam
     * @return String
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月6日 上午11:19:45
     */
    @RequestMapping(value = "/findRoleList", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Role>> findRoleList(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("findRoleList.start  requestParam：    " + requestParam);
        Role role = mapper.readValue(requestParam, Role.class);
        return roleService.findRoleList(role);
    }
    
    /**
    * 
    * <角色批量新增权限>
    * @param requestParam
    * @return String
    * @throws Exception
    * @author: lifeiyue
    * @time:2018年6月8日 上午11:31:19
    */
    @RequestMapping(value = "/batchAddAuthToRoleRel", method = RequestMethod.POST)
    @ResponseBody
    public Result batchAddAuthToRoleRel(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("batchAddRoleGroupRel.start");
        RoleAuthorityRel roleAuthorityRel =
            mapper.readValue(requestParam, RoleAuthorityRel.class);
        return roleService.batchAddAuthToRoleRel(roleAuthorityRel);
    }
    
    /**
     * 
     * <角色批量删除权限>
     * @param requestParam
     * @return String
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月7日 下午2:13:44
     */
    @RequestMapping(value = "/batchDelAuthToRoleRel", method = RequestMethod.DELETE)
    @ResponseBody
    public Result batchDelAuthToRoleRel(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("batchDelRoleGroupRel.start");
        RoleAuthorityRel roleAuthorityRel =
            mapper.readValue(requestParam, RoleAuthorityRel.class);
        return roleService.batchDelAuthToRoleRel(roleAuthorityRel);
    }
    
    /**
     * 
     * <查询角色和权限的关系by角色Id>
     * @param roleId
     * @return
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月13日 上午9:30:36
     */
    @RequestMapping(value = "/getAuthRoleRelListByRoleId/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<RoleAuthorityRel>> getAuthRoleRelListByRoleId(
        @PathVariable("roleId") Long roleId)
        throws Exception
    {
        LOG.info("getRoleRelListByGroupId.start");
        return roleService.getAuthRoleRelListByRoleId(roleId);
    }
}
