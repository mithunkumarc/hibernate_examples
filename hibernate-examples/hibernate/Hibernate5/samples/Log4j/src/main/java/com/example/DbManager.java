package com.example;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DbManager {
	private static StandardServiceRegistry registry;
	  private static SessionFactory sessionFactory;
	  
	   private static final Logger logger = LogManager.getLogger(DbManager.class);

	   public static void main(String[] args) {
	      Session session = null;
	      Transaction transaction = null;
	      try {
	         //session = getSessionFactory().openSession();
	         //transaction = session.getTransaction();
	         //transaction.begin();
	         System.out.println("******************************");
	         /*Customer customer = new Customer();
	         customer.setName("Joe");
	         session.persist(customer);*/

	         //transaction.commit();

	         logger.info("empty session *****");
	         System.out.println("************77777************");
	      } catch (Exception e) {
	         if (transaction != null) {
	            transaction.rollback();
	         }
	         logger.error("Failed to save customer..." + e);
	      } finally {
	         if (session != null) {
	            session.close();
	         }
	      }

	      shutdown();
	   }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

	  public static SessionFactory getSessionFactory() {
	    if (sessionFactory == null) {
	      try {
	        StandardServiceRegistryBuilder registryBuilder = 
	            new StandardServiceRegistryBuilder();

	        Map<String, String> settings = new HashMap();
	        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
	        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/test");
	        settings.put(Environment.USER, "root");
	        settings.put(Environment.PASS, "root");
	        settings.put(Environment.HBM2DDL_AUTO, "update");

	        registryBuilder.applySettings(settings);

	        registry = registryBuilder.build();

	        MetadataSources sources = new MetadataSources(registry)
	            .addAnnotatedClass(Employee.class);

	        Metadata metadata = sources.getMetadataBuilder().build();

	        sessionFactory = metadata.getSessionFactoryBuilder().build();
	      } catch (Exception e) {
	        System.out.println("SessionFactory creation failed");
	        if (registry != null) {
	          StandardServiceRegistryBuilder.destroy(registry);
	        }
	      }
	    }
	    return sessionFactory;
	  }

	  public static void shutdown() {
	    if (registry != null) {
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	  }
}
