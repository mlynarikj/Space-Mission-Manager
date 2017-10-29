package cz.muni.fi.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import cz.muni.fi.Entity.Spacecraft;

import java.util.List;

/**
 * @author Vojtech Bruza
 */
@Repository
public class SpacecraftDaoImpl implements SpacecraftDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addSpacecraft(Spacecraft spacecraft) {
        if (spacecraft == null){
            throw new IllegalArgumentException("Spacecraft should not be null");
        }
        if(spacecraft.getId() != null){
            throw new IllegalArgumentException("Spacecraft id should be null - generated by the database");
        }
        if (spacecraft.getAllComponents().isEmpty()){
            throw new IllegalArgumentException("Spacecraft should have at least one component");
        }
        if(spacecraft.getName() == null){
            throw new IllegalArgumentException("Spacecraft name must not be null");
        }
        em.persist(spacecraft);
    }

    @Override
    public void removeSpacecraft(Spacecraft spacecraft) {
        if (spacecraft == null){
            throw new IllegalArgumentException("Spacecraft should not be null");
        }
        if (spacecraft.getId() == null){
            throw new IllegalArgumentException("Spacecraft id must not be null");
        }
        em.remove(findSpacecraftById((spacecraft.getId())));
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
        return em.find(Spacecraft.class, id);
    }

    @Override
    public void updateSpacecraft(Spacecraft spacecraft) {
        if (spacecraft == null){
            throw new IllegalArgumentException("Spacecraft should not be null");
        }
        if (spacecraft.getId() == null){
            throw new IllegalArgumentException("Spacecraft id must not be null");
        }
        if (spacecraft.getAllComponents().isEmpty()){
            throw new IllegalArgumentException("Spacecraft should have at least one component");
        }
        if(spacecraft.getName() == null){
            throw new IllegalArgumentException("Spacecraft name must not be null");
        }
        em.merge(spacecraft);
    }

}
