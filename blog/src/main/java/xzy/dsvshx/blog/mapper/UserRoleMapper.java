package xzy.dsvshx.blog.mapper;

import xzy.dsvshx.blog.model.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}