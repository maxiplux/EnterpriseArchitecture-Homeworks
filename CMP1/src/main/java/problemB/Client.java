package problemB;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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

            Flight flight1 = new Flight();
            flight1.setDate(new Date());
            flight1.setFlightnumber("1");
            flight1.setOrigin("Iowa");
            flight1.setDestiny("New York");

            Flight flight2 = new Flight();
            flight2.setDate(new Date());
            flight2.setFlightnumber("2");
            flight2.setOrigin("Colombia");
            flight2.setDestiny("Ethiopia");

            Passenger passenger = new Passenger();
            passenger.setFirstname("Theodros");
            passenger.addFlight(flight1);
            passenger.addFlight(flight2);

            // save the car
            session.persist(passenger);

            session.getTransaction().commit();
        }

        tearDown();
    }
}
