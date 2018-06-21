package com.server.edu.userservice.service;

import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.entity.AuthorityMenu;

@SuppressWarnings("all")
public interface AuthorityMenuService
{
    
    /**
     * 根据主键id查询权限信息
     * @param authorityMenu
     * @return
     */
    public AuthorityMenu getAuthorityMenuById(AuthorityMenu authorityMenu);
    
    /**
     * 查询权限列表信息
     * @param authorityMenu
     * @return
     */
    public Result findAuthorityMenuList(Long parentAuthId, Integer type);
    
    /**
     * 新增权限
     * @param authorityMenu
     * @return
     */
    public Result addAuthorityMenu(AuthorityMenu authorityMenu);
    
    /**
     * 修改权限
     * @param authorityMenu
     * @return
     */
    public Result updateAuthorityMenu(AuthorityMenu authorityMenu);
    
    /**
     * 删除权限
     * @param auth_id
     * @return
     */
    public Result deleteAuthorityMenu(long auth_id);
    
    /**
     * 根据角色id查询相关权限列表
     * @param requestParam
     * @return
     */
    public Result findAuthorityMenuListByRoleId(Long roleId, Long parentAuthId,
        Integer type);
    
    /**
     * 角色授权
     * @param requestParam
     * @return
     */
    public Result saveRoleAuthorityRel(String requestParam);

    /**
     * 
     * <通过用户查询权限>
     * @param requestParam
     * @return
     * @author: lifeiyue
     * @time:2018年6月12日 上午9:43:07
     */
    public Result findAuthMenuByUserId(long userId, int type);
    
}
