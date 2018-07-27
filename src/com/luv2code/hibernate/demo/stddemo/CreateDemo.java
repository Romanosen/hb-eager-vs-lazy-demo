package com.luv2code.hibernate.demo.stddemo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
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

            //Create the objects
//            Instructor tempInstructor=
//                    new Instructor("Savchuk","Roman","romanosen@gmail.com");
//
//            InstructorDetail tempInstructorDetail=
//                    new InstructorDetail("http://www.romanosen.com/youtube", "programming");
            Instructor theInstructor=
                    new Instructor("Savchuk","Tamara","tamarasavchuk@gmail.com");

            InstructorDetail theInstructorDetail=
                    new InstructorDetail("http://www.tamarasavchuk.com/youtube", "cooking");
            //associate the objects
           // tempInstructor.setInstructorDetail(tempInstructorDetail);
            theInstructor.setInstructorDetail(theInstructorDetail);
            //start transaction
            session.beginTransaction();

            //save the instructor
            //also save details object because of cascade all
            //System.out.println("Saving Instructor "+ tempInstructor);
            System.out.println("Saving Instructor "+ theInstructor);

           // session.save(tempInstructor);
            session.save(theInstructor);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }




    }
}
