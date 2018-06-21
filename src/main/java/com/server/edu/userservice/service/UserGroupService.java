package com.server.edu.userservice.service;

import java.util.List;

import com.server.edu.userservice.common.rest.PageResult;
import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.entity.UserGroup;
import com.server.edu.userservice.entity.UserGroupRel;
import com.server.edu.userservice.entity.UserGroupRoleRel;

/**
 * 
 * @Description:用户组
 * @author:lifeiyue
 * @time:2018年6月7日 上午10:59:39
 */
public interface UserGroupService
{
    /**
     * 
     * <新增用户组>
     * @param userGroup
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 上午10:51:42
     */
    Result<UserGroup> addGroup(UserGroup userGroup);
    
    /**
     * 
     * <修改用户组>
     * @param userGroup
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 上午10:52:03
     */
    Result<UserGroup> updateGroup(UserGroup userGroup);
    
    /**
     * 
     * <删除用户组>
     * @param group_id
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 上午10:52:16
     */
    Result<UserGroup> deleteGroup(long group_id);
    
    /**
     * 
     * <获取单个用户组详情>
     * @param groupId
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 上午10:52:36
     */
    Result<UserGroup> findUserGroup(Long groupId);
    
    /**
     * 
     * <查询用户组列表>
     * @param userGroup
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 上午10:52:51
     */
    Result<PageResult<UserGroup>> findUserGroupList(UserGroup userGroup);
    
    /**
     * 
     * <用户组批量新增用户>
     * @param userGroupRel
     * @return
     * @author: lifeiyue
     * @time:2018年6月7日 上午10:50:42
     */
    Result batchAddUserGroupRel(UserGroupRel userGroupRel);
    
    /**
     * 
     * <用户组批量删除用户>
     * @param userGroupRel
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 上午10:51:01
     */
    Result batchDelUserGroupRel(UserGroupRel userGroupRel);
    
    /**
     * 
     * <获取用户和组的关系By用户组Id>
     * @param groupId
     * @return
     * @author: lifeiyue
     * @time:2018年6月8日 下午3:53:44
     */
    Result<List<UserGroupRel>> getUserRelListByGroupId(Long groupId);
    
    /**
     * 
     * <用户组批量新增角色>
     * @param userGroupRoleRel
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 上午10:51:14
     */
    Result batchAddRoleGroupRel(UserGroupRoleRel userGroupRoleRel);
    
    /**
     * 
     * <用户组批量删除角色>
     * @param userGroupRoleRel
     * @return Result
     * @author: lifeiyue
     * @time:2018年6月7日 上午10:51:26
     */
    Result batchDelRoleGroupRel(UserGroupRoleRel userGroupRoleRel);
    
    /**
     * 
     * <获取角色和组的关系By用户组Id>
     * @param groupId
     * @return
     * @author: lifeiyue
     * @time:2018年6月8日 下午3:51:55
     */
    Result<List<UserGroupRoleRel>> getRoleRelListByGroupId(Long groupId);
    
}
