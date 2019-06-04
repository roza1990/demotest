package com.example.demotest.controller;


import com.example.demotest.modul.User;
import com.example.demotest.modul.UserType;
import com.example.demotest.repository.UserRepository;
import com.example.demotest.security.SpringUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String main() {
        return "index";
    }


    @GetMapping("/admin")
    public String admin(Model map) {
        List<User> all = userRepository.findAll();
        map.addAttribute("users", all);
        return "admin";
    }


    @GetMapping("/user")
    public String user(@AuthenticationPrincipal
                               SpringUser springUser, Model map) {
        User current = springUser.getUser();
        current = userRepository.findById(current.getId()).get();
        current.setLastLoginTime(new Date());
        userRepository.save(current);
        map.addAttribute("current", current);
        return "user";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal
                                       SpringUser springUser) {

        if (springUser.getUser().getUserType().equals("USER")) {

            return "redirect:/user";
        }


        return "redirect:/admin";

    }


    @GetMapping("/user/delete")
    public String deleteById(@RequestParam("id") int id) {
        Optional<User> u = userRepository.findById(id);
        if (u.isPresent()) {
            userRepository.deleteById(id);
        }
        return "redirect:/admin";
    }

    @GetMapping("/user/edit")
    public ModelAndView edit(@AuthenticationPrincipal
                                     SpringUser springUser, @RequestParam("id") int id, ModelAndView map) {
        Optional<User> us = userRepository.findById(id);
        if (us.isPresent()) {
            User current = springUser.getUser();
            current = userRepository.findById(current.getId()).get();
            System.out.println(current);
            map.addObject("current", current);
            map.addObject("user", us.get());
        }
        map.setViewName("edit");
        return map;
    }

    @GetMapping("/user/register")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }


    @PostMapping("/user/register")
    public String reg(@AuthenticationPrincipal
                              SpringUser springUser, @ModelAttribute("user")
                      @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";

        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        LOGGER.info("User successfully registreated!");
        return "redirect:/admin";
    }


    @PostMapping("/user/edit")
    public String edit(@AuthenticationPrincipal
                               SpringUser springUser, @ModelAttribute("user")
                       @Valid User user, BindingResult bindingResult, RedirectAttributes map) {
        if (bindingResult.hasErrors()) {
            return "edit";

        }

        String pass = userRepository.findById(user.getId()).get().getPassword();
        //String role=userRepository.findById(user.getId()).get().getUserType();
        user.setPassword(pass);
        //user.setUserType(role);
        userRepository.save(user);

        if (springUser.getUser().getUserType().equals("USER")) {
            return "redirect:/user";
        } else {
            return "redirect:/admin";
        }

    }
}

