package cz.muni.fi.controllers;


import cz.muni.fi.ApiUris;
import cz.muni.fi.dto.SpacecraftCreateDTO;
import cz.muni.fi.dto.SpacecraftDTO;
import cz.muni.fi.exceptions.ResourceAlreadyExistsException;
import cz.muni.fi.exceptions.ResourceNotFoundException;
import cz.muni.fi.facade.SpacecraftFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;

/**
 * Created by jcizmar
 *
 * @author jcizmar
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_SPACECRAFTS)
public class SpacecraftController {

    private final static Logger logger = LoggerFactory.getLogger(SpacecraftController.class);


    @Autowired
    private SpacecraftFacade spacecraftFacade;

    /**
     * Returns all spacecrafts
     * @return SpacecraftDTO Collection of Spacecrafts
     */

    @RolesAllowed({"ROLE_MANAGER", "ROLE_USER"})
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<SpacecraftDTO> getUsers() {
        logger.debug("[REST] getSpacecraft()");
        return spacecraftFacade.findAllSpacecrafts();
    }

    /**
     * Returns spacecrat by id
     * @param id id of spacecraft
     * @return SpacecraftDTO
     */
    @RolesAllowed({"ROLE_MANAGER", "ROLE_USER"})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SpacecraftDTO getUser(@PathVariable("id") long id) {
        logger.debug("[REST] getSpacecraft() id=" + id);
        SpacecraftDTO spacecraftDTO = spacecraftFacade.findSpacecraftById(id);
        if (spacecraftDTO == null) {
            throw new ResourceNotFoundException();
        }
        return spacecraftDTO;
    }

    /**
     * Creates and returns spacecraft
     * @param spacecraft Spacecraft to create
     * @return SpacecraftDTO created spacecraft
     */

    @RolesAllowed("ROLE_MANAGER")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SpacecraftDTO crateSpacecraft(@RequestBody SpacecraftCreateDTO spacecraft) {

        logger.debug("[REST] createSpacecraft()");

        try {
            Long id = spacecraftFacade.addSpacecraft(spacecraft);
            return spacecraftFacade.findSpacecraftById(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new ResourceAlreadyExistsException();
        }
    }

    /**
     * Deletes spacecraft and returns all remaining spacecrafts
     * @param id id of spacecraft to delete
     * @return Collection<SpacecraftDTO> all remaining spacecrafts
     */

    @RolesAllowed("ROLE_MANAGER")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Collection<SpacecraftDTO> deleteSpacecraft(@PathVariable Long id) {
        logger.debug("[REST] deleteSpacecraft()");

        SpacecraftDTO spacecraft = spacecraftFacade.findSpacecraftById(id);
        if (spacecraft == null) {
            throw new ResourceNotFoundException();
        }
        spacecraftFacade.removeSpacecraft(spacecraft);
        return spacecraftFacade.findAllSpacecrafts();
    }

    /**
     * Updates and returns spacecraft
     * @param spacecraft spacecraft to update
     * @return SpacecraftDTO updated spacecraft
     */
    @RolesAllowed("ROLE_MANAGER")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SpacecraftDTO updateSpacecraft(@RequestBody SpacecraftDTO spacecraft) {
        logger.debug("[REST] updateSpacecraft()");

        try {
            spacecraftFacade.updateSpacecraft(spacecraft);
            return spacecraft;
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new ResourceAlreadyExistsException();
        }
    }
}
