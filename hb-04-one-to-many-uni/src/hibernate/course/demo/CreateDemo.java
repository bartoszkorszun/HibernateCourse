package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Course;
import hibernate.course.entity.Instructor;
import hibernate.course.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		
		// CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
			
			// CREATE OBJECTS
			Instructor instructor = new Instructor("Bartosz", "Korszun", "bartosz@email.com");
			InstructorDetail instructorDetail = new InstructorDetail("http://www.bartosz.com/youtube", "I love Java!");
			
			// ASSOCIATE THE OBJECTS
			instructor.setInstructorDetail(instructorDetail);
			
			// START TRANSACTION
			session.beginTransaction();
			
			// SAVE THE INSTRUCTOR
			// NOTE: THIS WILL ALSO SAVE THE DETAILS OBJECT BECAUSE OF CASCADING ALL
			System.out.println(">>Saving instructor: " + instructor);
			session.save(instructor);
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>DONE<<");
			
		} finally {
			// ADD CLEANUP CODE
			session.close();
			factory.close();
		}
	}

}
