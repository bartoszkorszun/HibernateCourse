package hibernate.practice.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.practice.entity.Employee;

public class DeleteEmployeeDemo {

	public static void main(String[] args) {
		// CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		// CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
			
			int employeeId = 2;
			
			// STARTING TRANSACTION
			session.beginTransaction();
			
			Employee employee = session.get(Employee.class, employeeId);
			
			System.out.println(">>Deleting employee id: " + employeeId);
			session.delete(employee);
			
			// COMMITING TRANSACTION
			session.getTransaction().commit();
			System.out.println(">>DONE<<\n");
			
		} finally {
			session.close();
		}

	}

}
