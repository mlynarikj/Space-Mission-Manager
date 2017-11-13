package cz.muni.fi.dao;

import cz.muni.fi.entity.Mission;

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
	List<Mission> findAllMissions(boolean active);

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