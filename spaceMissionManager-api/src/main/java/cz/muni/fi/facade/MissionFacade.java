package cz.muni.fi.facade;

import cz.muni.fi.dto.MissionDTO;

import java.util.List;

public interface MissionFacade {

	/**
	 *
	 * @param mission mission to be saved in the database
	 */
	void createMission(MissionDTO mission);

	/**
	 *
	 * @param mission mission to be deleted from the database
	 */
	void cancelMission(MissionDTO mission);

	/**
	 *
	 * @return list of all missions in the database
	 */
	List<MissionDTO> findAllMissions();

	/**
	 *
	 * @param active state of the mission
	 * @return list of the missions
	 */
	List<MissionDTO> findAllMissions(boolean active);

	/**
	 *
	 * @param id id of desired mission
	 * @return mission with selected id or null
	 */
	MissionDTO findMissionById(Long id);

	/**
	 *
	 * @param mission mission to be updated
	 */
	void updateMission(MissionDTO mission);

}
