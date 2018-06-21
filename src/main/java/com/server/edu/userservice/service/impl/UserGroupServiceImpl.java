
package com.server.edu.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.server.edu.userservice.common.rest.PageResult;
import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.dao.UserGroupDao;
import com.server.edu.userservice.entity.Role;
import com.server.edu.userservice.entity.User;
import com.server.edu.userservice.entity.UserGroup;
import com.server.edu.userservice.entity.UserGroupRel;
import com.server.edu.userservice.entity.UserGroupRoleRel;
import com.server.edu.userservice.service.UserGroupService;
import com.server.edu.userservice.util.CommonConstant;

@SuppressWarnings("all")
@Service
public class UserGroupServiceImpl implements UserGroupService
{
    private static Logger LOG = LoggerFactory.getLogger(
        "com.server.edu.userservice.service.impl.UserGroupServiceImpl");
    
    @Autowired
    private UserGroupDao userGroupDao;
    
    @Autowired
    private JdbcTemplateService jdbcTemplateService;
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Override
    @Transactional
    public Result<UserGroup> addGroup(UserGroup userGroup)
    {
        try
        {
            if (CommonConstant.isEmptyStr(userGroup.getGroupName()))
            {
                return Result.fail("Request parameter is error.");
            }
            UserGroup userGroup1 =
                userGroupDao.findGroupByName(userGroup.getGroupName());
            if (null != userGroup1)
            {
                return Result.fail("User group already exists.");
            }
            userGroupDao.createOrEditGroup(userGroup);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData(userGroup);
    }
    
    @Override
    @Transactional
    public Result<UserGroup> updateGroup(UserGroup userGroup)
    {
        try
        {
            if (userGroup.getGroupId() == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            UserGroup userGroup1 =
                userGroupDao.findGroupById(userGroup.getGroupId());
            if (null == userGroup1)
            {
                return Result.fail("User group does not exist.");
            }
            userGroupDao.createOrEditGroup(userGroup);
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
    public Result deleteGroup(long group_id)
    {
        try
        {
            if (group_id == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            UserGroup userGroup1 = userGroupDao.findGroupById(group_id);
            if (null == userGroup1)
            {
                return Result.fail("User group does not exist.");
            }
            userGroupDao.deleteGroup(group_id);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData();
    }
    
    @Override
    public Result<UserGroup> findUserGroup(Long groupId)
    {
        UserGroup userGroup;
        try
        {
            if (groupId == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            userGroup = userGroupDao.findGroupById(groupId);
            if (null == userGroup)
            {
                return Result.fail("User group does not exist.");
            }
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData(userGroup);
    }
    
    @Override
    public Result<PageResult<UserGroup>> findUserGroupList(UserGroup userGroup)
    {
        PageResult pageResult;
        try
        {
            if (userGroup.getPageNum() == 0 || userGroup.getPageSize() == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            PageHelper.startPage(userGroup.getPageNum(),
                userGroup.getPageSize());
            Page<UserGroup> page =
                (Page<UserGroup>)userGroupDao.findUserGroupList(userGroup);
            pageResult = new PageResult(userGroup.getPageNum(),
                userGroup.getPageSize(), page);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        
        return Result.successData(pageResult);
    }
    
    @Override
    @Transactional
    public Result batchAddUserGroupRel(UserGroupRel userGroupRel)
    {
        UserGroup userGroup;
        try
        {
            if (userGroupRel.getGroupId() == 0
                || CommonConstant.isEmptyList(userGroupRel.getUserList()))
            {
                return Result.fail("Request parameter is error.");
            }
            
            userGroup = userGroupDao.findGroupById(userGroupRel.getGroupId());
            if (null == userGroup)
            {
                return Result.fail("User group does not exist.");
            }
            List<UserGroupRel> userGroupRelList = new ArrayList<UserGroupRel>();
            for (User user : userGroupRel.getUserList())
            {
                UserGroupRel rel = new UserGroupRel();
                rel.setUserId(user.getUserId());
                rel.setGroupId(userGroupRel.getGroupId());
                rel.setUserType(userGroupRel.getUserType());
                userGroupRelList.add(rel);
            }
            userGroupDao.createUserGroupRelList(userGroupRelList);
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
    public Result batchDelUserGroupRel(UserGroupRel userGroupRel)
    {
        try
        {
            if (userGroupRel.getGroupId() == 0
                || CommonConstant.isEmptyList(userGroupRel.getUserList()))
            {
                return Result.fail("Request parameter is error.");
            }
            List<UserGroupRel> userGroupRelList = new ArrayList<UserGroupRel>();
            for (User user : userGroupRel.getUserList())
            {
                UserGroupRel rel = new UserGroupRel();
                rel.setUserId(user.getUserId());
                rel.setGroupId(userGroupRel.getGroupId());
                userGroupRelList.add(rel);
            }
            jdbcTemplateService.batchDelUserGroupRel(userGroupRelList);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData();
    }
    
    @Override
    public Result<List<UserGroupRel>> getUserRelListByGroupId(Long groupId)
    {
        List<UserGroupRel> userGroupRelList;
        try
        {
            if (groupId == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            UserGroup userGroup = userGroupDao.findGroupById(groupId);
            if (null == userGroup)
            {
                return Result.fail("User group does not exist.");
            }
            userGroupRelList = userGroupDao.getUserRelListByGroupId(groupId);
        }
        catch (Exception e)
        {
            return Result.error(e.getMessage());
        }
        return Result.successData(userGroupRelList);
    }
    
    @Override
    @Transactional
    public Result batchAddRoleGroupRel(UserGroupRoleRel userGroupRoleRel)
    {
        try
        {
            if (userGroupRoleRel.getGroupId() == 0
                || CommonConstant.isEmptyList(userGroupRoleRel.getRoleList()))
            {
                return Result.fail("Request parameter is error.");
            }
            UserGroup userGroup =
                userGroupDao.findGroupById(userGroupRoleRel.getGroupId());
            if (null == userGroup)
            {
                return Result.fail("User group not exist");
            }
            List<UserGroupRoleRel> roleGroupRelList =
                new ArrayList<UserGroupRoleRel>();
            for (Role role : userGroupRoleRel.getRoleList())
            {
                UserGroupRoleRel rel = new UserGroupRoleRel();
                rel.setGroupId(userGroupRoleRel.getGroupId());
                rel.setRoleId(role.getRoleId());
                roleGroupRelList.add(rel);
            }
            userGroupDao.createRoleGroupRelList(roleGroupRelList);
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
    public Result batchDelRoleGroupRel(UserGroupRoleRel userGroupRoleRel)
    {
        try
        {
            if (userGroupRoleRel.getGroupId() == 0
                || CommonConstant.isEmptyList(userGroupRoleRel.getRoleList()))
            {
                return Result.fail("Request parameter is error.");
            }
            List<UserGroupRoleRel> roleGroupRelList =
                new ArrayList<UserGroupRoleRel>();
            for (Role role : userGroupRoleRel.getRoleList())
            {
                UserGroupRoleRel rel = new UserGroupRoleRel();
                rel.setGroupId(userGroupRoleRel.getGroupId());
                rel.setRoleId(role.getRoleId());
                roleGroupRelList.add(rel);
            }
            jdbcTemplateService.batchDelRoleGroupRel(roleGroupRelList);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData();
    }
    
    @Override
    public Result<List<UserGroupRoleRel>> getRoleRelListByGroupId(Long groupId)
    {
        List<UserGroupRoleRel> userGroupRoleRelList;
        try
        {
            if (groupId == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            UserGroup userGroup = userGroupDao.findGroupById(groupId);
            if (null == userGroup)
            {
                return Result.fail("User group does not exist.");
            }
            userGroupRoleRelList =
                userGroupDao.getRoleRelListByGroupId(groupId);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData(userGroupRoleRelList);
    }
    
}
