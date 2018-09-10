package study.p3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;

public class Client {

    private static SessionFactory sessionFactory;

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

            MechanicId mechanicId1 = new MechanicId();
            mechanicId1.setName("name-1");
            mechanicId1.setHireDate(new Date());

            Mechanic mechanic1 = new Mechanic();
            mechanic1.setId(mechanicId1);
            mechanic1.setPhonenr("1111");
            mechanic1.setSalary(9999);


            session.persist(mechanic1);
            session.getTransaction().commit();
        }

        tearDown();

    }
}
