package com.wetoband.template.dao.impl;

import com.fy.toolhelper.db.BaseDaoImpl;
import com.wetoband.template.dao.UserDao;
import com.wetoband.template.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public UserDaoImpl() throws Exception {
    }

    public User getUser(Connection connection, Long id) throws SQLException {
        String sql = "SELECT * FROM blp_draft WHERE application_case_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        List<User> userList = new ArrayList<>();
        if (rs.next()) {
            User user = new User();
            /*省略*/
            return user;
        }
        if (rs != null) {
            rs.close();
        }
        if (pstm != null) {
            pstm.close();
        }
        return null;
    }

}