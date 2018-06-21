package com.server.edu.userservice.service.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.server.edu.userservice.entity.LocalUserDept;
import com.server.edu.userservice.entity.RoleAuthorityRel;
import com.server.edu.userservice.entity.UserGroupRel;
import com.server.edu.userservice.entity.UserGroupRoleRel;

/**
 * 
 * @Description:jdbc批量操作
 * @author:lifeiyue
 * @time:2018年6月7日 下午2:21:04
 */
@Service
public class JdbcTemplateService
{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public int[] batchDelUserGroupRel(final List<UserGroupRel> userGroupRelList)
    {
        String sql =
            "delete from user_group_rel_t where GROUP_ID_ = ? and USER_ID_ = ? and USER_TYPE_ = ?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps, int i)
                throws SQLException
            {
                UserGroupRel userGroupRel =
                    (UserGroupRel)userGroupRelList.get(i);
                ps.setLong(1, userGroupRel.getGroupId());
                ps.setLong(2, userGroupRel.getUserId());
                ps.setLong(3, userGroupRel.getUserType());
            }
            
            public int getBatchSize()
            {
                return userGroupRelList.size();
                //这个方法设定更新记录数，通常List里面存放的都是我们要更新的，所以返回list.size();  
            }
        });
        return null;
    }
    
    public int[] batchDelRoleGroupRel(
        final List<UserGroupRoleRel> roleGroupRelList)
    {
        String sql =
            "delete from role_group_rel_t where GROUP_ID_ = ? and ROLE_ID_ = ?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps, int i)
                throws SQLException
            {
                UserGroupRoleRel roleGroupRel =
                    (UserGroupRoleRel)roleGroupRelList.get(i);
                ps.setLong(1, roleGroupRel.getGroupId());
                ps.setLong(2, roleGroupRel.getRoleId());
            }
            
            public int getBatchSize()
            {
                return roleGroupRelList.size();
                //这个方法设定更新记录数，通常List里面存放的都是我们要更新的，所以返回list.size();  
            }
        });
        return null;
    }
    
    public int[] batchDelAuthToRoleRel(
        List<RoleAuthorityRel> roleAuthorityRelList)
    {
        String sql =
            "DELETE FROM role_authority_rel_t WHERE ROLE_ID_ = ? AND VALUE_ID_ = ? AND TYPE_ = ?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps, int i)
                throws SQLException
            {
                RoleAuthorityRel roleGroupRel =
                    (RoleAuthorityRel)roleAuthorityRelList.get(i);
                ps.setLong(1, roleGroupRel.getRoleId());
                ps.setLong(2, roleGroupRel.getValueId());
                ps.setLong(3, roleGroupRel.getType());
            }
            
            public int getBatchSize()
            {
                return roleAuthorityRelList.size();
            }
        });
        return null;
    }
    
    public int[] batchDelDeptToUserDept(List<LocalUserDept> localUserDeptList)
    {
        String sql =
            "DELETE FROM local_user_dept WHERE USER_ID_ = ? AND DEPT_ID_ = ? ";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps, int i)
                throws SQLException
            {
                LocalUserDept roleGroupRel =
                    (LocalUserDept)localUserDeptList.get(i);
                ps.setLong(1, roleGroupRel.getUserId());
                ps.setLong(2, roleGroupRel.getDeptId());
            }
            
            public int getBatchSize()
            {
                return localUserDeptList.size();
            }
        });
        return null;
    }
}
