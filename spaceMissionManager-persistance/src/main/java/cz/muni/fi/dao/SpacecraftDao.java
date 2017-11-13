package cz.muni.fi.dao;

import cz.muni.fi.entity.Spacecraft;

import java.util.List;

public interface SpacecraftDao {

	/**
	 * Create new entity in the database
	 * @param spacecraft entity to be persisted
	 */
	void addSpacecraft(Spacecraft spacecraft);

	/**
	 * Remove entity from the database
	 * @param spacecraft entity to be deleted
	 */
	void removeSpacecraft(Spacecraft spacecraft);

	/**
	 * Get list of all spacecrafts in the database
	 * @return list of all spacecrafts in the database
	 */
	List<Spacecraft> findAllSpacecrafts();

	/**
	 * Find spacecraft by its id
	 * @param id to search for in the database
	 * @return reuquested spacecraft
	 */
	Spacecraft findSpacecraftById(Long id);

	/**
	 * Update this spacecraft in the database
	 * @param spacecraft entity to be updated
	 */
	void updateSpacecraft(Spacecraft spacecraft);

}