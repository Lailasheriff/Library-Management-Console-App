package util;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration();

                //load DB properties from system properties
                String dbHost =  System.getenv("DB_HOST");
                String dbPort = System.getenv("DB_PORT");
                String dbName =  System.getenv("DB_NAME");
                String dbUser =  System.getenv("DB_USER");
                String dbPassword = System.getenv("DB_PASSWORD");

                String jdbcUrl = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC", dbHost, dbPort, dbName);

                config.setProperty("hibernate.connection.url", jdbcUrl);
                config.setProperty("hibernate.connection.username", dbUser);
                config.setProperty("hibernate.connection.password", dbPassword);
                config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                config.setProperty("hibernate.hbm2ddl.auto", "update");
                config.setProperty("show_sql", "true");

                config.addAnnotatedClass(Book.class);
                config.addAnnotatedClass(User.class);
                config.addAnnotatedClass(Admin.class);
                config.addAnnotatedClass(RegularUser.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(config.getProperties()).build();

                sessionFactory = config.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to create SessionFactory");
            }
        }
        return sessionFactory;
    }
}