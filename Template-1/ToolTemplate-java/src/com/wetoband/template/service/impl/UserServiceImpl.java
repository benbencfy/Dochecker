package com.wetoband.template.service.impl;

import com.fy.basejar.tool.ActionToolBase;
import com.wetoband.template.dao.UserDao;
import com.wetoband.template.entity.User;
import com.wetoband.template.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * (Draft)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 17:00:42
 */
@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUser(User user) throws Exception{
        final Connection connection = ActionToolBase.getDBConnection();
        return userDao.getUser(connection, user.getId());
    }

}
