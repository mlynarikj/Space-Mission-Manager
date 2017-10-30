package cz.muni.fi.Dao;

import cz.muni.fi.ApplicationContext;
import cz.muni.fi.Entity.CraftComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Vojtech Bruza
 */

@ContextConfiguration(classes = ApplicationContext.class)
public class CraftComponentDaoImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    CraftComponentDao craftComponentDao;

    CraftComponent orbitalModule;
    CraftComponent heatShield;
    CraftComponent retroRocket;

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    @BeforeMethod
	public void setUp() {
        orbitalModule = new CraftComponent();
        heatShield = new CraftComponent();
        retroRocket = new CraftComponent();
    }

    @AfterMethod
	public void tearDown() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from User ").executeUpdate();
        entityManager.createQuery("delete from Mission ").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

//    ADD
    @Test
    public void testAddComponent(){
        EntityManager em = entityManagerFactory.createEntityManager();
        craftComponentDao.addComponent(orbitalModule);
        assertThat(em.find(CraftComponent.class, orbitalModule.getID())).isEqualTo(orbitalModule);
        assertThat(em.createQuery("select c from CraftComponent c", CraftComponent.class).getResultList()).hasSize(1);
        craftComponentDao.addComponent(heatShield);
        assertThat(em.find(CraftComponent.class, heatShield.getID())).isEqualTo(heatShield);
        assertThat(em.createQuery("select c from CraftComponent c", CraftComponent.class).getResultList()).hasSize(2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddComponentWithId(){
        orbitalModule.setId(1L);
        craftComponentDao.addComponent(orbitalModule);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddComponentWithXXXX(){
        //TODO next tests
    }

//    FIND ALL
    public void testFindAllComponents(){
        EntityManager em = entityManagerFactory.createEntityManager();
        assertThat(craftComponentDao.findAllComponents()).hasSize(0);
        em.persist(orbitalModule);
        em.persist(heatShield);
        assertThat(craftComponentDao.findAllComponents()).hasSize(2);
        em.persist(retroRocket);
        assertThat(craftComponentDao.findAllComponents()).hasSize(3);
    }

//    FIND BY ID
    public void testFindComponentById(){

    }

//    UPDATE
    public void testUpdateComponent(){

    }

//    REMOVE
    public void testRemoveComponent(){

    }

}
