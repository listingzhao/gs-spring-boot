package com.didispace.service;

import com.didispace.domain.User;
import com.didispace.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

/**
 * Created with com.didispace.service.
 *
 * @author: Xavier
 * @time: 2019/3/21 17:41
 */
@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;

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
