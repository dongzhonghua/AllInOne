package xyz.dsvshx.blog.mapper;

import xyz.dsvshx.blog.entity.SecurityUser;


public interface SecurityUserMapper {
    SecurityUser getUserByPhone(String phone);
}
