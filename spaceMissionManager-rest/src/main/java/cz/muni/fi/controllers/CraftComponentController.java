package cz.muni.fi.controllers;


import cz.muni.fi.ApiUris;
import cz.muni.fi.dto.CraftComponentCreateDTO;
import cz.muni.fi.dto.CraftComponentDTO;
import cz.muni.fi.exceptions.ResourceAlreadyExistsException;
import cz.muni.fi.facade.CraftComponentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(ApiUris.ROOT_URI_CRAFTCOMPONENTS)
public class CraftComponentController {

	private final static Logger logger = Logger.getLogger(CraftComponentController.class.getName());


	private CraftComponentFacade craftComponentFacade;

	@Autowired
	public CraftComponentController(CraftComponentFacade craftComponentFacade) {
		this.craftComponentFacade = craftComponentFacade;
	}


	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public final CraftComponentDTO createCraftComponent(@RequestBody CraftComponentCreateDTO craftComponentCreateDTO) throws Exception{
		logger.log(Level.INFO, "[REST] Creating CraftComponent...");
		try {
			return craftComponentFacade.findComponentById(craftComponentFacade.addComponent(craftComponentCreateDTO));
		}catch (Exception e){
			logger.log(Level.WARNING, e.getMessage());
			throw new ResourceAlreadyExistsException();
		}
	}

	@RolesAllowed("ROLE_MANAGER")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CraftComponentDTO> findAllComponents(){
		logger.log(Level.INFO, "[REST] finding all components...");
		return craftComponentFacade.findAllComponents();
	}

}
