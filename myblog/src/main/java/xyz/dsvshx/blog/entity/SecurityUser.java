package xyz.dsvshx.blog.entity;

import lombok.Data;

import java.util.List;

@Data

public class SecurityUser {
    private Integer id;

    private String phone;

    private String username;

    private String password;

    private String gender;

    private String truename;

    private String birthday;

    private String email;

    private String personalbrief;

    private String recentlylanded;

    private String avatarimgurl;
    private List<Role> roles;

    public SecurityUser(Integer id, String phone, String username, String password, String gender, String truename, String birthday, String email, String personalbrief, String recentlylanded, String avatarimgurl, List<Role> roles) {
        this.id = id;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.truename = truename;
        this.birthday = birthday;
        this.email = email;
        this.personalbrief = personalbrief;
        this.recentlylanded = recentlylanded;
        this.avatarimgurl = avatarimgurl;
        this.roles = roles;
    }
}
