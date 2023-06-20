package com.wetoband.template.dao;


import com.fy.toolhelper.db.IBaseDao;
import com.wetoband.template.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * (Draft)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-28 17:00:42
 */
public interface UserDao extends IBaseDao<User> {

    User getUser(Connection connection, Long id) throws SQLException;

}

