package com.server.edu.userservice.service;

import java.util.List;

import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.entity.Role;
import com.server.edu.userservice.entity.RoleAuthorityRel;

/**
 * 
 * @Description:角色Service
 * @author:lifeiyue
 * @time:2018年6月7日 上午10:59:16
 */

public interface RoleService
{
    /**
     * 
     * <新增角色>
     * @param role
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 下午2:18:48
     */
    Result<Role> addRole(Role role);
    
    /**
     * 
     * <修改角色>
     * @param role
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 下午2:18:59
     */
    Result<Role> updateRole(Role role);
    
    /**
     * 
     * <删除角色>
     * @param roleId
     * @return String
     * @author: lifeiyue
     * @time:2018年6月7日 下午2:19:09
     */
    Result deleteRole(long roleId);
    
    /**
     * 
     * <查询单个角色详情>
     * @param roleId
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 下午2:19:18
     */
    Result<Role> findRole(Long roleId);
    
    /**
     * 
     * <查询角色列表>
     * @param role
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 下午2:19:42
     */
    Result<List<Role>> findRoleList(Role role);
    
    /**
     * 
     * <角色批量添加权限>
     * @param roleAuthorityRel
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 下午2:20:04
     */
    Result batchAddAuthToRoleRel(RoleAuthorityRel roleAuthorityRel);
    
    /**
     * 
     * <角色批量删除权限>
     * @param roleAuthorityRel
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 下午2:20:18
     */
    Result batchDelAuthToRoleRel(RoleAuthorityRel roleAuthorityRel);

    /**
     * 
     * <查询角色和权限的关系by角色Id>
     * @param roleId
     * @return
     * @author: lifeiyue
     * @time:2018年6月13日 上午9:25:02
     */
    Result<List<RoleAuthorityRel>> getAuthRoleRelListByRoleId(Long roleId);
    
}
