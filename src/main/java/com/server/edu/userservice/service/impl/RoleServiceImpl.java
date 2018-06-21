
package com.server.edu.userservice.service.impl;

import java.util.ArrayList;
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
import com.server.edu.userservice.dao.RoleDao;
import com.server.edu.userservice.entity.Role;
import com.server.edu.userservice.entity.RoleAuthorityRel;
import com.server.edu.userservice.entity.UserGroup;
import com.server.edu.userservice.entity.UserGroupRoleRel;
import com.server.edu.userservice.service.RoleService;
import com.server.edu.userservice.util.CommonConstant;

@SuppressWarnings("all")
@Service
public class RoleServiceImpl implements RoleService
{
    private static Logger LOG = LoggerFactory
        .getLogger("com.server.edu.userservice.service.impl.RoleServiceImpl");
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private JdbcTemplateService jdbcTemplateService;
    
    @Override
    @Transactional
    public Result<Role> addRole(Role role)
    {
        // 入参校验
        try
        {
            if (CommonConstant.isEmptyStr(role.getRoleName()))
            {
                return Result.fail("Request parameter is error.");
            }
            
            Role role1 = roleDao.findRoleByName(role.getRoleName());
            if (null != role1)
            {
                return Result.fail("Role already exists.");
            }
            roleDao.createOrEidtRole(role);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData(role);
    }
    
    @Override
    @Transactional
    public Result updateRole(Role role)
    {
        try
        {
            if (role.getRoleId() == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            Role role1 = roleDao.findRoleById(role.getRoleId());
            if (null == role1)
            {
                return Result.fail("Role does not exist.");
            }
            roleDao.createOrEidtRole(role);
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
    public Result deleteRole(long roleId)
    {
        try
        {
            if (roleId == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            Role role = roleDao.findRoleById(roleId);
            if (null == role)
            {
                return Result.fail("Role does not exist");
            }
            roleDao.deleteRole(roleId);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData();
    }
    
    @Override
    public Result findRole(Long roleId)
    {
        Role role;
        try
        {
            if (roleId == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            role = roleDao.findRoleById(roleId);
            if (null == role)
            {
                return Result.fail("Role does not exist");
            }
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData(role);
    }
    
    @Override
    public Result findRoleList(Role role)
    {
        PageResult pageResult;
        try
        {
            if (role.getPageNum() == 0 || role.getPageSize() == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            PageHelper.startPage(role.getPageNum(), role.getPageSize());
            Page<Role> page = (Page<Role>)roleDao.findRoleList(role);
            pageResult =
                new PageResult(role.getPageNum(), role.getPageSize(), page);
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
    public Result batchAddAuthToRoleRel(RoleAuthorityRel roleAuthorityRel)
    {
        
        try
        {
            if (roleAuthorityRel.getRoleId() == 0
                || roleAuthorityRel.getType() == 0
                || CommonConstant.isEmptyObj(roleAuthorityRel.getAuth())
                || CommonConstant.isEmptyList((List)roleAuthorityRel.getAuth()))
            {
                return Result.fail("Request parameter is error.");
            }
            Role role = roleDao.findRoleById(roleAuthorityRel.getRoleId());
            if (null == role)
            {
                return Result.fail("Role not exist");
            }
            List<RoleAuthorityRel> roleAuthorityRelList =
                new ArrayList<RoleAuthorityRel>();
            for (Integer valueId : (List<Integer>)roleAuthorityRel.getAuth())
            {
                RoleAuthorityRel rel = new RoleAuthorityRel();
                rel.setValueId(valueId);
                rel.setRoleId(roleAuthorityRel.getRoleId());
                rel.setType(roleAuthorityRel.getType());
                roleAuthorityRelList.add(rel);
            }
            roleDao.createRoleAuthRelList(roleAuthorityRelList);
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
    public Result batchDelAuthToRoleRel(RoleAuthorityRel roleAuthorityRel)
    {
        
        try
        {
            if (roleAuthorityRel.getRoleId() == 0
                || roleAuthorityRel.getType() == 0
                || CommonConstant.isEmptyObj(roleAuthorityRel.getAuth())
                || CommonConstant.isEmptyList((List)roleAuthorityRel.getAuth()))
            {
                return Result.fail("Request parameter is error.");
            }
            
            List<RoleAuthorityRel> roleAuthorityRelList =
                new ArrayList<RoleAuthorityRel>();
            for (Integer valueId : (List<Integer>)roleAuthorityRel.getAuth())
            {
                RoleAuthorityRel rel = new RoleAuthorityRel();
                rel.setValueId(valueId);
                rel.setRoleId(roleAuthorityRel.getRoleId());
                rel.setType(roleAuthorityRel.getType());
                roleAuthorityRelList.add(rel);
            }
            jdbcTemplateService.batchDelAuthToRoleRel(roleAuthorityRelList);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData();
    }

    @Override
    public Result<List<RoleAuthorityRel>> getAuthRoleRelListByRoleId(
        Long roleId)
    {
        List<RoleAuthorityRel> authRoleRelList;
        try
        {
            if (roleId == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            Role role = roleDao.findRoleById(roleId);
            if (null == role)
            {
                return Result.fail("Role does not exist.");
            }
            authRoleRelList =
                roleDao.getAuthRoleRelListByRoleId(roleId);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData(authRoleRelList);
    }
    
}
