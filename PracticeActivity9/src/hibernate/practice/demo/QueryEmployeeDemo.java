package hibernate.practice.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.practice.entity.Employee;

public class QueryEmployeeDemo {

	public static void main(String[] args) {
		// CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		// CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
			// STARTING TRANSACTION
			session.beginTransaction();
			
			List<Employee>employees = session.createQuery("from Employee s where s.company='Zadupcol S.A.'").list();
			
			System.out.println(">>List of employees from Zadupcol S.A.");
			displayStudents(employees);
			
			// COMMITING TRANSACTION
			session.getTransaction().commit();
			System.out.println(">>DONE<<\n");
			
		} finally {
			session.close();
		}
	}

	private static void displayStudents(List<Employee> employees) {
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}
}
