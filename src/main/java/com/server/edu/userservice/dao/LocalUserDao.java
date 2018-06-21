package com.server.edu.userservice.dao;

import java.util.List;

import com.server.edu.userservice.entity.LocalUser;
import com.server.edu.userservice.entity.LocalUserDept;

public interface LocalUserDao
{
    
    List<LocalUser> findLocalUserListByDeptId(Long deptId);
    
    LocalUser getLocalUserById(Long userId);
    
    void createLocalUser(LocalUser localUser);
    
    void updateLocalUser(LocalUser localUser);
    
    void deleteLocalUser(Long userId);

    LocalUser findLocalUserByName(String userName);

    void createUserDeptList(List<LocalUserDept> localUserDeptList);
    
}
