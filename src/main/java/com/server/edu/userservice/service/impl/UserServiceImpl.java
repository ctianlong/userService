
package com.server.edu.userservice.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.server.edu.userservice.dao.UserDao;
import com.server.edu.userservice.entity.User;
import com.server.edu.userservice.util.ExcelExport;
import com.server.edu.userservice.util.ExcelImport;

@Service
public class UserServiceImpl
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private ExcelExport excelExport;
    
    @Autowired
    private ExcelImport excelRead;
    
    static final String[] cellTitle = {"序号", "学工号", "用户名", "真实姓名", "地址", "昵称",
        "头像", "性别", "手机号", "身份证号", "出生日期", "电子邮件", "备注"};
    
    @Transactional
    public void createUser(String name, Integer age)
    {
        jdbcTemplate.update("insert into user_t values(?,?);", age, name);
    }
    
    public void exportUserList()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("序号", "id");
        map.put("学工号", "userId");
        map.put("用户名", "userName");
        map.put("真实姓名", "realName");
        map.put("地址", "address");
        map.put("昵称", "aliaName");
        map.put("头像", "headPortRait");
        map.put("性别", "sex");
        map.put("手机号", "telephone");
        map.put("身份证号", "identity");
        map.put("身份证号", "birthday");
        map.put("电子邮件", "email");
        map.put("备注", "remarks");
        
        List<User> userList = userDao.findUserList();
        try
        {
            excelExport.exportUserInfoList(userList);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void importUserList(MultipartFile file)
    {
        try
        {
            List<ArrayList<String>> title = excelRead.readExcel(file);
            List<User> data = excelRead.readXlsxList(file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public void editUserInfo(User userinfo)
    {
        userDao.updateUser(userinfo);
    }
    
    public List<User> findUserList()
    {
        return userDao.findUserList();
    }
    
}
