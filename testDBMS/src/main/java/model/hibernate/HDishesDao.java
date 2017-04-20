package model.hibernate;

import model.Dishes;
import model.DishesDao;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lyoumi on 13.02.2017.
 */
public class HDishesDao implements DishesDao {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(Dishes dish) {
        sessionFactory.getCurrentSession().save(dish);
    }

    @Override
    @Transactional
    public Dishes getDishById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Dishes d where d.id = :id");
        query.setParameter("id", id);
        return (Dishes) query.uniqueResult();
    }

    @Override
    @Transactional
    public List<Dishes> finalAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select d from Dishes d").list();
    }

    @Transactional
    public void remove(Dishes dish) {
        sessionFactory.getCurrentSession().remove(dish);
    }

    @Override
    @Transactional
    public void update(Dishes dish) {
        sessionFactory.getCurrentSession().update(dish);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
