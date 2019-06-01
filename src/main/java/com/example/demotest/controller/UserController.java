package com.example.demotest.controller;


import com.example.demotest.modul.User;
import com.example.demotest.modul.UserType;
import com.example.demotest.repository.UserRepository;
import com.example.demotest.security.SpringUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
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

        List<User> all =  userRepository.findAll();
        map.addAttribute("users", all);
        return "admin";
    }



//    @PostMapping("/register")
//    public String register(@ModelAttribute User user ) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return "redirect:/";
//    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal
                                       SpringUser springUser, RedirectAttributes map) {

        if (springUser.getUser().getUserType().equals("USER")) {
            User current=springUser.getUser();
            current.setLastLoginTime(new Date());
            userRepository.save(current);
            map.addFlashAttribute("current",current);
            return "redirect:/user";
        }


        return "redirect:/admin";

    }

    @GetMapping("/mylogout")
    public String logout(@AuthenticationPrincipal
                                     SpringUser springUser, RedirectAttributes map){
        User current=springUser.getUser();
        current.setLastLogoutTime(new Date());
        System.out.println(userRepository.save(current));

        return "index";
    }

    @GetMapping("/user/delete")
    public String deleteById(@RequestParam("id") int id){
        Optional<User> u=userRepository.findById(id);
        if(u.isPresent()){
            userRepository.deleteById(id);
        }
        return "redirect:/admin";
    }

    @GetMapping("/user/edit")
    public ModelAndView edit(@RequestParam("id") int id, ModelAndView map){
        Optional<User> us=userRepository.findById(id);
        if(us.isPresent()){
            map.addObject("user", us.get());
        }
        map.setViewName("register");
        return map;
    }

    @GetMapping("/user/register")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        modelAndView.addObject("user", new User());

        return modelAndView;
    }


    @PostMapping("/register")
    public String editById(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/login";}
}

