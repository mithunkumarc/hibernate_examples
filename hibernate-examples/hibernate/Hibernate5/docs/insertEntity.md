java 10 hibernate 5.3.6
> https://www.sitepoint.com/reflection-vs-encapsulation-in-the-java-module-system/#commandlineescapehatches 


### module-info.java
        module newinsert_module {
          requires org.hibernate.orm.core;
          requires java.xml.bind;
          requires mysql.connector.java;
          requires java.sql;
          requires javax.persistence;
          opens newinsert;
          exports newinsert;  //package name
        }


### pom.xml
        <dependencies>

          <!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
                    <dependency>
                        <groupId>org.hibernate</groupId>
                        <artifactId>hibernate-core</artifactId>
                        <version>5.3.6.Final</version>
                    </dependency>
                      <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>6.0.5</version>
                      </dependency>

                      <!-- https://mvnrepository.com/artifact/org.eclipse.persistence/javax.persistence -->
                    <dependency>
                        <groupId>org.eclipse.persistence</groupId>
                        <artifactId>javax.persistence</artifactId>
                        <version>2.2.0</version>
                    </dependency>

          </dependencies>

  
### Mobile.java, Entity class

        package newinsert;

        import javax.persistence.Entity;
        import javax.persistence.Id;
        import javax.persistence.Table;
        import javax.persistence.Column;

        @Entity
        @Table(name = "Mobile")
        public class Mobile {
            @Id
            @Column(name = "id")
            private int id;

            @Column(name = "brandname")
            private String brandName;

            @Column(name = "price")
            private int price;

            public int getId() {
             return id;
            }

            public void setId(int id) {
             this.id = id;
            }

            public String getBrandName() {
             return brandName;
            }

            public void setBrandName(String brandName) {
             this.brandName = brandName;
            }

            public int getPrice() {
             return price;
            }

            public void setPrice(int price) {
             this.price = price;
            }    
        }




### DbManager.java

        package newinsert;

        import java.util.HashMap;
        import java.util.Map;
        import org.hibernate.SessionFactory;
        import org.hibernate.boot.Metadata;
        import org.hibernate.boot.MetadataSources;
        import org.hibernate.boot.registry.StandardServiceRegistry;
        import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
        import org.hibernate.Session;
        import org.hibernate.Transaction;

        public class DbManager {
              private static StandardServiceRegistry registry;
              private static SessionFactory sessionFactory;


              public static void main(String[] args) {
              Session session = null;
              Transaction transaction = null;
              try {
                session = getSessionFactory().openSession();
                transaction = session.beginTransaction();
                //transaction.begin();

                Mobile mobile = new Mobile();
                mobile.setId(55);
                mobile.setBrandName("oppo2");
                mobile.setPrice(26000);
                session.save(mobile);

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

              shutdown();
               }


              public static SessionFactory getSessionFactory() {
              if (sessionFactory == null) {
                try {
                  StandardServiceRegistryBuilder registryBuilder =
                      new StandardServiceRegistryBuilder();

                  Map<String, String> settings = new HashMap<>();
                  settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                  settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
                  settings.put("hibernate.connection.username", "root");
                  settings.put("hibernate.connection.password", "root");
                  settings.put("hibernate.show_sql", "true");
                  settings.put("hibernate.hbm2ddl.auto", "update");

                  registryBuilder.applySettings(settings);

                  registry = registryBuilder.build();

                  MetadataSources sources = new MetadataSources(registry)
                      .addAnnotatedClass(Mobile.class);

                  Metadata metadata = sources.getMetadataBuilder().build();

                  sessionFactory = metadata.getSessionFactoryBuilder().build();
                } catch (Exception e) {
                  e.printStackTrace();
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




### database :
        create table Mobile(id int primary key, brandname text, price int);
        insert into Mobile(id, brandname, price) values (1,"galaxy",5000);
