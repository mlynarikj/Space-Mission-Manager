package cz.muni.fi.Dao;

import cz.muni.fi.Entity.Mission;
import cz.muni.fi.Entity.Spacecraft;
import cz.muni.fi.Entity.User;

import java.time.LocalDate;
import java.util.List;

/**
 * @author mlynarikj 
 */
public interface MissionDao {

	/**
	 * 
	 * @param mission mission to be saved in the database
	 */
	void createMission(Mission mission);

	/**
	 * 
	 * @param mission mission to be deleted from the database
	 */
	void cancelMission(Mission mission);

	/**
	 *
	 * @return list of all missions in the database
	 */
	List<Mission> findAllMissions();

	/**
	 *
	 * @param active state of the mission
	 * @return list of the missions
	 */
	List<Mission> findAllMissions(Boolean active);

	/**
	 *
	 * @param id id of desired mission
	 * @return mission with selected id or null
	 */
	Mission findMissionById(Long id);

	/**
	 * 
	 * @param mission mission to be updated
	 */
	void updateMission(Mission mission);

}