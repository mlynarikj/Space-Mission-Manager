package Dao;

import Entity.Spacecraft;

public interface SpacecraftDao {

	/**
	 * 
	 * @param spacecraft
	 */
	void addSpacecraft(Spacecraft spacecraft);

	/**
	 * 
	 * @param spacecraft
	 */
	void removeSpacecraft(Spacecraft spacecraft);

	void findAllSpacecrafts();

	/**
	 * 
	 * @param type
	 */
	void findAllSpacecrafts(String type);

	/**
	 * 
	 * @param id
	 */
	void findSpacecraftById(Long id);

	/**
	 * 
	 * @param spacecraft
	 */
	void updateSpacecraft(Spacecraft spacecraft);

}