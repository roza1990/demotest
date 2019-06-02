package com.example.demotest.controller;

import com.example.demotest.modul.User;
import com.example.demotest.repository.UserRepository;
import com.example.demotest.security.SpringUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;

@ControllerAdvice
public class BaseController {
@Autowired
private UserRepository userRepository;

  public void update(@AuthenticationPrincipal SpringUser springUser){
    if(springUser!=null) {
        User user = springUser.getUser();
        user.setLastLogoutTime(new Date());
        userRepository.save(user);
    }
  }
}
