package Dao;

import Entity.CraftComponent;

public interface CraftComponentDao {

	/**
	 * 
	 * @param craftComponent
	 */
	void addComponent(CraftComponent craftComponent);

	void findAllComponents();

	/**
	 * 
	 * @param id
	 */
	void findComponentById(Long id);

	/**
	 * 
	 * @param craftComponent
	 */
	void updateComponent(CraftComponent craftComponent);

	/**
	 * 
	 * @param craftComponent
	 */
	void removeComponent(CraftComponent craftComponent);

}