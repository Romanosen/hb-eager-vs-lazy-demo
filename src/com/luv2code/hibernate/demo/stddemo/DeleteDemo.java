package com.luv2code.hibernate.demo.stddemo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
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

            //get Instructor by primary key
            int theId=1;
            Instructor tempInstructor=session.get(Instructor.class,theId);

            System.out.println("Found Instructor : " +tempInstructor);

            //deleting the Instructor
            if(tempInstructor!=null){
                System.out.println("Deleting Instructor: "+ tempInstructor);

                //also delete InstructorDetails object because cascade type all
                session.delete(tempInstructor);
            }



            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }




    }
}
