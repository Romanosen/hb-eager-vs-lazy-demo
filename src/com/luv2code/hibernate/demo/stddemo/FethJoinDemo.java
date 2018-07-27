package com.luv2code.hibernate.demo.stddemo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FethJoinDemo {
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

            Query<Instructor> query=
                     session.createQuery("select i from Instructor i "
                                    + "JOIN fetch  i.courses "
                                    + "where i.id=:theInstructorId",
                                    Instructor.class);

            //set paramether on query
            ((org.hibernate.query.Query) query).
                    setParameter("theInstructorId", theId);
            //execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();
            System.out.println("luv to code "+"Instuctor :"+tempInstructor);





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
