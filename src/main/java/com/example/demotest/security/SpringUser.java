package com.example.demotest.security;

import com.example.demotest.modul.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class SpringUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public SpringUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}