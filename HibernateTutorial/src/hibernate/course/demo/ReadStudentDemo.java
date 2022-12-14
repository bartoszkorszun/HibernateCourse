package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Student;

public class ReadStudentDemo {

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
			Student student = new Student("Daffy", "Duck", "daffy@hibernate.com");
			
			// START TRANSACTION
			session.beginTransaction();
			
			// SAVE STUDENT OBJECT
			System.out.println(">>Saving the student<<");
			System.out.println(student);
			session.save(student);
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			// NEW CODE
			// FIND OUT THE STUDENTS ID
			System.out.println(">>Saved student. Generated id: " + student.getId());
			
			// GET A NEW SESSION AND START TRANSACTION
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// RETRIEVE A STUDENT BASED IN THE ID: PRIMARY KEY
			System.out.println("\n>>Getting student with id: " + student.getId());
			Student myStudent = session.get(Student.class, student.getId());
			System.out.println(">>Get complete: " + myStudent);
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>DONE<<");
			
		} finally {
			factory.close();
		}
	}

}
