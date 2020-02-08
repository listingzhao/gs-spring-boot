package com.leyiju.service;

import com.leyiju.domain.User;
import com.leyiju.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

/**
 * Created with com.leyiju.service.
 *
 * @author: Xavier
 * @time: 2019/3/21 17:41
 */
public interface UserService {

    public List<User> getUserList();

    public int saveUser(User user);

    public User getUser(Long id);

    public User getUserByUsername(String username);

    public User getUserByPhone(String phone);

    public void updateUser(User user);
}
