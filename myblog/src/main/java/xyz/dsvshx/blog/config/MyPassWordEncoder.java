package xyz.dsvshx.blog.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.dsvshx.blog.utils.MD5Util;

public class MyPassWordEncoder implements PasswordEncoder {
    MD5Util md5Util = new MD5Util();
    @Override
    public String encode(CharSequence rawPassword) {
        return md5Util.encode((String) rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(md5Util.encode((String)rawPassword));
    }
}
