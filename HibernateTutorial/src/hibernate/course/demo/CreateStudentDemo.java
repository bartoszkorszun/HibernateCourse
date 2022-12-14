package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
			// USE SESSION OBJECT TO SAVE JAVA OBJECT
			// CREATE A STUDENT OBJECT
			System.out.println(">>Creating a new student object<<");
			Student student = new Student("Paul", "Wall", "paul@hibernate.com");
			
			// START TRANSACTION
			session.beginTransaction();
			
			// SAVE STUDENT OBJECT
			System.out.println(">>Saving the student<<");
			session.save(student);
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>DONE<<");
			
		} finally {
			factory.close();
		}
	}

}
