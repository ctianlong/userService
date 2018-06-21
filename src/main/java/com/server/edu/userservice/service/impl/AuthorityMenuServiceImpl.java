package com.server.edu.userservice.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.dao.AuthorityMenuDao;
import com.server.edu.userservice.entity.AuthorityMenu;
import com.server.edu.userservice.entity.RoleAuthorityRel;
import com.server.edu.userservice.service.AuthorityMenuService;
import com.server.edu.userservice.util.CommonConstant;

import net.sf.json.JSONObject;

@SuppressWarnings("all")
@Service
public class AuthorityMenuServiceImpl implements AuthorityMenuService
{
    private static Logger LOG = LoggerFactory.getLogger(
        "com.server.edu.userservice.service.impl.AuthorityMenuServiceImpl");
    @Autowired
    private AuthorityMenuDao authorityMenuDao;
    
    @Override
    public AuthorityMenu getAuthorityMenuById(AuthorityMenu authorityMenu)
    {
        return authorityMenuDao.getAuthorityMenuById(authorityMenu.getAuthId());
    }
    
    @Override
    public Result findAuthorityMenuList(Long parentAuthId, Integer type)
    {
        if (type != 1 && type != 2)
        {
            return Result.fail("Request parameter is error.");
        }
        AuthorityMenu authorityMenu = new AuthorityMenu();
        authorityMenu.setParentAuthId(parentAuthId);
        authorityMenu.setType(type);
        List<AuthorityMenu> listData =
            authorityMenuDao.findAuthorityMenuList(authorityMenu);
        return Result.successData(listData);
    }
    
    @Override
    public Result addAuthorityMenu(AuthorityMenu authorityMenu)
    {
        try
        {
            if (CommonConstant.isEmptyStr(authorityMenu.getAuthNameEn()))
            {
                return Result.fail("Request parameter is error.");
            }
            if (authorityMenu.getType() != 1 && authorityMenu.getType() != 2)
            {
                return Result.fail("Request parameter is error.");
            }
            AuthorityMenu authorityMenu1 =
                authorityMenuDao.findAuthorityMenuByName(authorityMenu.getAuthNameEn());
            if (null != authorityMenu1)
            {
                return Result.fail("Authority Menu already exists.");
            }
            authorityMenuDao.createAuthorityMenu(authorityMenu);
        }
        catch (Exception e)
        {
            return Result.error(e.getMessage());
        }
        return Result.successData(authorityMenu);
    }
    
    @Override
    public Result updateAuthorityMenu(AuthorityMenu authorityMenu)
    {
        try
        {
            if (authorityMenu.getAuthId() == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            AuthorityMenu authorityMenu1 =
                authorityMenuDao.getAuthorityMenuById(authorityMenu.getAuthId());
            if (null == authorityMenu1)
            {
                return Result.fail("Authority Menu does not exist.");
            }
            authorityMenuDao.updateAuthorityMenu(authorityMenu);
        }
        catch (Exception e)
        {
            return Result.error(e.getMessage());
        }
        return Result.successData(authorityMenu);
    }
    
    @Override
    public Result deleteAuthorityMenu(long auth_id)
    {
        try
        {
            if (auth_id == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            AuthorityMenu authorityMenu =
                authorityMenuDao.getAuthorityMenuById(auth_id);
            if (null == authorityMenu)
            {
                return Result.fail("Authority Menu does not exist.");
            }
            authorityMenuDao.deleteAuthorityMenu(auth_id);
        }
        catch (Exception e)
        {
            return Result.error(e.getMessage());
        }
        return Result.successData(null);
    }
    
    @Override
    public Result findAuthorityMenuListByRoleId(Long roleId, Long parentAuthId,
        Integer type)
    {
        if (type != 1 && type != 2)
        {
            return Result.fail("Request parameter is error.");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleId);
        map.put("parentAuthId", parentAuthId);
        map.put("type", type);
        List<AuthorityMenu> authorityMenuList =
            authorityMenuDao.findAuthorityMenuListByRoleId(map);
        return Result.successData(authorityMenuList);
    }
    
    @Override
    public Result saveRoleAuthorityRel(String requestParam)
    {
        JSONObject json = JSONObject.fromObject(requestParam);
        Long role_id = json.getLong("role_id");
        String[] value_ids = json.getString("value_id").split(",");
        List<Long> newList = new ArrayList<>();
        CollectionUtils.collect(Arrays.asList(value_ids), new Transformer()
        {
            @Override
            public Object transform(Object o)
            {
                return Long.valueOf(o.toString());
            }
        }, newList);
        //------------------通过role_id查询出该角色对应的权限列表信息------------------
        List<AuthorityMenu> list = this.listAuthorityMenu(role_id);
        List<Long> srcList =
            list.stream()
                .map(AuthorityMenu::getAuthId)
                .collect(Collectors.toList());
        RoleAuthorityRel vo = new RoleAuthorityRel();
        for (Long valueId : newList)
        {
            if (!srcList.contains(valueId))
            {//新上传的权限在用户的权限列表中找不到时，认为是新增的权限
                vo.setRoleId(role_id);
                vo.setValueId(valueId);
                authorityMenuDao.addRoleAuthorityRel(vo);
            }
        }
        for (Long valueId : srcList)
        {
            if (!newList.contains(valueId))
            {//已有的权限在上传的列表中找不到时，认为是删除的权限
                vo.setRoleId(role_id);
                vo.setValueId(valueId);
                authorityMenuDao.deleteRoleAuthorityRel(vo);
            }
        }
        return Result.successData(null);
    }
    
    private List<AuthorityMenu> listAuthorityMenu(Long roleId)
    {
        if (roleId == null || roleId == 0)
        {
            return new ArrayList<AuthorityMenu>();
        }
        List<AuthorityMenu> result = null;
        Map<Long, AuthorityMenu> map = this.listRoleFuncList(roleId);
        if (map == null || map.isEmpty())
        {
            result = new ArrayList<>();
        }
        else
        {
            result = new ArrayList<>(map.values());
            AuthorityMenu.sort(result);
        }
        return result;
    }
    
    private Map<Long, AuthorityMenu> listRoleFuncList(Long roleId)
    {
        Map<Long, AuthorityMenu> result = new HashMap<Long, AuthorityMenu>();
        RoleAuthorityRel vo = new RoleAuthorityRel();
        vo.setRoleId(roleId);
        List<RoleAuthorityRel> roleAuthorityRelList =
            authorityMenuDao.findRoleAuthorityRelList(vo);
        for (RoleAuthorityRel item : roleAuthorityRelList)
        {
            // 获取该角色拥有的权限
            AuthorityMenu sysFunc =
                authorityMenuDao.getAuthorityMenuById(item.getValueId());
            result.put(item.getValueId(), sysFunc);
        }
        return result;
    }

    @Override
    public Result<List<Long>> findAuthMenuByUserId(long userId, int type)
    {
        List<Long> authList;
        try
        {
            if (userId == 0 || type == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            map.put("type", type);
            authList = authorityMenuDao.findAuthMenuByUserId(map);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData(authList);
    }
    
}
