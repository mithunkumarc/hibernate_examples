package com.example.hibernate;

//--add-modules java.xml.bind : add this in run configurations -> Arguements ->- vm arguments
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
 
public class BookManager {
	protected SessionFactory sessionFactory;

	protected void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			//System.out.println("connection = "+sessionFactory.isOpen());
		} catch (Exception ex) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	protected void exit() {
		sessionFactory.close();
	}

	protected void create() {
		Book book = new Book();
		book.setTitle("CompleteReference Java");
		book.setAuthor("Herbert sheldt");
		book.setPrice(50.59f);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();
		session.close();
		
		
		
		/*Book book = new Book();
		book.setTitle("Effective Java");
		book.setAuthor("Joshua Bloch");
		book.setPrice(32.59f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(book);

		session.getTransaction().commit();
		session.close();*/
	}
	
	
	protected void search(long bookID) {
		System.out.println("first session");
	    Session session = sessionFactory.openSession();	    
	    Book book = session.get(Book.class, bookID);	 
	    System.out.println("Title: " + book.getTitle());
	    System.out.println("Author: " + book.getAuthor());
	    System.out.println("Price: " + book.getPrice());	 
	    session.close();
	    
	    System.out.println("second session");
	    Session session2 = sessionFactory.openSession();	    
	    Book book2 = session2.get(Book.class, bookID);	 
	    System.out.println("Title: " + book2.getTitle());
	    System.out.println("Author: " + book2.getAuthor());
	    System.out.println("Price: " + book2.getPrice());	 
	    session.close();	    	    
	}
	
	protected void read() {
		Session session = sessionFactory.openSession();
		 TypedQuery<Book> query = session.createQuery("FROM Book");
		 List<Book> result = query.getResultList();
		/*List<Book> bookList = new ArrayList(); 
		List<Book> list = session.createQuery("FROM Book").getResultList();*/
		
	    result.forEach(book -> System.out.println(book.getTitle()));
		session.close();
	}

	protected void update() {
		Book book = new Book();
		book.setId(1);
		book.setTitle("Ultimate Java Programming");
		book.setAuthor("Nam Ha Minh");
		book.setPrice(19.99f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.update(book);

		session.getTransaction().commit();
		session.close();
	}

	protected void delete() {
		Book book = new Book();
		book.setId(2);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.delete(book);

		session.getTransaction().commit();
		session.close();
	}
	
	public static void main(String[] args) {
		BookManager manager = new BookManager();
		manager.setup();
		//test one by one by uncommenting
		//manager.create();
		//manager.update();
		manager.read();
		//manager.delete();
		//without second cache
		//manager.search(1);
		//manager.exit();
				
	}

}
