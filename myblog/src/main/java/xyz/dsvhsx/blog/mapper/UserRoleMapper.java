package xyz.dsvhsx.blog.mapper;


import xyz.dsvhsx.blog.model.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}