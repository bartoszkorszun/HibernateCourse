package hibernate.course.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.course.entity.Course;
import hibernate.course.entity.Instructor;
import hibernate.course.entity.InstructorDetail;
import hibernate.course.entity.Review;
import hibernate.course.entity.Student;

public class CreateCourseAndReviewsDemo {

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
			
			// ADD SOME REVIEWS
			course.addReview(new Review("Great course - loved it!"));
			course.addReview(new Review("Cool course - job well done!"));
			course.addReview(new Review("What a dumb course!"));
			
			// SAVE THE COURSE AND REVERADGE THE CASCADE ALL
			System.out.println("Saving the course: " + course);
			System.out.println(course.getReviews());
			session.save(course);
			
			// COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println(">>DONE<<");
			
		} finally {
			factory.close();
		}
	}

}
