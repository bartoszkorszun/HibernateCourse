package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import hibernate.course.entity.Course;
import hibernate.course.entity.Instructor;
import hibernate.course.entity.InstructorDetail;

public class FetchJoinDemo {

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
			
			// HIBERNATE QUERY FROM HQL
			
			// GET THE INSTRUCTOR FROM DB
			int id = 1;
			
			Query<Instructor> query = session.createQuery("select i from Instructor i "
															+ "JOIN FETCH i.courses where i.id=:theInstructorId", 
															Instructor.class); 
			
			// SET PARAMETER ON QUERY 
			query.setParameter("theInstructorId", id);
			
			// EXECUTE QUERY AND GET THE INSTRUCTOR
			Instructor instructor = query.getSingleResult();
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			session.close();
			
			System.out.println("\n>>The session is closed<<\n");
			
			// GET COURSES FOR THE INSTRUCTOR
			System.out.println(">>Courses: " + instructor.getCourses());
			
			System.out.println(">>Luv2Code: DONE<<");
			
		} finally {
			factory.close();
		}
	}

}
