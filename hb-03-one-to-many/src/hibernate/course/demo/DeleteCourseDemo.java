package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Course;
import hibernate.course.entity.Instructor;
import hibernate.course.entity.InstructorDetail;

public class DeleteCourseDemo {

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
			
			// GET A COURSE
			int id = 10;
			Course course = session.get(Course.class, id);
			
			// DELETE A COURSE
			System.out.println("Deleting the course: " + course);
			session.delete(course);
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>DONE<<");
			
		} finally {
			factory.close();
		}
	}

}
