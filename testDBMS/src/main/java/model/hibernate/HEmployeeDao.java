package model.hibernate;

import model.Employee;
import model.EmployeeDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lyoumi on 24.12.2016.
 */
public class HEmployeeDao implements EmployeeDao{

    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    @Transactional
    public Employee getEmployeeById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Employee d where d.id = :id");
        query.setParameter("id", id);
        return (Employee) query.uniqueResult();
    }

    @Override
    @Transactional
    public List<Employee> finalAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Employee e").list();

    }

    @Transactional
    public void remove(Employee employee) {
        sessionFactory.getCurrentSession().remove(employee);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        sessionFactory.getCurrentSession().update(employee);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
