package cz.muni.fi.Dao;

import cz.muni.fi.ApplicationContext;
import cz.muni.fi.Entity.CraftComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Vojtech Bruza
 */

@ContextConfiguration(classes = ApplicationContext.class)
public class CraftComponentDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    CraftComponentDao craftComponentDao;

    CraftComponent orbitalModule;
    CraftComponent heatShield;
    CraftComponent retroRocket;

    @BeforeMethod
	public void setUp() {
        orbitalModule = new CraftComponent();
        heatShield = new CraftComponent();
        retroRocket = new CraftComponent();
    }

    @AfterTest //Aftermethod od Aftertest
	public void tearDown() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); //TODO entity manager factory
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from User ").executeUpdate();
        entityManager.createQuery("delete from Mission ").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

//    ADD
    @Test
    public void testAddComponent(){
        craftComponentDao.addComponent(orbitalModule);
        assertThat(craftComponentDao.findComponentById(orbitalModule.getId())).isEqualTo(orbitalModule);
        assertThat(craftComponentDao.findAllComponents()).hasSize(1); //TODO rewrite (findAllComponents) - so the test is not dependent on other methods
        craftComponentDao.addComponent(heatShield);
        assertThat(craftComponentDao.findComponentById(heatShield.getId())).isEqualTo(heatShield);
        assertThat(craftComponentDao.findAllComponents()).hasSize(2);
    }

//    FIND ALL
    public void testFindAllComponents(){

    }

//    FING BY ID
    public void testFindComponentById(){

    }

//    UPDATE
    public void testUpdateComponent(){

    }

//    REMOVE
    public void testRemoveComponent(){

    }

}
