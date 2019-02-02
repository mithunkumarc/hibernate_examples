package com.example;

//for java 9 --add-modules java.xml.bind : add this in run configurations -> Arguements ->- vm arguments
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DbManager {
  protected SessionFactory sessionFactory;

  protected void setup() {
  	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
          	.configure() // configures settings from hibernate.cfg.xml
          	.build();
  	try {
      	sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
      	System.out.println("connection = "+sessionFactory.isOpen());
  	} catch (Exception ex) {
      	StandardServiceRegistryBuilder.destroy(registry);
  	}finally {
  		if(sessionFactory != null) {
  			sessionFactory.close();
  			System.out.println("session factory closing ...");
  		}
  	}
  }

  protected void exit() {
  	sessionFactory.close();
  	if(sessionFactory.isClosed()) {
   	   System.out.println("connection closed");
  	}
  }

	public static void main(String[] args) {
  	DbManager manager = new DbManager();
  	manager.setup();
  	manager.exit();
         	 
  }

}

