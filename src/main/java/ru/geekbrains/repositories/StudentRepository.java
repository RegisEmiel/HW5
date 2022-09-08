package ru.geekbrains.repositories;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.geekbrains.models.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentRepository {
    private SessionFactory sessionFactory;

    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Student entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void add1000(List<Student> students) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            for (Student student: students) {
                session.persist(student);
            }
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public Student findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();

            return student;
        }
    }

    public List<Student> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Student> studentsList = session.createQuery("from ru.geekbrains.models.Student").list();
            session.getTransaction().commit();

            return studentsList;
        }
    }

    public boolean deleteById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            session.createQuery("delete from Student where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            session.getTransaction().commit();

            return true;
        } catch (HibernateException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean delete(Student removingStudent) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            session.delete(removingStudent);

            session.getTransaction().commit();

            return true;
        } catch (HibernateException e) {
            e.printStackTrace();

            return false;
        }
    }

    public void update(Student student) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            session.update(student);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
