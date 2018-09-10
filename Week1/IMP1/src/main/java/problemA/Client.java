package problemA;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;

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

            Product product1 = new Product();
            product1.setName("product 1");
            product1.setDescription("This is the p1");

            Product product2 = new Product();
            product2.setName("product 2");
            product2.setDescription("This is the p2");

            Product product3 = new Product();
            product3.setName("product 3");
            product3.setDescription("This is the p3");



            OrderLine orderLine1 = new OrderLine();
            orderLine1.setProduct(product1);
            orderLine1.setQuantity(5);

            OrderLine orderLine2 = new OrderLine();
            orderLine2.setProduct(product2);
            orderLine2.setQuantity(7);

            OrderLine orderLine3 = new OrderLine();
            orderLine3.setProduct(product3);
            orderLine3.setQuantity(9);



            Order order = new Order();
            order.setDate(LocalDate.now());
            order.addOrderLine(orderLine1);
            order.addOrderLine(orderLine2);
            order.addOrderLine(orderLine3);



            Customer customer = new Customer();
            customer.setFirstname("Edwin");
            customer.setLastname("Cobos");
            customer.addOrder(order);


            session.persist(customer);
            session.getTransaction().commit();
        }

        tearDown();
    }
}
