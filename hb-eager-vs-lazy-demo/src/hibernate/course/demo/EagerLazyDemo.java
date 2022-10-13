package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Course;
import hibernate.course.entity.Instructor;
import hibernate.course.entity.InstructorDetail;

public class EagerLazyDemo {

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
			
			System.out.println(">>Luv2Code: Instructor: " + instructor);
			
			// GET COURSE FROM THE INSTRUCTOR
			System.out.println(">>Luv2Code: Courses: " + instructor.getCourses());
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>Luv2Code: DONE<<");
			
		} finally {
			factory.close();
		}
	}

}
