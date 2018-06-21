package com.server.edu.userservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.edu.userservice.entity.User;
import com.server.edu.userservice.service.impl.UserServiceImpl;

@RestSchema(schemaId = "UsersController")
@RequestMapping("/users")
public class UsersController
{
    
    private static Logger LOG = LoggerFactory
        .getLogger("com.server.edu.userservice.controller.UsersController");
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private UserServiceImpl userService;
    
    /**
     * 
     * <修改用户信息>
     * @param userinfo
     * @return String
     * @throws JsonProcessingException 
     * @author: lifeiyue
     * @time:2018年5月31日 下午4:16:40
     */
    @RequestMapping(value = "/editUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public String editUserInfo(@RequestBody User userinfo)
        throws JsonProcessingException
    {
        LOG.info("editUserInfo.start ===== userinfo"
            + mapper.writeValueAsString(userinfo));
        userService.editUserInfo(userinfo);
        return "success";
    }
    
    /**
     * 
     * <查询用户信息>
     * @return String
     * @throws Exception
     * @author: lifeiyue
     * @time:2018年5月31日 下午4:17:05
     */
    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ResponseBody
    public String findUser(HttpServletRequest request)
        throws Exception
    {
        LOG.info("findUser.start");
        List<User> userList = userService.findUserList();
        return mapper.writeValueAsString(userList);
    }
    
    @RequestMapping(value = "/exportUserList", method = RequestMethod.GET)
    @ResponseBody
    public String exportUserList()
    {
        LOG.info("editUserInfo.start");
        userService.exportUserList();
        return "success";
    }
    
    @RequestMapping(value = "/importUserList", method = RequestMethod.POST)
    @ResponseBody
    public String importUserList(MultipartFile file)
    {
        LOG.info("editUserInfo.start");
        userService.importUserList(file);
        return "success";
    }
    
}
