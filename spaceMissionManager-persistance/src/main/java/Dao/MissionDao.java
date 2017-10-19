package Dao;

import Entity.Mission;
import Entity.Spacecraft;
import Entity.User;

import java.time.LocalDate;

public interface MissionDao {

	/**
	 * 
	 * @param mission
	 */
	void createMission(Mission mission);

	/**
	 * 
	 * @param mission
	 */
	void cancelMission(Mission mission);

	/**
	 * 
	 * @param mission
	 * @param spacecraft
	 */
	void addSpacecraftToMission(Mission mission, Spacecraft spacecraft);

	/**
	 * 
	 * @param mission
	 * @param user
	 */
	void addAstronautToMission(Mission mission, User user);

	/**
	 * 
	 * @param mission
	 * @param result
	 */
	void archiveMission(Mission mission, String result);

	/**
	 * 
	 * @param mission
	 * @param result
	 * @param endDate
	 */
	void archiveMission(Mission mission, String result, LocalDate endDate);

	void findAllMissions();

	/**
	 * 
	 * @param id
	 */
	void findMissionById(Long id);

	/**
	 * 
	 * @param mission
	 */
	void updateMission(Mission mission);

	/**
	 * 
	 * @param mission
	 * @param user
	 */
	void removeAstronautFromMission(Mission mission, User user);

}