package xyz.dsvshx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dsvshx.blog.entity.UserExample;
import xyz.dsvshx.blog.service.UserService;
import xyz.dsvshx.blog.entity.User;
import xyz.dsvshx.blog.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper usermapper;
    @Override
    public User findUserByPhone(String phone) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        return usermapper.selectByExample(userExample).get(0);

    }
}
