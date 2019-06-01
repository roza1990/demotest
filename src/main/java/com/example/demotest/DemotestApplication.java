package com.example.demotest;

import com.example.demotest.modul.Gender;
import com.example.demotest.modul.User;
import com.example.demotest.modul.UserType;
import com.example.demotest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@PropertySources(value = {@PropertySource("classpath:application.properties") })
public class DemotestApplication  {

    public static void main(String[] args) {
        SpringApplication.run(DemotestApplication.class, args);
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
