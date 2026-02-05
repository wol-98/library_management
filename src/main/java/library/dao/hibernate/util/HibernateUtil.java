package library.dao.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Properties;

public class HibernateUtil
{
    private static SessionFactory sessionFactory;
    private static Session session;

    static
    {
        // Create Hibernate configuration object
        Configuration configuration = new Configuration();

        // Load settings from hibernate.cfg.xml
        configuration.configure();

        /*
         * MySQL configuration properties.
         * These override the values in hibernate.cfg.xml.
         */
        Properties mysqlProperties = new Properties();

        // JDBC driver for MySQL 8+
        mysqlProperties.setProperty(
            "hibernate.connection.driver_class",
            "com.mysql.cj.jdbc.Driver"
        );

        // Database connection URL with encoding fix
        mysqlProperties.setProperty(
            "hibernate.connection.url",
            "jdbc:mysql://localhost/library_management?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8"
        );

        // Database username
        mysqlProperties.setProperty(
            "hibernate.connection.username",
            "library"
        );

        // Database password
        mysqlProperties.setProperty(
            "hibernate.connection.password",
            "library123"
        );

        // SQL dialect for MySQL
        mysqlProperties.setProperty(
            "hibernate.dialect",
            "org.hibernate.dialect.MySQLDialect"
        );

        // Connection pool size
        mysqlProperties.setProperty(
            "hibernate.connection.pool_size",
            "50"
        );

        // Apply MySQL properties
        configuration.addProperties(mysqlProperties);

        // Build the SessionFactory
        sessionFactory = configuration.buildSessionFactory();

        // Open a session
        session = sessionFactory.openSession();
    }

    // Returns the current session
    public static Session getCurrentSession()
    {
        return session;
    }
}
