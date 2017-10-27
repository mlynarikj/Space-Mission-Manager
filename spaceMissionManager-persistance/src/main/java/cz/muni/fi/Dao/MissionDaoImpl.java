package Dao;

import Entity.Mission;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author mlynarikj
 */

@Repository
public class MissionDaoImpl implements MissionDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createMission(Mission mission) {
		entityManager.persist(mission);
	}

	@Override
	public void cancelMission(Mission mission) {
		entityManager.remove(entityManager.merge(mission));
	}

	@Override
	public List<Mission> findAllMissions() {
		return entityManager.createQuery("select m from Mission m", Mission.class).getResultList();
	}

	@Override
	public List<Mission> findAllMissions(Boolean active) {
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
		entityManager.merge(mission);
	}
}
