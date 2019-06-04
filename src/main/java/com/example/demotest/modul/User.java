package com.example.demotest.modul;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "iguan_test_task")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column


    private int id;
    @Column
    @NotEmpty(message = "Name may not be empty")
    @Size(min = 3, max = 12, message = "Name must be between 3 and 12 characters long")
    private String name;
    @Column
    @NotEmpty(message = "SurName may not be empty")
    @Size(min = 5, max = 20, message = "Name must be between 5 and 20 characters long")
    private String surname;
    @Column
    @Email
    @NotEmpty(message = "Email may not be empty")
    private String email;
    @Column
    //@NotEmpty(message = "Password may not be empty")
    //@Size(min = 6, max = 20, message = "Password must be between 10 and 20 characters long")
    private String password;
    @Column
    @NotEmpty(message = "UserType may not be empty")
    private String userType;
    @Column
    private Date lastLoginTime = new Date();
    @Column
    private Date lastLogoutTime = new Date();
    @Column

    private int age;
    @Column
    private int blocked = 1;

}
