package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			
			// GET A NEW SESSION AND START TRANSACTION
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// RETRIEVE A STUDENT BASED IN THE ID: PRIMARY KEY
			System.out.println("\n>>Getting student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println(">>Updating student");
			myStudent.setFirstName("Scooby");
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			// NEW CODE
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// UPDATE EMAIL FOR ALL STUDENTS
			System.out.println(">>Update email for all students");
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();

			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>DONE<<");
			
		} finally {
			factory.close();
		}
	}

}
