package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Instructor;
import hibernate.course.entity.InstructorDetail;

public class DeleteDemo {

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
			
			// START TRANSACTION
			session.beginTransaction();
			
			// GET INSTRUKTOR BY PRIMARY KEY / ID
			int id = 1;
			Instructor instructor = session.get(Instructor.class, id);
			System.out.println(">>Found instructor: " + instructor);
			
			// DELETE THE INSTRUCTORS
			if (instructor != null) {
				System.out.println(">>Deleting: " + instructor);
				// NOTE: IT WILL ALSO DELETE ASSOCIATED DETAILS OBJECTS BECAUSE OF CASCADING
				session.delete(instructor);
			}
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>DONE<<");
			
		} finally {
			factory.close();
		}
	}

}
