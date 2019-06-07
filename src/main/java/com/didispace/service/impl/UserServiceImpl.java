package com.didispace.service.impl;

import com.didispace.domain.User;
import com.didispace.mapper.UserMapper;
import com.didispace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with com.didispace.service.impl.
 *
 * @author: Xavier
 * @time: 2019/6/7 15:08
 */
@Component
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.getAll();
    }

    @Transactional
    public int saveUser (User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.insert(user);
        return 0;
    }

    public User getUser(Long id) {
        return userMapper.getUser(id);
    }

    public User getUserByUsername (String username) {
        return userMapper.getUserByUsername(username);
    }

    public void updateUser(User user) {
        userMapper.update(user);
    }
}
