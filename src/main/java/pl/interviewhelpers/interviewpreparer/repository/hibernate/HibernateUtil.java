package pl.interviewhelpers.interviewpreparer.repository.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private final static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            File configurationFile = new File("src/main/resources/hibernate.cfg.xml");
            return new Configuration()
                    .configure(configurationFile)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial session factory has failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
