package cz.muni.fi.Dao;

import cz.muni.fi.ApplicationContext;
import cz.muni.fi.Entity.CraftComponent;
import cz.muni.fi.Entity.Mission;
import cz.muni.fi.Entity.Spacecraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;

import java.time.*;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Vojtech Bruza
 */

@ContextConfiguration(classes = ApplicationContext.class)
public class CraftComponentDaoImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    CraftComponentDao craftComponentDao;

    @Autowired
    SpacecraftDao spacecraftDao;

    @Autowired
    MissionDao missionDao;

    CraftComponent orbitalModule;
    CraftComponent heatShield;
    CraftComponent retroRocket;

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    @BeforeMethod
	public void setUp() {
        LocalDateTime ldt = LocalDateTime.of(2018, Month.AUGUST, 22, 14, 30);
        Mission mission = new Mission();
        mission.setName("Mission");
        mission.setActive(false);
        mission.setDestination("Earth");
        mission.setEta(ldt.atZone(ZoneId.of("GMT+2")).minusMonths(1));

        Spacecraft spacecraft = new Spacecraft();
        spacecraft.setName("S");
        spacecraft.setType("Test");
        spacecraft.setMission(mission);

        missionDao.createMission(mission);

        orbitalModule = new CraftComponent();
        orbitalModule.setName("Orbital Module");
        orbitalModule.setReadyDate(ldt.atZone(ZoneId.of("GMT+2")));
        orbitalModule.setReadyToUse(true);
        orbitalModule.setSpacecraft(spacecraft);

        spacecraftDao.addSpacecraft(spacecraft);

        heatShield = new CraftComponent();
        heatShield.setName("Heat Shield");
        heatShield.setReadyDate(ldt.atZone(ZoneId.of("UTC-06:00")));
        heatShield.setReadyToUse(false);
        heatShield.setSpacecraft(spacecraft);

        retroRocket = new CraftComponent();
        retroRocket.setName("Retro Rocket");
        retroRocket.setReadyDate(ldt.atZone(ZoneId.of("UTC+08:00")));
        retroRocket.setReadyToUse(true);
        retroRocket.setSpacecraft(spacecraft);
    }

    @AfterMethod
	public void tearDown() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Spacecraft ").executeUpdate();
        entityManager.createQuery("delete from CraftComponent ").executeUpdate();
        entityManager.createQuery("delete from Mission ").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

//    ADD
    @Test
    public void testAddComponent(){
        EntityManager em = entityManagerFactory.createEntityManager();
        craftComponentDao.addComponent(orbitalModule);
        assertThat(em.find(CraftComponent.class, orbitalModule.getId())).isEqualTo(orbitalModule);
        assertThat(em.createQuery("select c from CraftComponent c", CraftComponent.class).getResultList()).hasSize(1);
        craftComponentDao.addComponent(heatShield);
        assertThat(em.find(CraftComponent.class, heatShield.getId())).isEqualTo(heatShield);
        assertThat(em.createQuery("select c from CraftComponent c", CraftComponent.class).getResultList()).hasSize(2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddComponentWithId(){
        orbitalModule.setId(1L);
        craftComponentDao.addComponent(orbitalModule);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddComponentWithNullName() throws Exception {
        orbitalModule.setName(null);
        craftComponentDao.addComponent(orbitalModule);
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
    @Test
	public void testFindComponentById() throws Exception {
        craftComponentDao.addComponent(heatShield);
        assertThat(craftComponentDao.findComponentById(heatShield.getId())).isEqualToComparingFieldByField(heatShield);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
	public void testFindComponentByIdWithNullId() throws Exception {
        craftComponentDao.addComponent(retroRocket);
        craftComponentDao.findComponentById(null);
    }

    @Test
	public void testFindNonexistingComponent() throws Exception {
        craftComponentDao.addComponent(retroRocket);
        assertThat(craftComponentDao.findComponentById(retroRocket.getId()+1L)).isNull();
    }

//    UPDATE
    @Test
    public void testUpdateComponent(){
        craftComponentDao.addComponent(orbitalModule);
        orbitalModule.setName("Large orbital module");
        craftComponentDao.updateComponent(orbitalModule);
        assertThat(craftComponentDao.findComponentById(orbitalModule.getId())).isEqualToComparingFieldByField(orbitalModule);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateComponentWithNullName() throws Exception {
        craftComponentDao.addComponent(orbitalModule);
        orbitalModule.setName(null);
        craftComponentDao.updateComponent(orbitalModule);
    }


    //    REMOVE
    @Test
    public void testRemoveComponent(){
        craftComponentDao.addComponent(retroRocket);
        assertThat(craftComponentDao.findAllComponents()).contains(retroRocket);
        craftComponentDao.removeComponent(retroRocket);
        assertThat(craftComponentDao.findAllComponents()).isEmpty();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveComponentWithNullId(){
        craftComponentDao.addComponent(retroRocket);
        assertThat(craftComponentDao.findAllComponents()).contains(retroRocket);
        retroRocket.setId(null);
        craftComponentDao.removeComponent(retroRocket);
    }

}
