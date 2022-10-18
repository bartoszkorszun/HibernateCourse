package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Course;
import hibernate.course.entity.Instructor;
import hibernate.course.entity.InstructorDetail;
import hibernate.course.entity.Review;
import hibernate.course.entity.Student;

public class AddCoursesForMaryDemo {

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
			
			// GET MARY FROM DB
			int id = 2;
			Student student = session.get(Student.class, id);
			
			System.out.println("Loaded student: " + student);
			System.out.println("Course: " + student.getCourses());
			
			// CREATE MORE COURSES
			Course course1 = new Course("Rubik's cube - How to speed cube");
			Course course2 = new Course("Atari 2600 - Game Development");
			
			// ADD MARY TO THOSE COURSES
			course1.addStudents(student);
			course2.addStudents(student);
			
			// SAVE COURSES
			System.out.println("Saving courses...");
			session.save(course1);
			session.save(course2);
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>DONE<<");
			
		} finally {
			factory.close();
		}
	}

}
