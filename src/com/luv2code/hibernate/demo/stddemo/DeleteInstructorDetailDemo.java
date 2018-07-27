package com.luv2code.hibernate.demo.stddemo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory= (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        //create session

        Session session=factory.getCurrentSession();

        try {


            session.beginTransaction();

            //get the instructor detail object

            int theId=3;
            InstructorDetail tempInstructorDetail=
                    session.get(InstructorDetail.class,theId);
            //print the instructor detail object
            System.out.println("Instructor detail object"+tempInstructorDetail);

            //print the associated instructor
            System.out.println(" associated Instructor object"+tempInstructorDetail.getInstructor());

            //now delete instructor detail also delete instructor
            System.out.println("Deleting temp instructor detail"
                    +tempInstructorDetail);

            //remove the associated object referance
            //break bi-derectional link
            tempInstructorDetail.getInstructor().setInstructorDetail(null);
            //deleting
            session.delete(tempInstructorDetail);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }




    }
}
