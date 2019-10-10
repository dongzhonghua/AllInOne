package xyz.dsvshx.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import xyz.dsvshx.blog.entity.Role;
import xyz.dsvshx.blog.entity.User;
import xyz.dsvshx.blog.entity.UserExample;

public interface UserMapper {


    @Select("select * from user where phone=#{phone}")
    @Results({
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "phone", property = "roles", javaType = List.class, many = @Many(select = "xyz.dsvshx.blog.mapper.UserMapper.getRoleNameByPhone")),
    })
    User getUsernameAndRolesByPhone(@Param("phone") String phone);

    @Select("select r.name from user u LEFT JOIN user_role sru on u.id= sru.User_id LEFT JOIN role r on sru.Role_id=r.id where phone=#{phone}")
    Role getRoleNameByPhone(String phone);

    @Select("select * from user where phone=#{phone}")
    User findUserByPhone(@Param("phone") String phone);

    @Select("select * from user where username=#{username}")
    User findUserByUserName(@Param("username") String username);

    @Select("select username from user where id=#{id}")
    String findUsernameById(int id);

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExampleWithBLOBs(UserExample example);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExampleWithBLOBs(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    @Select("select Role_id from user_role where User_id=#{userId}")
    List<Object> findRoleIdByUserId(@Param("userId") int userId);
}