package cz.muni.fi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.dto.MissionDTO;
import cz.muni.fi.entity.Mission;
import cz.muni.fi.facade.MissionFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;
import java.util.MissingResourceException;

@RestController
@RequestMapping("/missions")
public class MissionController {

    final static Logger logger = LoggerFactory.getLogger(MissionController.class);

    @Inject
    private MissionFacade missionFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<MissionDTO> getMissions(@RequestParam(value="active", required=false) Boolean active) throws JsonProcessingException {
        logger.debug("rest getMissions()");
        if(active == null){ //TODO check if correct
            return missionFacade.findAllMissions();
        }
        return missionFacade.findAllMissions(active);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MissionDTO getMission(@PathVariable("id") long id) throws Exception {
        logger.debug("rest getMission({})", id);
        MissionDTO mission = missionFacade.findMissionById(id);
        if (mission == null){
            throw new MissingResourceException("Resource not found", Mission.class.toString(), Long.toString(id));
        }
        return mission;
    }
}
