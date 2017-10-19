package Dao;

import Entity.ArchivedMission;

public interface ArchivedMissionDao {

	/**
	 * 
	 * @param archivedMission
	 */
	void addArchivedMission(ArchivedMission archivedMission);

	void findAllArchivedMissions();

	/**
	 * 
	 * @param id
	 */
	void findArchivedMissionById(Long id);

	/**
	 * 
	 * @param archivedMission
	 */
	void updateArchivedMission(ArchivedMission archivedMission);

	/**
	 * 
	 * @param archivedMission
	 */
	void removeArchivedMission(ArchivedMission archivedMission);

}