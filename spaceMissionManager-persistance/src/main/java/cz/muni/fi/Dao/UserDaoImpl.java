package cz.muni.fi.Dao;

import cz.muni.fi.Entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author jsmadis
 */

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException(User.class.getName());
        }

        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException(User.class.getName());
        }
        em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        if (user == null){
            throw new IllegalArgumentException(User.class.getName());
        }
        em.remove(em.merge(user));
    }

    @Override
    public List<User> findAllUsers() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public List<User> findAllAstronauts() {
        return em.createQuery("select u from User u where u.isManager = false", User.class)
                .getResultList();
    }

    @Override
    public User findUserById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("User id is null");
        }
        try {
            return em.createQuery("select u from User u where u.id = :id", User.class)
                    .setParameter("id", id).getSingleResult();
        }
        catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public List<User> findAllAvailableAstronauts() {
        return em.createQuery("select u from User u where u.mission is null and u.isManager = false", User.class)
                .getResultList();
    }

}
