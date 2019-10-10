package xyz.dsvshx.blog.service;

import com.alibaba.fastjson.JSONObject;
import xyz.dsvshx.blog.entity.User;
import xyz.dsvshx.blog.utils.Result;

public interface UserService {
    User findUserByPhone(String phone);
    User findUserByusername(String phone);

    int findIdByUsername(String username);

    JSONObject getHeadPortraitUrl(int idByUsername);

    Result getUserPersonalInfoByUsername(String username);

    Result savePersonalDate(User user, String username);

    String insert(User user);

    String findUsernameByid(Integer answererid);

    boolean isSuperAdmin(String phone);
}
