package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Instructor;
import hibernate.course.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
			
			// GET THE INSTRUCTOR DETAIL OBJECT
			int id = 3;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
			
			// PRINT THE INSTRUCTOR DETAIL
			System.out.println("Instructor detail: " + instructorDetail);
			
			// PRINT THE ASSOCIATED INSTRUCTOR
			System.out.println("Associated instructor: " + instructorDetail.getInstructor());
			
			// NOW LETS DELETE THE INSTRUCTOR DETAIL
			System.out.println("Deleting instructor detail: " + instructorDetail);
			
			// REMOVE ASSOCIATED OBJECT REFERENCE
			// BREAK BI-DIRECTIONAL LINK
			instructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(instructorDetail);
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>DONE<<");
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			// HANDLE CONNECTION LEAK ISSUE
			session.close();
			factory.close();
		}
	}

}
