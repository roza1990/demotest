package com.example.demotest.modul;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="iguan_test_task")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    private String password;
//    @Column(columnDefinition = "varchar(255)")
//    private UserType userType;
//    @Column( columnDefinition = "varchar(255)")
//
//    private Gender gender;
@Column
private String userType;
    @Column
    private Date lastLoginTime = new Date();
    @Column
    private Date lastLogoutTime= new Date();
    @Column
    private int age;
    @Column
    private int blocked=1;

}
