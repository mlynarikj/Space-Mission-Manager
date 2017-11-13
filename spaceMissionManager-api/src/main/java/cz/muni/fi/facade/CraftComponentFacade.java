package cz.muni.fi.facade;

import cz.muni.fi.dto.CraftComponentDTO;

import java.util.List;

public interface CraftComponentFacade {
	/**
	 *
	 * @param craftComponent
	 */
	void addComponent(CraftComponentDTO craftComponent);

	List<CraftComponentDTO> findAllComponents();

	/**
	 *
	 * @param id
	 */
	CraftComponentDTO findComponentById(Long id);

	/**
	 *
	 * @param craftComponent
	 */
	void updateComponent(CraftComponentDTO craftComponent);

	/**
	 *
	 * @param craftComponent
	 */
	void removeComponent(CraftComponentDTO craftComponent);
}
