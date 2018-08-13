package com.example;

import java.time.LocalDate;
import java.time.Month;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.entity.User;
import com.example.entity.UserDetail;
import com.example.config.HibernateUtil;

public class Test {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.beginTransaction();
         //transaction.begin();//already begun in previous line

         User user = new User();
         user.setUsername("USR001");
         user.setPassword("abcd@xyz");

         UserDetail userDetail = new UserDetail();
         userDetail.setFirstName("Michael");
         userDetail.setLastName("Smith");
         userDetail.setEmail("michael.smith@example.com");
         userDetail.setDob(LocalDate.of(1985, Month.APRIL, 1));

         user.setUserDetail(userDetail);

         session.persist(user);
         transaction.commit();

         System.out.println("User saved successfully");

      } catch (Exception e) {
         if (transaction != null) {
            System.out.println("Transaction is being rolled back.");
            transaction.rollback();
         }
         e.printStackTrace();
      } finally {
         if (session != null) {
            session.close();
         }
      }
      HibernateUtil.shutdown();
   }
}