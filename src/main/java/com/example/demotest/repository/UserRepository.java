package com.example.demotest.repository;

import com.example.demotest.modul.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);



//@Autowired
//    private SessionFactory sessionFactory;
//    public void createUser(User user){
//        Session session=null;
//        try{
//            session=sessionFactory.openSession();
//            session.beginTransaction();
//            session.save(user);
//            System.out.println("User Id  ");
//            session.getTransaction().commit();
//
//        }catch(Exception e){
//            System.out.println(e + "fgdg");
//        }
//
//    }
//    public User findByEmail(String email){
//        Session session=sessionFactory.openSession();
//        Criteria cr = session.createCriteria(User.class);
//        cr.add(Expression.eq("email",email));
//        return (User) cr.list().get(0);
//
//
//    }
}
