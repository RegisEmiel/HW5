import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.models.Student;

public class SessionFactoryCreator {
    private static SessionFactory sessionFactory;

    private static void buildSessionFactory() {
        try {
            // Конфигурационные настройки и мэппинг сущностей в файле hibernate.cfg.xml
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) buildSessionFactory();
        return sessionFactory;
    }
}
