package com.luv2code.hibernate.demo.stddemo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLasyDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory= (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        //create session
        Session session=factory.getCurrentSession();
        try {



            session.beginTransaction();

            //get the instructor from db
            int theId=1;
            Instructor tempInstructor=session.get(Instructor.class,theId);

            System.out.println("luv to code "+"Instuctor :"+tempInstructor);




            System.out.println("luv to code "+"Courses: "+ tempInstructor.getCourses());

            session.getTransaction().commit();

            // close the session
            session.close();
            System.out.println("\nthe session is now closed\n");

            //since courses are lazy loaded... this should fail
            System.out.println("luv to code "+"Courses: "+ tempInstructor.getCourses());

            System.out.println("luv to code "+"Done!");

        }
        finally {
            session.close();
            factory.close();
        }




    }
}
