package com.elderpereira.customerservice.security;

import com.elderpereira.customerservice.controller.CustomerController;
import com.elderpereira.customerservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable().authorizeRequests()
               .anyRequest()
               .authenticated()
//               .and()
//               .formLogin()
               .and()
               .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        logger.info("Password: {}", passwordEncoder.encode("pass123"));
        auth.inMemoryAuthentication()
                .withUser("elder")
                .password(passwordEncoder.encode("pass123"))
                .roles("USER", "ADMIN")
                .and()
                .withUser("carlos")
                .password(passwordEncoder.encode("pass123"))
                .roles("USER");

        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }
}
