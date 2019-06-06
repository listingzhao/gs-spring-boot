package com.didispace.mapper;

import com.didispace.domain.User;

import java.util.List;

/**
 * Created with com.didispace.mapper.
 *
 * @author: Xavier
 * @time: 2019/3/19 20:36
 */
public interface UserMapper {
    List<User> getAll();

    User getUser(Long id);

    User getUserByUsername(String username);

    void insert(User user);

    void update(User user);

    void delete(Long id);
}
