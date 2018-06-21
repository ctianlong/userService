package com.server.edu.userservice.dao;

import java.util.List;
import java.util.Map;

import com.server.edu.userservice.entity.AuthorityMenu;
import com.server.edu.userservice.entity.RoleAuthorityRel;

public interface AuthorityMenuDao
{
    
    AuthorityMenu getAuthorityMenuById(long authId);
    
    List<AuthorityMenu> findAuthorityMenuList(AuthorityMenu authorityMenu);
    
    AuthorityMenu findAuthorityMenuByName(String authNameEn);
    
    void createAuthorityMenu(AuthorityMenu authorityMenu);
    
    void updateAuthorityMenu(AuthorityMenu authorityMenu);
    
    void deleteAuthorityMenu(long auth_id);
    
    List<RoleAuthorityRel> findRoleAuthorityRelList(RoleAuthorityRel vo);
    
    List<AuthorityMenu> findAuthorityMenuListByRoleId(Map<String, Object> map);
    
    void addRoleAuthorityRel(RoleAuthorityRel vo);
    
    void deleteRoleAuthorityRel(RoleAuthorityRel vo);
    
    List<Long> findAuthMenuByUserId(Map<String, Object> map);
    
}
