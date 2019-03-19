package com.didispace.test.mapper;

import com.didispace.domain.User;
import com.didispace.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created with didispace.test.
 *
 * @author: Xavier
 * @time: 2019/3/19 20:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new User("aa1", 18));
        userMapper.insert(new User("bb2", 20));
        userMapper.insert(new User("cc3", 32));
        System.out.println(userMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<User> users = userMapper.getAll();
        System.out.println(users.toString());
    }

    @Test
    public void testUpdate() throws Exception {
        User user = userMapper.getUser(3l);
        if(null != user) {
            System.out.println(user.toString());
            user.setName("listing");
            userMapper.update(user);
            Assert.assertTrue(("listing".equals(userMapper.getUser(3l).getName())));
        }

    }
}
