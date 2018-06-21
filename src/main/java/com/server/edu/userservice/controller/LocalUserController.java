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
import com.server.edu.userservice.common.rest.ResultStatus;
import com.server.edu.userservice.entity.LocalUser;
import com.server.edu.userservice.entity.LocalUserDept;
import com.server.edu.userservice.service.LocalUserService;

@SuppressWarnings("all")
@RestSchema(schemaId = "LocalUserController")
@RequestMapping("/localUser")
public class LocalUserController
{
    private static Logger LOG =
        LoggerFactory.getLogger("com.server.edu.userservice.controller.LocalUserController");
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private LocalUserService localUserService;
    
    /**
     * 
     * <测试是否已连接服务>
     * @return
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月15日 下午4:31:26
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Result test()
    {
        LOG.info("test.sucess");
        return Result.successData(ResultStatus.SUCCESS, "服务已连接成功", null);
    }
    
    /**
     * 
     * <根据deptId查询对应的本地用户列表>
     * @param deptId
     * @return
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月12日 下午4:31:26
     */
    @RequestMapping(value = "/findLocalUserListByDeptId/{deptId}", method = RequestMethod.GET)
    @ResponseBody
    public Result findLocalUserList(@PathVariable("deptId") Long deptId)
        throws Exception
    {
        LOG.info("findLocalUserListByDeptId.start");
        return localUserService.findLocalUserListByDeptId(deptId);
    }
    
    /**
     * 
     * <根据主键id查询本地用户信息>
     * @param userId
     * @return
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月12日 下午4:32:22
     */
    @RequestMapping(value = "/findLocalUserById/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result findLocalUserById(@PathVariable("userId") Long userId)
        throws Exception
    {
        LOG.info("findLocalUserById.start");
        return localUserService.findLocalUserById(userId);
    }
    
    /**
     * 
     * <新增本地用户信息>
     * @param requestParam(userId, userName, userPwd, email, mobilePhone, createBy, createDate, lastUpdateBy, lastUpdateDate, comment)
     * @return
     * @throws IOException
     * @author: lizhiwei
     * @time:2018年6月12日 下午4:33:01
     */
    @RequestMapping(value = "/addLocalUser", method = RequestMethod.POST)
    @ResponseBody
    public Result addLocalUser(@RequestBody String requestParam)
        throws IOException
    {
        LOG.info("addLocalUser.start");
        LocalUser localUser = mapper.readValue(requestParam, LocalUser.class);
        return localUserService.addLocalUser(localUser);
    }
    
    /**
     * 
     * <更新本地用户信息>
     * @param requestParam(userName, userPwd, email, mobilePhone, createBy, createDate, lastUpdateBy, lastUpdateDate, comment)
     * @return
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月12日 下午4:33:28
     */
    @RequestMapping(value = "/updateLocalUser", method = RequestMethod.PUT)
    @ResponseBody
    public Result updateLocalUser(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("updateLocalUser.start");
        LocalUser localUser = mapper.readValue(requestParam, LocalUser.class);
        return localUserService.updateLocalUser(localUser);
    }
    
    /**
     * 
     * <删除本地用户信息>
     * @param requestParam(userId)
     * @return
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月12日 下午4:33:52
     */
    @RequestMapping(value = "/deleteLocalUser", method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteLocalUser(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("deleteLocalUser.start");
        LocalUser localUser = mapper.readValue(requestParam, LocalUser.class);
        return localUserService.deleteLocalUser(localUser.getUserId());
    }
    
    /**
     * 
     * <校验用户密码>
     * @param requestParam(userName, userPwd)
     * @return
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月12日 下午4:33:52
     */
    @RequestMapping(value = "/checkLocalUser", method = RequestMethod.POST)
    @ResponseBody
    public Result checkLocalUser(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("checkLocalUser.start");
        LocalUser localUser = mapper.readValue(requestParam, LocalUser.class);
        return localUserService.checkLocalUser(localUser);
    }
    
    /**
     * 
     * <本地用户批量新增部门>
     * @param requestParam
     * @return String
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月13日 上午11:31:19
     */
    @RequestMapping(value = "/batchAddDeptToUserDept", method = RequestMethod.POST)
    @ResponseBody
    public Result batchAddDeptToUserDept(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("batchAddDeptToUserDept.start");
        LocalUserDept localUserDept =
            mapper.readValue(requestParam, LocalUserDept.class);
        return localUserService.batchAddDeptToUserDept(localUserDept);
    }
    
    /**
     * 
     * <本地用户批量删除部门>
     * @param requestParam
     * @return String
     * @throws Exception
     * @author: lizhiwei
     * @time:2018年6月13日 下午2:13:44
     */
    @RequestMapping(value = "/batchDelDeptToUserDept", method = RequestMethod.DELETE)
    @ResponseBody
    public Result batchDelDeptToUserDept(@RequestBody String requestParam)
        throws Exception
    {
        LOG.info("batchDelDeptToUserDept.start");
        LocalUserDept localUserDept =
            mapper.readValue(requestParam, LocalUserDept.class);
        return localUserService.batchDelDeptToUserDept(localUserDept);
    }
}
