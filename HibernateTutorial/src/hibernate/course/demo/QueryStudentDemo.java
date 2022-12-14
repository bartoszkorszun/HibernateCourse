package hibernate.course.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {			
			// START TRANSACTION
			session.beginTransaction();
			
			// QUERY STUDENTS
			List<Student> students = session.createQuery("from Student").list();
			
			// DISPLAY STUDENTS
			displayStudents(students);
			
			// QUERY STUDENTS: LASTNAME = 'DOE'
			students = session.createQuery("from Student s where s.lastName='Doe'").list();

			// DISPLAY STUDENTS
			System.out.println("\nStudents who have last name of Doe");
			displayStudents(students);
			
			// QUERY STUDENTS: LASTNAME = 'DOE' OR FIRSTNAME = 'DAFFY'
			students = session.createQuery("from Student s where s.lastName='Doe' or s.firstName='Daffy'").list();
			
			// DISPLAY STUDENTS
			System.out.println("\nStudents who have last name of Doe or first name Daffy");
			displayStudents(students);
			
			// QUERY STUDENTS: EMAIL LIKE '%HIBERNATE.COM'
			students = session.createQuery("from Student s where s.email like '%hibernate.com'").list();
			
			// DISPLAY STUDENTS
			System.out.println("\nStudents who have email ending with hibernate.com");
			displayStudents(students);
						
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>DONE<<");
			
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		for (Student student : students) {
			System.out.println(student);
		}
	}

}
