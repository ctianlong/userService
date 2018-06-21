package com.server.edu.userservice.dao;

import java.util.List;

import com.server.edu.userservice.entity.UserGroup;
import com.server.edu.userservice.entity.UserGroupRel;
import com.server.edu.userservice.entity.UserGroupRoleRel;

public interface UserGroupDao
{
    
    void createOrEditGroup(UserGroup userGroup);
    
    UserGroup findGroupById(long groupId);
    
    UserGroup findGroupByName(String groupName);
    
    void createUserGroupRel(UserGroupRel userGroupRel);
    
    List<UserGroup> findUserGroupList(UserGroup userGroup);
    
    void createUserGroupRelList(List<UserGroupRel> userGroupRelList);
    
    void deleteGroup(long groupId);
    
    void deleteUserGroupRel(UserGroupRel userGroupRel);
    
    void createRoleGroupRelList(List<UserGroupRoleRel> userGroupRoleRelList);
    
    List<UserGroupRel> getUserRelListByGroupId(Long groupId);
    
    List<UserGroupRoleRel> getRoleRelListByGroupId(Long groupId);
}
