package cz.muni.fi.Dao;

import cz.muni.fi.Entity.Mission;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author mlynarikj
 */

@Repository
@Transactional
public class MissionDaoImpl implements MissionDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createMission(Mission mission) {
		if (mission == null || ZonedDateTime.now().isAfter(mission.getEta())){
			throw new IllegalArgumentException("mission is null");
		}
		entityManager.persist(mission);
	}

	@Override
	public void cancelMission(Mission mission) {
		if (mission == null){
			throw new IllegalArgumentException("mission is null");
		}
		entityManager.remove(entityManager.merge(mission));
	}

	@Override
	public List<Mission> findAllMissions() {
		return entityManager.createQuery("select m from Mission m", Mission.class).getResultList();
	}

	@Override
	public List<Mission> findAllMissions(boolean active) {
		return entityManager.createQuery("select m from Mission m where active = :active", Mission.class)
				.setParameter("active", active)
				.getResultList();
	}

	@Override
	public Mission findMissionById(Long id) {
		try {
			return entityManager.createQuery("select m from Mission m where id = :id", Mission.class)
					.setParameter("id", id)
					.getSingleResult();
		}catch (NoResultException nre){
			return null;
		}
	}

	@Override
	public void updateMission(Mission mission) {
		if (mission == null || ZonedDateTime.now().isAfter(mission.getEta())){
			throw new IllegalArgumentException("mission is null");
		}
		entityManager.merge(mission);
	}
}