package xyz.dsvshx.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService())
                .passwordEncoder(new MyPassWordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index", "/aboutme", "/archives", "/categories", "/friendlylink", "/tags", "/update")
                .permitAll()
                .antMatchers("/editor", "/user", "/mylove").hasAnyRole("USER")
                .antMatchers("/ali").hasAnyRole("ADMIN")
                .antMatchers("/superadmin", "/myheart", "/today", "/yesterday","/getUserByPhone").hasAnyRole("SUPERADMIN")
                //.antMatchers().
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");

        http.csrf().disable();
    }
}
