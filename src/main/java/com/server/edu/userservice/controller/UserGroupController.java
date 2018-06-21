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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.entity.UserGroup;
import com.server.edu.userservice.entity.UserGroupRel;
import com.server.edu.userservice.entity.UserGroupRoleRel;
import com.server.edu.userservice.service.UserGroupService;

@RestSchema(schemaId = "UserGroupController")
@RequestMapping("/userGroup")
public class UserGroupController
{
    
    private static Logger LOG = LoggerFactory
        .getLogger("com.server.edu.userservice.controller.UserGroupController");
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private UserGroupService userGroupService;
    
    /**
     * 
     * <新增用户组>
     * @param group_name，description，status
     * @return String
     * @throws IOException 
     * @author: lifeiyue
     * @time:2018年6月5日 下午3:14:57
     */
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    @ResponseBody
    public Result<UserGroup> addGroup(@RequestBody String requestParam)
        throws IOException
    {
        LOG.info("addGroup.start  requestParam：    " + requestParam);
        UserGroup userGroup = mapper.readValue(requestParam, UserGroup.class);
        return userGroupService.addGroup(userGroup);
    }
    
    /**
     * 
     * <修改用户组>
     * @param group_id,description,status
     * @return String
     * @throws JsonProcessingException
     * @author: lifeiyue
     * @time:2018年6月5日 下午3:16:14
     */
    @RequestMapping(value = "/updateGroup", method = RequestMethod.PUT)
    @ResponseBody
    public Result<UserGroup> updateGroup(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("updateGroup.start  requestParam：    " + requestParam);
        UserGroup userGroup = mapper.readValue(requestParam, UserGroup.class);
        return userGroupService.updateGroup(userGroup);
    }
    
    /**
     * 
     * <删除用户组>
     * @param group_id
     * @return String
     * @throws JsonProcessingException
     * @author: lifeiyue
     * @time:2018年6月5日 下午3:16:56
     */
    @RequestMapping(value = "/deleteGroup", method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteGroup(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("deleteGroup.start  requestParam：    " + requestParam);
        UserGroup userGroup = mapper.readValue(requestParam, UserGroup.class);
        return userGroupService.deleteGroup(userGroup.getGroupId());
    }
    
    /**
     * 
     * <获取单个用户组的详细信息>
     * @param group_id
     * @return String
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月5日 下午5:42:45
     */
    @RequestMapping(value = "/getGroup/{group_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<UserGroup> getGroup(@PathVariable("group_id") Long groupId)
        throws Exception
    {
        LOG.info("getGroup.start  groupId  ：" + groupId);
        return userGroupService.findUserGroup(groupId);
    }
    
    /**
     * 
     * <查询用户组>
     * @param userGroup
     * @return String
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月1日 下午4:18:12
     */
    @RequestMapping(value = "/findGroupList", method = RequestMethod.POST)
    @ResponseBody
    public Result findGroupList(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("findGroupList.start  requestParam：    " + requestParam);
        UserGroup userGroup = mapper.readValue(requestParam, UserGroup.class);
        return userGroupService.findUserGroupList(userGroup);
    }
    
    /**
     * 
     * <批量绑定用户和用户组的关系>
     * @param group_id，user_id
     * @return String
     * @throws Exception 
     * @author: lifeiyue
     * @time:2018年5月31日 下午4:39:52
     */
    @RequestMapping(value = "/batchAddUserGroupRel", method = RequestMethod.POST)
    @ResponseBody
    public Result batchAddUserGroupRel(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("batchAddUserGroupRel.start  requestParam：    " + requestParam);
        UserGroupRel userGroupRel =
            mapper.readValue(requestParam, UserGroupRel.class);
        return userGroupService.batchAddUserGroupRel(userGroupRel);
    }
    
    /**
     * 
     * <批量删除用户与用户组关系>
     * @param group_id，user_id
     * @return String
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月5日 下午4:26:29
     */
    @RequestMapping(value = "/batchDelUserGroupRel", method = RequestMethod.DELETE)
    @ResponseBody
    public Result batchDelUserGroupRel(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("batchDelUserGroupRel.start  requestParam：    " + requestParam);
        UserGroupRel userGroupRel =
            mapper.readValue(requestParam, UserGroupRel.class);
        return userGroupService.batchDelUserGroupRel(userGroupRel);
    }
    
    /**
     * 
     * <获取用户和组的关系By用户组Id>
     * @param groupId
     * @return
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月8日 下午4:25:47
     */
    @RequestMapping(value = "/getUserRelListByGroupId/{groupId}", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<UserGroupRel>> getUserRelListByGroupId(
        @PathVariable("groupId") Long groupId)
        throws Exception
    {
        LOG.info("getUserRelListByGroupId.start  groupId：    " + groupId);
        return userGroupService.getUserRelListByGroupId(groupId);
    }
    
    /**
     * 
     * <角色批量新增用户组关系>
     * @param requestParam
     * @return String
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月6日 上午11:22:57
     */
    @RequestMapping(value = "/batchAddRoleGroupRel", method = RequestMethod.POST)
    @ResponseBody
    public Result batchAddRoleGroupRel(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("batchAddRoleGroupRel.start  requestParam：    " + requestParam);
        UserGroupRoleRel userGroupRoleRel =
            mapper.readValue(requestParam, UserGroupRoleRel.class);
        return userGroupService.batchAddRoleGroupRel(userGroupRoleRel);
    }
    
    /**
     * 
     * <角色批量删除用户组关系>
     * @param requestParam
     * @return String
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月6日 上午11:24:05
     */
    @RequestMapping(value = "/batchDelRoleGroupRel", method = RequestMethod.DELETE)
    @ResponseBody
    public Result batchDelRoleGroupRel(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("batchDelRoleGroupRel.start  requestParam：    " + requestParam);
        UserGroupRoleRel userGroupRoleRel =
            mapper.readValue(requestParam, UserGroupRoleRel.class);
        return userGroupService.batchDelRoleGroupRel(userGroupRoleRel);
    }
    
    /**
     * 
     * <获取角色和组的关系By用户组Id>
     * @param groupId
     * @return
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月8日 下午4:24:59
     */
    @RequestMapping(value = "/getRoleRelListByGroupId/{groupId}", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<UserGroupRoleRel>> getRoleRelListByGroupId(
        @PathVariable("groupId") Long groupId)
        throws Exception
    {
        LOG.info("getRoleRelListByGroupId.start");
        return userGroupService.getRoleRelListByGroupId(groupId);
    }
    
}
