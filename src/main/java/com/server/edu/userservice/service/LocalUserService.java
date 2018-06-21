package com.server.edu.userservice.service;

import com.server.edu.userservice.common.rest.Result;
import com.server.edu.userservice.entity.LocalUser;
import com.server.edu.userservice.entity.LocalUserDept;

@SuppressWarnings("all")
public interface LocalUserService
{
    /**
     * 
     * <根据deptId查询对应的本地用户列表>
     * @param deptId
     * @return
     * @author: lizhiwei
     * @time:2018年6月12日 下午4:37:56
     */
    public Result findLocalUserListByDeptId(Long deptId);
    
    /**
     * 
     * <根据主键id查询本地用户信息>
     * @param userId
     * @return
     * @author: lizhiwei
     * @time:2018年6月12日 下午4:38:22
     */
    public Result findLocalUserById(Long userId);
    
    /**
     * 
     * <新增本地用户信息>
     * @param localUser
     * @return
     * @author: lizhiwei
     * @time:2018年6月12日 下午4:38:42
     */
    public Result addLocalUser(LocalUser localUser);
    
    /**
     * 
     * <更新本地用户信息>
     * @param localUser
     * @return
     * @author: lizhiwei
     * @time:2018年6月12日 下午4:38:59
     */
    public Result updateLocalUser(LocalUser localUser);
    
    /**
     * 
     * <删除本地用户信息>
     * @param userId
     * @return
     * @author: lizhiwei
     * @time:2018年6月12日 下午4:39:20
     */
    public Result deleteLocalUser(Long userId);
    
    /**
     * 
     * <校验用户密码>
     * @param userName, userPwd
     * @return
     * @author: lizhiwei
     * @time:2018年6月12日 下午4:33:52
     */
    public Result checkLocalUser(LocalUser localUser);
    
    /**
     * 
     * <本地用户批量新增部门>
     * @param localUserDept
     * @return
     * @author: lizhiwei
     * @time:2018年6月13日 上午10:16:46
     */
    public Result batchAddDeptToUserDept(LocalUserDept localUserDept);
    
    /**
     * 
     * <本地用户批量删除部门>
     * @param localUserDept
     * @return
     * @author: lizhiwei
     * @time:2018年6月13日 上午10:17:13
     */
    public Result batchDelDeptToUserDept(LocalUserDept localUserDept);
    
}
