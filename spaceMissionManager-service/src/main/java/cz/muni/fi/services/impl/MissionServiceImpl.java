package cz.muni.fi.services.impl;

import cz.muni.fi.dao.MissionDao;
import cz.muni.fi.entity.Mission;
import cz.muni.fi.services.MissionService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the {@link MissionService}. This class is part of the
 * service module of the application that provides the implementation of the
 * business logic (main logic of the application).
 *
 * @author Vojtech Bruza
 */
@Service
public class MissionServiceImpl implements MissionService {
    @Inject
    MissionDao missionDao;
    //TODO javadoc
    //TODO exceptions?
    //TODO other functions that are doing some logic
    //TODO modify interfaces to offer business functions

//Archive mission - add endDate and move all the information to the result string resetting all the other attributes
    public void archive(Long missionId, LocalDate date){
        if(date.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Mission end date must be in the past");
        }
        Mission mission = missionDao.findMissionById(missionId);
        mission.setEndDate(date);
        mission.setActive(false);
        mission.setResult("Mission{" +
                        "astronauts=" + mission.getAstronauts() +
                        ", spacecrafts=" + mission.getSpacecrafts() +
                        ", name='" + mission.getName() + '\'' +
                        ", destination='" + mission.getDestination() + '\'' +
                        ", eta=" + mission.getEta() +
                        ", missionDescription='" + mission.getMissionDescription() + '\'' +
                        ", endDate=" + mission.getEndDate() +
                    '}');
        updateMission(mission);
    }

    @Override
    public void createMission(Mission mission) throws DataAccessException {
        missionDao.createMission(mission);
    }

    @Override
    public void cancelMission(Mission mission) throws DataAccessException {
        missionDao.cancelMission(mission);
    }

    @Override
    public List<Mission> findAllMissions() throws DataAccessException {
        return Collections.unmodifiableList(missionDao.findAllMissions());
    }

    @Override
    public List<Mission> findAllMissions(boolean active) throws DataAccessException {
        return Collections.unmodifiableList(missionDao.findAllMissions(active));
    }

    @Override
    public Mission findMissionById(Long id) throws DataAccessException {
        return missionDao.findMissionById(id);
    }

    @Override
    public void updateMission(Mission mission) throws DataAccessException {
        if(mission.isActive())
            missionDao.updateMission(mission);
        else throw new IllegalArgumentException("Cannot modify archived mission");
    }
}
