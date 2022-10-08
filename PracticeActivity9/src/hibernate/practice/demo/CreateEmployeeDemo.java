package hibernate.practice.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.practice.entity.Employee;

public class CreateEmployeeDemo {

	public static void main(String[] args) {
		
		// CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		// CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
			// CREATING A EMPLOYEEs
			System.out.println(">>Creating employees");
			Employee employee1 = new Employee("Jan", "Kowalski", "Januszex");
			Employee employee2 = new Employee("Bartosz", "Bartoszewski", "PolJan");
			Employee employee3 = new Employee("Grzegorz", "Dupa", "Zadupcol S.A.");
			Employee employee4 = new Employee("Heniu", "Zbyszewski", "HenioBud");
			
			// STARTING TRANSACTION
			session.beginTransaction();
			
			// SAVING WMPLOYEE OBJECT
			System.out.println(">>Saving employees");
			session.save(employee1);
			session.save(employee2);
			session.save(employee3);
			session.save(employee4);
			
			// COMMITING TRANSACTION
			session.getTransaction().commit();
			System.out.println(">>DONE<<\n");
			
		} finally {
			session.close();
		}
	}
}
