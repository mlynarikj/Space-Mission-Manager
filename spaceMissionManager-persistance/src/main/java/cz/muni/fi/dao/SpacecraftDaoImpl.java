package cz.muni.fi.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import cz.muni.fi.entity.Spacecraft;

import java.util.List;

/**
 * @author Vojtech Bruza
 */
@Repository
@Transactional
public class SpacecraftDaoImpl implements SpacecraftDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Spacecraft addSpacecraft(Spacecraft spacecraft) {
        if (spacecraft == null){
            throw new IllegalArgumentException("Spacecraft should not be null");
        }
        if(spacecraft.getId() != null){
            throw new IllegalArgumentException("Spacecraft id should be null - generated by the database");
        }
        if (spacecraft.getComponents().isEmpty()){
            throw new IllegalArgumentException("Spacecraft should have at least one component");
        }
        if(spacecraft.getName() == null){
            throw new IllegalArgumentException("Spacecraft name must not be null");
        }
        em.persist(spacecraft);
        return spacecraft;
    }

    @Override
    public Spacecraft removeSpacecraft(Spacecraft spacecraft) {
        if (spacecraft == null){
            throw new IllegalArgumentException("Spacecraft should not be null");
        }
        if (spacecraft.getId() == null){
            throw new IllegalArgumentException("Spacecraft id must not be null");
        }
        em.remove(findSpacecraftById((spacecraft.getId())));
        return spacecraft;
    }

    @Override
    public List<Spacecraft> findAllSpacecrafts() {
        return em.createQuery("select s from Spacecraft s", Spacecraft.class).getResultList();
    }

    @Override
    public Spacecraft findSpacecraftById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Id must not be null");
        }
        try {
            return em.createQuery("select s from Spacecraft s fetch all properties where id = :id", Spacecraft.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (NoResultException nre){
            return null;
        }
    }

    @Override
    public Spacecraft updateSpacecraft(Spacecraft spacecraft) {
        if (spacecraft == null){
            throw new IllegalArgumentException("Spacecraft should not be null");
        }
        if (spacecraft.getId() == null){
            throw new IllegalArgumentException("Spacecraft id must not be null");
        }
        if (spacecraft.getComponents().isEmpty()){
            throw new IllegalArgumentException("Spacecraft should have at least one component");
        }
        if(spacecraft.getName() == null){
            throw new IllegalArgumentException("Spacecraft name must not be null");
        }
        em.merge(spacecraft);
        em.flush();
        return spacecraft;
    }

}
