package Dao;

import Entity.ArchivedMission;
import Entity.Mission;

import java.util.List;

public interface ArchivedMissionDao {

	/**
	 * 
	 * @param archivedMission
	 */
	void addArchivedMission(ArchivedMission archivedMission);

	List<Mission> findAllArchivedMissions();

	/**
	 * 
	 * @param id
	 */
	Mission findArchivedMissionById(Long id);

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