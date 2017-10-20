package Dao;

import Entity.CraftComponent;

import java.util.List;

public interface CraftComponentDao {

	/**
	 * 
	 * @param craftComponent
	 */
	void addComponent(CraftComponent craftComponent);

	List<CraftComponent> findAllComponents();

	/**
	 * 
	 * @param id
	 */
	CraftComponent findComponentById(Long id);

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