package cz.muni.fi.services;

import cz.muni.fi.entity.CraftComponent;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CraftComponentService {
	/**
	 *
	 * @param craftComponent
	 */
	void addComponent(CraftComponent craftComponent) throws DataAccessException;

	List<CraftComponent> findAllComponents() throws DataAccessException;

	/**
	 *
	 * @param id
	 */
	CraftComponent findComponentById(Long id) throws DataAccessException;

	/**
	 *
	 * @param craftComponent
	 */
	void updateComponent(CraftComponent craftComponent) throws DataAccessException;

	/**
	 *
	 * @param craftComponent
	 */
	void removeComponent(CraftComponent craftComponent) throws DataAccessException;

}
