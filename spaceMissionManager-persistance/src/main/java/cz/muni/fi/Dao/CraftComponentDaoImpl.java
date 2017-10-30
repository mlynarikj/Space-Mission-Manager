package cz.muni.fi.Dao;

import cz.muni.fi.Entity.CraftComponent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CraftComponentDaoImpl implements CraftComponentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addComponent(CraftComponent craftComponent) {
        if(craftComponent == null){
            throw new IllegalArgumentException("craftComponent is null");
        }
        if (craftComponent.getName() == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (craftComponent.getId() != null) {
            throw new IllegalArgumentException("id is not null");
        }
        this.entityManager.persist(craftComponent);
    }

    @Override
    public List<CraftComponent> findAllComponents() {
        return entityManager.createQuery("select cc from CraftComponent cc", CraftComponent.class).getResultList();
    }

    @Override
    public CraftComponent findComponentById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        try {
            return entityManager.createQuery("select cc from CraftComponent cc where id = :id", CraftComponent.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public void updateComponent(CraftComponent craftComponent) {
        if(craftComponent == null){
            throw new IllegalArgumentException("craftComponent is null");
        }
        if (craftComponent.getName() == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (craftComponent.getId() == null) {
            throw new IllegalArgumentException("id is null");
        }
        this.entityManager.merge(craftComponent);
    }

    @Override
    public void removeComponent(CraftComponent craftComponent) {
        if(craftComponent == null){
            throw new IllegalArgumentException("craftComponent is null");
        }
        if (craftComponent.getId() == null) {
            throw new IllegalArgumentException("id is null");
        }
        entityManager.remove(this.entityManager.merge(craftComponent));
    }
}
