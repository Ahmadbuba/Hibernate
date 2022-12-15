package com.luv2code.hibernate.demo;

import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		// create session
		
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction 
			session.beginTransaction();
			
			// get the instructor detail
			int theId = 2999;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			// print the instructor detail
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			
			// print the associated instructor
			System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();
			
			factory.close();
		}
		
		

	}

}
