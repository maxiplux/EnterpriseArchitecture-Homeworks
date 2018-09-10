package cs544;

import cs544.doctors.EyeDoctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.List;

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


            EyeDoctor eyeDoctor = new EyeDoctor();
            eyeDoctor.setFirstname("Edwin");
            eyeDoctor.setLastname("Cobos");

            Patient patient = new Patient();
            patient.setName("Alejandro Fonseca");
            patient.setCity("New York");
            patient.setStreet("Broadway");
            patient.setZip("1574");

            Payment payment = new Payment();
            payment.setAmount(950);
            payment.setPaydate("12-05-2018");

            Appointment appointment = new Appointment();
            appointment.setAppdate("12-06-2018");
            appointment.setDoctor(eyeDoctor);
            appointment.setPatient(patient);
            appointment.setPayment(payment);


            session.persist(appointment);
            session.getTransaction().commit();
        }


        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            List<Appointment> carList = session.createQuery("from Appointment").list();
            for (Appointment appointment : carList) {
                System.out.println(appointment.toString());
                System.out.println("----");
            }

            session.getTransaction().commit();
        }

        tearDown();
    }
}
