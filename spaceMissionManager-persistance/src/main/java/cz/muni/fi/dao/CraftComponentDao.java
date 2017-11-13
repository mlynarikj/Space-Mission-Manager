package cz.muni.fi.dao;

import cz.muni.fi.entity.CraftComponent;

import java.util.List;

public interface CraftComponentDao {

	/**
	 * 
	 * @param craftComponent componnent to add
	 */
	void addComponent(CraftComponent craftComponent);

	List<CraftComponent> findAllComponents();

	/**
	 * 
	 * @param id id of craft component
	 */
	CraftComponent findComponentById(Long id);

	/**
	 * 
	 * @param craftComponent component to update
	 */
	void updateComponent(CraftComponent craftComponent);

	/**
	 * 
	 * @param craftComponent component to remove
	 */
	void removeComponent(CraftComponent craftComponent);

}