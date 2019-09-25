package xyz.dsvshx.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import xyz.dsvshx.blog.entity.Role;
import xyz.dsvshx.blog.entity.SecurityUser;
import xyz.dsvshx.blog.entity.User;
import xyz.dsvshx.blog.mapper.SecurityUserMapper;
import xyz.dsvshx.blog.mapper.UserMapper;
import xyz.dsvshx.blog.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userMapper.getUsernameAndRolesByPhone(s);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");

        }
        //更新用户登录时间


        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
