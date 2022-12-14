package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Student;

public class PrimaryKeyDemo {

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
			// CREATE 3 STUDENT OBJECTS
			System.out.println(">>Creating 3 student objects<<");
			Student student1 = new Student("John", "Doe", "john@hibernate.com");
			Student student2 = new Student("Mary", "Public", "mary@hibernate.com");
			Student student3 = new Student("Bonita", "Applebum", "bonita@hibernate.com");
			
			// START TRANSACTION
			session.beginTransaction();
			
			// SAVE STUDENT OBJECT
			System.out.println(">>Saving the student<<");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>DONE<<");
			
		} finally {
			factory.close();
		}
	}

}
