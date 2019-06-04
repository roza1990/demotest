package com.example.demotest.repository;

import com.example.demotest.modul.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface UserRepository extends  JpaRepository<User, Integer>  {

    User findByEmail(String email);


//    @Transactional
//    @Modifying
//    @Query(value="UPDATE iguan_test_task set name=:name ,age=:age where id=:id",
//            nativeQuery = true)
//    void update(@Param("id") int id,@Param("name") String name,@Param("age") int age);


}
