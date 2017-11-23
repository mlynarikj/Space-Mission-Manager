package cz.muni.fi.services;

import cz.muni.fi.entity.CraftComponent;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CraftComponentService {
	/**
	 *
	 * @param craftComponent Component to add
	 */
	void addComponent(CraftComponent craftComponent) throws DataAccessException;

	List<CraftComponent> findAllComponents() throws DataAccessException;

	/**
	 *
	 * @param id id of component to find
	 */
	CraftComponent findComponentById(Long id) throws DataAccessException;

	/**
	 *
	 * @param craftComponent craft component to update
	 */
	void updateComponent(CraftComponent craftComponent) throws DataAccessException;

	/**
	 *
	 * @param craftComponent component to remove
	 */
	void removeComponent(CraftComponent craftComponent) throws DataAccessException;

}
