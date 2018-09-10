package problemA;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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

            Laptop laptop1 = new Laptop();
            laptop1.setBrand("Brand1");
            laptop1.setType("Type1");

            Laptop laptop2 = new Laptop();
            laptop2.setBrand("Brand2");
            laptop2.setType("Type2");

            Employee employee1 = new Employee();
            employee1.setFirstname("Pacho1");
            employee1.setLastname("Mosquera");

            employee1.addLaptop(laptop1);
            employee1.addLaptop(laptop2);

            // save the car
            session.persist(employee1);

            session.getTransaction().commit();
        }

        tearDown();
    }
}
