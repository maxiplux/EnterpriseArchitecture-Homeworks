package cs544.hpa1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AppCar {

    private static EntityManagerFactory entityManagerFactory;

    /* Reads hibernate.cfg.xml and prepares Hibernate for use     */
    protected static void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("cs544_HPA2");
    }

    protected static void tearDown() throws Exception {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    public static void main(String[] args) throws Exception {
        setUp();

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        // Create new instance of Car and set values in it
        Car car1 = new Car("BMW", "SDA231", 30221.00);
        // save the car
        em.persist(car1);
        // Create new instance of Car and set values in it
        Car car2 = new Car("Mercedes", "HOO100", 4088.00);
        // save the car
        em.persist(car2);

        em.getTransaction().commit();
        em.close();







        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        // retieve all cars
        @SuppressWarnings("unchecked")
        List<Car> carList = em.createQuery("from Car").getResultList();
        for (Car car : carList) {
            System.out.println("brand= " + car.getBrand() + ", year= "
                    + car.getYear() + ", price= " + car.getPrice());
        }
        em.getTransaction().commit();
        em.close();

        // Close the SessionFactory (best practice)
        tearDown();
    }
}

