package xyz.dsvshx.blog.service;

import org.springframework.stereotype.Service;
import xyz.dsvshx.blog.entity.User;

public interface UserService {
    User findUserByPhone(String phone);
}
