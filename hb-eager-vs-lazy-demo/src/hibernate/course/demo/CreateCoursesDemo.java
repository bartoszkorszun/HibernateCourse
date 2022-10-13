package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Course;
import hibernate.course.entity.Instructor;
import hibernate.course.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		
		// CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		// CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
			
			// START TRANSACTION
			session.beginTransaction();
			
			// GET THE INSTRUCTOR FROM DB
			int id = 1;
			Instructor instructor = session.get(Instructor.class, id);
			
			// CREATE SOME COURSES
			Course course1 = new Course("Air guitar - the ultimate guide");
			Course course2 = new Course("The Pinball Masterclass");
			
			// ADD COURSES TO INSTRUCTOR
			instructor.add(course1);
			instructor.add(course2);
			
			// SAVE COURSES
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
