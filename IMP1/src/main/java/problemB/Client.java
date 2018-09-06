package problemB;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import problemB.products.Book;
import problemB.products.CD;
import problemB.products.DVD;
import problemB.products.Product;

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

            Book product1 = new Book();
            product1.setName("Book 1");
            product1.setDescription("This is the Book 1");
            product1.setTitle("Book Title 1");

            CD product2 = new CD();
            product2.setName("CD 1");
            product2.setDescription("This is the CD 1");
            product2.setArtist("Edwin Artist");

            DVD product3 = new DVD();
            product3.setName("DVD 3");
            product3.setDescription("This is the DVD 1");
            product3.setGenre("Action genre");



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
            customer.setFirstname("Alejandro");
            customer.setLastname("Fonseca");
            customer.addOrder(order);


            session.persist(customer);
            session.getTransaction().commit();
        }

        tearDown();
    }
}
