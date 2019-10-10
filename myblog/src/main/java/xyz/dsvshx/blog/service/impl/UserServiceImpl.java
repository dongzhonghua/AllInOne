package xyz.dsvshx.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.dsvshx.blog.constant.RoleConstant;
import xyz.dsvshx.blog.entity.UserExample;
import xyz.dsvshx.blog.entity.UserRole;
import xyz.dsvshx.blog.mapper.UserRoleMapper;
import xyz.dsvshx.blog.service.UserService;
import xyz.dsvshx.blog.entity.User;
import xyz.dsvshx.blog.mapper.UserMapper;
import xyz.dsvshx.blog.utils.Result;
import xyz.dsvshx.blog.utils.ResultUtil;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public User findUserByPhone(String phone) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        return userMapper.selectByExample(userExample).get(0);

    }

    @Override
    public User findUserByusername(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(username);
        return userMapper.selectByExample(userExample).get(0);

    }

    @Override
    public int findIdByUsername(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.get(0) != null) {
            return users.get(0).getId();
        } else {
            return 0;
        }
    }

    @Override
    public JSONObject getHeadPortraitUrl(int idByUsername) {
        JSONObject jsonObject = new JSONObject();

        String avatarImgUrl = userMapper.selectByPrimaryKey(idByUsername).getAvatarimgurl();
        if (!"".equals(avatarImgUrl) && avatarImgUrl != null) {
            jsonObject.put("status", 200);
            jsonObject.put("avatarImgUrl", avatarImgUrl);
        }
        return jsonObject;
    }

    @Override
    public Result getUserPersonalInfoByUsername(String username) {
        User user = userMapper.findUserByUserName(username);
        return ResultUtil.success(user, "success");
    }

    @Override
    public Result savePersonalDate(User user, String username) {
        user.setUsername(user.getUsername().trim().replaceAll(" ", ""));
        String newName = user.getUsername();
        if (newName.length() > 35) {
            return ResultUtil.error(501, "太长");
        } else if ("".equals(newName)) {
            return ResultUtil.error(502, "是空");
        }

        //改了昵称
        if (!newName.equals(username)) {
            if (usernameIsExist(newName)) {
                return ResultUtil.error(500, "名字有人占用");

            }

            //注销当前登录用户,注销是否有必要，如果不注销可能下面操作会出问题，
            //但是有没有什么更好的方案可以直接再次登录呢。
            //SecurityContextHolder.getContext().setAuthentication(null);
        }
        //没改昵称
        else {
            return ResultUtil.success(201);

        }
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        userMapper.updateByExampleSelective(user, userExample);

        return ResultUtil.success("successs");
    }

    @Override
    public String insert(User user) {
        user.setUsername(user.getUsername().trim().replaceAll(" ", ""));
        String username = user.getUsername();

        if(username.length() > 35 || "".equals(username)){
            return "4";
        }
        if(userIsExist(user.getPhone())){
            return "1";
        }
        if("male".equals(user.getGender())){
            user.setAvatarimgurl("https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/user/avatar/noLogin_male.jpg");
        } else {
            user.setAvatarimgurl("https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/user/avatar/noLogin_female.jpg");
        }
        userMapper.insert(user);
        int userId = userMapper.findUserByPhone(user.getPhone()).getId();
        insertRole(userId, RoleConstant.ROLE_USER);
        return "2";
    }

    @Override
    public String findUsernameByid(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user.getUsername();
    }

    @Override
    public boolean isSuperAdmin(String phone) {
        int userId = userMapper.findUserByPhone(phone).getId();
        List<Object> roleIds = userMapper.findRoleIdByUserId(userId);

        for(Object i : roleIds){
            if((int)i == 3){
                return true;
            }
        }
        return false;
    }

    private void insertRole(int userId, int roleUser) {

        userRoleMapper.insert(new UserRole(userId,roleUser));
    }


    private boolean usernameIsExist(String newName) {
        return userMapper.findUserByUserName(newName) != null;
    }

    private boolean userIsExist(String phone) {
        return userMapper.findUserByPhone(phone) != null;
    }
}
