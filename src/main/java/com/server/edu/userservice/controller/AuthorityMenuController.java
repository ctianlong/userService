package com.server.edu.userservice.controller;

import java.io.IOException;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.entity.AuthorityMenu;
import com.server.edu.userservice.service.AuthorityMenuService;

@SuppressWarnings("all")
@RestSchema(schemaId = "AuthorityMenuController")
@RequestMapping("/authorityMenu")
public class AuthorityMenuController
{
    
    private static Logger LOG =
        LoggerFactory.getLogger("com.server.edu.userservice.controller.AuthorityMenuController");
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private AuthorityMenuService authorityMenuService;
    
    /**
     * 
     * <查询权限列表>
     * @return  requestParam(包含 parentAuthId, type)
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月8日 下午3:37:21
     */
    @RequestMapping(value = "/findAuthorityMenuList/{parentAuthId}-{type}", method = RequestMethod.GET)
    @ResponseBody
    public Result findAuthorityMenuList(
        @PathVariable("parentAuthId") Long parentAuthId,
        @PathVariable("type") Integer type)
        throws Exception
    {
        LOG.info("findAuthorityMenuList.start");
        return authorityMenuService.findAuthorityMenuList(parentAuthId, type);
    }
    
    /**
     * 
     * <新增权限>
     * @param requestParam(auth_name_en,auth_name_ch,parent_auth_id,urlPath,auth_code,type,order,application)
     * @return
     * @throws IOException
     * @author: lizhiwei
     * @time:2018年6月8日 下午3:35:50
     */
    @RequestMapping(value = "/addAuthorityMenu", method = RequestMethod.POST)
    @ResponseBody
    public Result addAuthorityMenu(@RequestBody String requestParam)
        throws IOException
    {
        LOG.info("addAuthorityMenu.start");
        AuthorityMenu authorityMenu =
            mapper.readValue(requestParam, AuthorityMenu.class);
        return authorityMenuService.addAuthorityMenu(authorityMenu);
    }
    
    /**
     * 
     * <更新权限>
     * @param requestParam(auth_name_en,auth_name_ch,parent_auth_id,urlPath,auth_code,type,order,application)
     * @return
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月8日 下午3:33:59
     */
    @RequestMapping(value = "/updateAuthorityMenu", method = RequestMethod.PUT)
    @ResponseBody
    public Result updateAuthorityMenu(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("updateAuthorityMenu.start");
        AuthorityMenu authorityMenu =
            mapper.readValue(requestParam, AuthorityMenu.class);
        return authorityMenuService.updateAuthorityMenu(authorityMenu);
    }
    
    /**
     * 
     * <删除权限>
     * @param requestParam(auth_id)
     * @return
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月8日 下午3:36:21
     */
    @RequestMapping(value = "/deleteAuthorityMenu", method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteAuthorityMenu(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("deleteAuthorityMenu.start");
        AuthorityMenu authorityMenu =
            mapper.readValue(requestParam, AuthorityMenu.class);
        return authorityMenuService.deleteAuthorityMenu(authorityMenu.getAuthId());
    }
    
    /**
     * 
     * <根据roleId查询权限列表>
     * @param requestParam(包含roleId, parentAuthId, type)
     * @return
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月6日 10:35:34
     */
    @RequestMapping(value = "/findAuthorityMenuListByRoleId/{roleId}-{parentAuthId}-{type}", method = RequestMethod.GET)
    @ResponseBody
    public Result findAuthorityMenuListByRoleId(
        @PathVariable("roleId") Long roleId,
        @PathVariable("parentAuthId") Long parentAuthId,
        @PathVariable("type") Integer type)
        throws Exception
    {
        LOG.info("findAuthorityMenuListByRoleId.start");
        return authorityMenuService.findAuthorityMenuListByRoleId(roleId,
            parentAuthId,
            type);
    }
    
    /**
     * 
     * <角色授权>
     * @param requestParam（包含role_id, value_id）
     * @return
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月8日 下午3:36:55
     */
    @RequestMapping(value = "/saveRoleAuthorityRel", method = RequestMethod.POST)
    @ResponseBody
    public Result saveRoleAuthorityRel(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("saveRoleAuthorityRel.start");
        return authorityMenuService.saveRoleAuthorityRel(requestParam);
    }
    
    /**
     * 
     * <通过用户查询权限>
     * @param requestParam
     * @return
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年6月12日 上午9:43:31
     */
    @RequestMapping(value = "/findAuthMenuByUserId/{userId}-{type}", method = RequestMethod.GET)
    @ResponseBody
    public Result findAuthMenuByUserId(@PathVariable("userId") long userId,
        @PathVariable("type") int type)
        throws Exception
    {
        LOG.info("findAuthMenuByUserId.start");
        return authorityMenuService.findAuthMenuByUserId(userId, type);
    }
    
}
