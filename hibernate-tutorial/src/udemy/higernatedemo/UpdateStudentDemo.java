package udemy.higernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import udemy.hibernatedemo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			int studentId = 1;
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			// retrieve student based on the id: primary key
			System.out.println("Getting student with id: " + studentId);
			Student student = session.get(Student.class, studentId);
			System.out.println("Read complete: " + student);
			// Update the student
			System.out.println("Updating the student...");
			student.setFirstName("Scooby");
			// commit the transaction
			session.getTransaction().commit();
			// new session
			session = factory.getCurrentSession();
			session.beginTransaction();
			// update email for all students
			System.out.println("Update email for all students");
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			session.getTransaction().commit();
			// DONE
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
