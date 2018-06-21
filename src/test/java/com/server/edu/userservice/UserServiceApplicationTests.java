package com.server.edu.userservice;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.edu.userservice.entity.User;
import com.server.edu.userservice.entity.UserGroup;
import com.server.edu.userservice.entity.UserGroupRel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceApplicationTests
{
    
    @Autowired
    private TestRestTemplate testRestTemplate;
    
    private String url = "http://127.0.0.1:8189";
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Test
    public void testEditUserInfo()
        throws JSONException, JsonProcessingException
    {
        //第一种方式
        User aa = new User();
        aa.setUserId(1);
        aa.setUserName("lizhi");
        //第二种方式
        String result = testRestTemplate
            .postForObject(url + "/users/editUserInfo", aa, String.class);
        System.out.println("测试结果：" + mapper.writeValueAsString(result));
    }
    
    @Test
    public void testcreateOrEidtGroup()
        throws JSONException, JsonProcessingException
    {
        //第一种方式
        UserGroup aa = new UserGroup();
        aa.setGroupId(3);
        aa.setGroupName("postgraduate");
        aa.setDescription("postgraduate");
        aa.setCreateBy("admin");
        
        UserGroupRel b = new UserGroupRel();
        b.setGroupId(3);
        List<User> userlist = new ArrayList<User>();
        User user = new User();
        user.setUserId(1);
        userlist.add(user);
        User user1 = new User();
        user1.setUserId(2);
        userlist.add(user1);
        b.setUserList(userlist);
        
        //第二种方式
        String result = testRestTemplate.postForObject(
            url + "/userGroup/createUserGroupRel", b, String.class);
        System.out.println("测试结果：" + mapper.writeValueAsString(result));
    }
    
    @Test
    public void testSelect()
        throws JSONException, JsonProcessingException
    {
        //第一种方式
        UserGroupRel aa = new UserGroupRel();
        aa.setGroupId(1);
        aa.setUserId(1);
        
        //第二种方式
        String result = testRestTemplate.postForObject(
            url + "/userGroup/createUserGroupRel", aa, String.class);
        System.out.println("测试结果：" + mapper.writeValueAsString(result));
    }
}
