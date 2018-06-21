package com.server.edu.userservice.dao;

import java.util.List;

import com.server.edu.userservice.entity.User;

public interface UserDao
{
    
    List<User> getUsers();
    
    User findUserByUserId(long usersId);
    
    void updateUser(User user);
    
    List<User> findUserList();
}
