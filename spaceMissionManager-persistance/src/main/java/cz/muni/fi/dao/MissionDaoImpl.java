package cz.muni.fi.dao;

import cz.muni.fi.entity.Mission;
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
		if (mission == null) {
			throw new IllegalArgumentException("mission is null");
		}
		if (mission.getId() != null){
			throw new IllegalArgumentException("id is not null");
		}
		if (mission.getEta() != null && ZonedDateTime.now().isAfter(mission.getEta())){
			throw new IllegalArgumentException("eta is in past");
		}
		if (mission.getName() == null){
			throw new IllegalArgumentException("name is null");
		}
		if (mission.getSpacecrafts() == null){
			throw new IllegalArgumentException("spacecrafts is null");
		}
		if (mission.getAstronauts() == null) {
			throw new IllegalArgumentException("astronauts is null");
		}
		if ( mission.getSpacecrafts().size() < 1){
			throw new IllegalArgumentException("at least 1 spacecraft is required");
		}
		entityManager.persist(mission);
	}

	@Override
	public void cancelMission(Mission mission) {
		if (mission == null) {
			throw new IllegalArgumentException("mission is null");
		}
		if (mission.getId() == null){
			throw new IllegalArgumentException("id is null");
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
		if (id == null){
			throw new IllegalArgumentException("id is null");
		}
		try {
			return entityManager.createQuery("select m from Mission m fetch all properties where id = :id", Mission.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public void updateMission(Mission mission) {
		if (mission == null) {
			throw new IllegalArgumentException("mission is null");
		}
		if (mission.getId() == null){
			throw new IllegalArgumentException("id is null");
		}
		if (mission.getEta() != null && ZonedDateTime.now().isAfter(mission.getEta())){
			throw new IllegalArgumentException("eta is in past");
		}
		if (mission.getName() == null){
			throw new IllegalArgumentException("name is null");
		}
		if (mission.getSpacecrafts() == null){
			throw new IllegalArgumentException("spacecrafts is null");
		}
		if (mission.getAstronauts() == null) {
			throw new IllegalArgumentException("astronauts is null");
		}
		if (mission.getSpacecrafts().size() < 1){
			throw new IllegalArgumentException("at least 1 spacecraft is required");
		}
		entityManager.merge(mission);
		entityManager.flush();
	}
}