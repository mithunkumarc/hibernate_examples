package com.example;


import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.config.*;
import com.example.entity.Address;
import com.example.entity.Employee;


public class Test {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.beginTransaction();
         

         Address address1 = new Address("Bengaluru", "Karnataka", "India", "560016");
         Address address2 = new Address("Jaipur", "Rajasthan", "India", "302017");

         // Employee1 have 2 addresses
         Employee employee1 = new Employee("Ravindra Singh", "Sales Manager", 450000);
         employee1.getAddresses().add(address1);
         employee1.getAddresses().add(address2);

         // Employee2 have 1 address
         Employee employee2 = new Employee("Mohit Sharma", "Software Engineer", 850000);
         employee2.getAddresses().add(address1);

         session.save(employee1);
         session.save(employee2);
         transaction.commit();

         System.out.println("Records saved successfully");

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