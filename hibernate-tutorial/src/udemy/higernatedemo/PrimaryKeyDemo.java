package udemy.higernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import udemy.hibernatedemo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			// create 3 student objects
			System.out.println("Creating a new student object...");
			Student student1 = new Student("John", "Doe", "john@hibernatedemo.com");
			Student student2 = new Student("Mary", "Public", "mary@hibernatedemo.com");
			Student student3 = new Student("Bonita", "Appleaum", "bonita@hibernatedemo.com");
			// start a transaction
			session.beginTransaction();
			// save the student object
			System.out.println("Saving the students...");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
