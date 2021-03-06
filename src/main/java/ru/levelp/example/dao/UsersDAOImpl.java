package ru.levelp.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelp.example.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

@Service
public class UsersDAOImpl implements UsersDAO {
    private EntityManager em;

    @Autowired
    public UsersDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User findByLogin(String login) {
        return em.find(User.class, login);
    }

    @Override
    public void add(User user) {
        em.getTransaction().begin();
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
