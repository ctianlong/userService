package com.server.edu.userservice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.dao.LocalUserDao;
import com.server.edu.userservice.entity.LocalUser;
import com.server.edu.userservice.entity.LocalUserDept;
import com.server.edu.userservice.service.LocalUserService;
import com.server.edu.userservice.util.CommonConstant;

@SuppressWarnings("all")
@Service
public class LocalUserServiceImpl implements LocalUserService
{
    private static Logger LOG =
        LoggerFactory.getLogger("com.server.edu.userservice.service.impl.LocalUserServiceImpl");
    
    @Autowired
    private LocalUserDao localUserDao;
    
    @Autowired
    private JdbcTemplateService jdbcTemplateService;
    
    @Override
    public Result findLocalUserListByDeptId(Long deptId)
    {
        if (deptId == 0)
        {
            return Result.fail("Request parameter is error.");
        }
        List<LocalUser> listData =
            localUserDao.findLocalUserListByDeptId(deptId);
        return Result.successData(listData);
    }
    
    @Override
    public Result findLocalUserById(Long userId)
    {
        LocalUser localUser = localUserDao.getLocalUserById(userId);
        return Result.successData(localUser);
    }
    
    @Override
    public Result addLocalUser(LocalUser localUser)
    {
        try
        {
            String resultReason = "";
            if (!"".equals(checkUserNamePwd(localUser, resultReason)))
            {
                return Result.fail(resultReason);
            }
            localUser.setCreateDate((new Date()).getTime());
            localUserDao.createLocalUser(localUser);
        }
        catch (Exception e)
        {
            return Result.error(e.getMessage());
        }
        return Result.successData(localUser);
    }
    
    @Override
    public Result updateLocalUser(LocalUser localUser)
    {
        try
        {
            if (localUser.getUserId() == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            LocalUser localUser1 =
                localUserDao.getLocalUserById(localUser.getUserId());
            if (null == localUser1)
            {
                return Result.fail("Local User does not exist.");
            }
            if (!CommonConstant.isEmptyStr(localUser.getUserName()))
            {
                localUser1.setUserName(localUser.getUserName());
            }
            if (!CommonConstant.isEmptyStr(localUser.getUserPwd()))
            {
                localUser1.setUserPwd(localUser.getUserPwd());
            }
            String resultReason = "";
            if (!"".equals(checkUserNamePwd(localUser1, resultReason)))
            {
                return Result.fail(resultReason);
            }
            localUser.setLastUpdateDate((new Date()).getTime());
            localUserDao.updateLocalUser(localUser);
        }
        catch (Exception e)
        {
            return Result.error(e.getMessage());
        }
        return Result.successData(localUser);
    }
    
    @Override
    public Result deleteLocalUser(Long userId)
    {
        try
        {
            if (userId == 0)
            {
                return Result.fail("Request parameter is error.");
            }
            LocalUser LocalUser = localUserDao.getLocalUserById(userId);
            if (null == LocalUser)
            {
                return Result.fail("Local User does not exist.");
            }
            localUserDao.deleteLocalUser(userId);
        }
        catch (Exception e)
        {
            return Result.error(e.getMessage());
        }
        return Result.successData(null);
    }
    
    @Override
    public Result checkLocalUser(LocalUser localUser)
    {
        String resultReason = "";
        if (!"".equals(checkUserNamePwd(localUser, resultReason)))
        {
            return Result.fail(resultReason);
        }
        return Result.successData(null);
    }
    
    private String checkUserNamePwd(LocalUser localUser, String resultReason)
    {
        if (CommonConstant.isEmptyStr(localUser.getUserName())
            || CommonConstant.isEmptyStr(localUser.getUserPwd()))
        {
            resultReason = "Request parameter is error.";
        }
        LocalUser localUser1 =
            localUserDao.findLocalUserByName(localUser.getUserName());
        if (null != localUser1)
        {
            resultReason = "Local User already exists.";
        }
        return resultReason;
    }
    
    @Override
    @Transactional
    public Result batchAddDeptToUserDept(LocalUserDept localUserDept)
    {
        try
        {
            if (localUserDept.getUserId() == 0
                || CommonConstant.isEmptyObj(localUserDept.getDept())
                || CommonConstant.isEmptyList((List)localUserDept.getDept()))
            {
                return Result.fail("Request parameter is error.");
            }
            LocalUser localUser =
                localUserDao.getLocalUserById(localUserDept.getUserId());
            if (null == localUser)
            {
                return Result.fail("Local User not exist");
            }
            List<LocalUserDept> localUserDeptList =
                new ArrayList<LocalUserDept>();
            for (Integer deptId : (List<Integer>)localUserDept.getDept())
            {
                LocalUserDept rel = new LocalUserDept();
                rel.setUserId(localUserDept.getUserId());
                rel.setDeptId(deptId);
                localUserDeptList.add(rel);
            }
            localUserDao.createUserDeptList(localUserDeptList);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData();
    }
    
    @Override
    @Transactional
    public Result batchDelDeptToUserDept(LocalUserDept localUserDept)
    {
        
        try
        {
            if (localUserDept.getUserId() == 0
                || CommonConstant.isEmptyObj(localUserDept.getDept())
                || CommonConstant.isEmptyList((List)localUserDept.getDept()))
            {
                return Result.fail("Request parameter is error.");
            }
            
            List<LocalUserDept> localUserDeptList =
                new ArrayList<LocalUserDept>();
            for (Integer deptId : (List<Integer>)localUserDept.getDept())
            {
                LocalUserDept rel = new LocalUserDept();
                rel.setUserId(localUserDept.getUserId());
                rel.setDeptId(deptId);
                localUserDeptList.add(rel);
            }
            jdbcTemplateService.batchDelDeptToUserDept(localUserDeptList);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            return Result.error(e.getMessage());
        }
        return Result.successData();
    }
}
