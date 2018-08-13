package com.example;


import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.config.*;
import com.example.entity.Department;
import com.example.entity.Employee;


public class Test {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.beginTransaction();
         //transaction.begin();

         Employee employee1 = new Employee();
         employee1.setName("Adam");
         employee1.setDesignation("Manager");

         Employee employee2 = new Employee();
         employee2.setName("Miller");
         employee2.setDesignation("Software Engineer");

         Employee employee3 = new Employee();
         employee3.setName("Smith");
         employee3.setDesignation("Associate  Engineer");

         Department department = new Department();
         department.setName("IT Department");
         department.getEmployees().add(employee1);
         department.getEmployees().add(employee2);
         department.getEmployees().add(employee3);

         session.persist(department);

         transaction.commit();
      } catch (Exception e) {
         if (transaction != null) {
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