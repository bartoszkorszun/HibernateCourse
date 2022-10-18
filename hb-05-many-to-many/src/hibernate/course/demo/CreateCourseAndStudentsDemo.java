package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Course;
import hibernate.course.entity.Instructor;
import hibernate.course.entity.InstructorDetail;
import hibernate.course.entity.Review;
import hibernate.course.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		
		// CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		// CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
			
			// START TRANSACTION
			session.beginTransaction();
			
			// CREATE COURSE
			Course course = new Course("Pacman - How to score 1 mln points");
			
			
			
			// SAVE THE COURSE AND REVERADGE THE CASCADE ALL
			System.out.println("Saving the course: " + course);
			session.save(course);
			System.out.println("Course saved...");
			
			// CREATE THE STUDENTS
			Student student1 = new Student("John", "Doe", "john@mail.com");
			Student student2 = new Student("Mary", "Public", "mary@mail.com");
			
			// ADD STUDENTS TO THE COURSE
			course.addStudents(student1);
			course.addStudents(student2);
			
			// SAVE THE STUDENTS
			System.out.println("Saving students: " + course.getStudents());
			session.save(student1);
			session.save(student2);
			System.out.println("Students saved...");
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>DONE<<");
			
		} finally {
			factory.close();
		}
	}

}
