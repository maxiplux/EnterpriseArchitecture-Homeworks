package problemC;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import problemB.Flight;
import problemB.Passenger;

import java.util.Date;

public class Client {

    private static SessionFactory sessionFactory;

    /* Reads hibernate.cfg.xml and prepares Hibernate for use     */
    protected static void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    protected static void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }


    public static void main(String[] args) throws Exception {
        setUp();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Student student1 = new Student();
            student1.setFirstname("Edwin");
            student1.setLastname("Cobos");

            Student student2 = new Student();
            student2.setFirstname("Pachito");
            student2.setLastname("Mosquera");

            session.persist(student1);
            session.persist(student2);

            School school = new School();
            school.setName("School 1");

            school.addStudent(student1);
            school.addStudent(student2);

            // save the car
            session.persist(school);

            session.getTransaction().commit();
        }

        tearDown();
    }
}
