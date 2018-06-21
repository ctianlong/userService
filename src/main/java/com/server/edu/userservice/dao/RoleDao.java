package com.server.edu.userservice.dao;

import java.util.List;

import com.server.edu.userservice.entity.Role;
import com.server.edu.userservice.entity.RoleAuthorityRel;
import com.server.edu.userservice.entity.UserGroupRoleRel;

public interface RoleDao
{
    
    Role findRoleById(long roleId);
    
    Role findRoleByName(String roleName);
    
    void createOrEidtRole(Role role);
    
    void createUserGroupRoleRel(UserGroupRoleRel userGroupRoleRel);
    
    void deleteRole(long roleId);
    
    List<Role> findRoleList(Role role);
    
    void createRoleAuthRelList(List<RoleAuthorityRel> roleAuthorityRelList);

    List<RoleAuthorityRel> getAuthRoleRelListByRoleId(Long roleId);
    
}
