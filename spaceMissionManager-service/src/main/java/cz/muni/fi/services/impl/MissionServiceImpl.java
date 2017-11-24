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

    @Override
    public void archive(Long missionId, LocalDate endDate){
        if(endDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Mission end date must be in the past");
        }
        Mission mission = missionDao.findMissionById(missionId);
        mission.setEndDate(endDate);
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
        if (mission == null){
            throw new IllegalArgumentException("Mission must not be null.");
        }
        try {
            missionDao.createMission(mission);
        } catch (Exception e) {
            throw new ServiceDataAccessException("Could not create mission.", e);
        }
    }

    @Override
    public void cancelMission(Mission mission) throws DataAccessException {
        if (mission == null){
            throw new IllegalArgumentException("Mission must not be null.");
        }
        try {
            missionDao.cancelMission(mission);
        } catch (Exception e) {
            throw new ServiceDataAccessException("Could not cancel mission.", e);
        }
    }

    @Override
    public List<Mission> findAllMissions() throws DataAccessException {
        try {
            return Collections.unmodifiableList(missionDao.findAllMissions());
        } catch (Exception e) {
            throw new ServiceDataAccessException("Could not find missions.", e);
        }
    }

    @Override
    public List<Mission> findAllMissions(boolean active) throws DataAccessException {
        try {
            return Collections.unmodifiableList(missionDao.findAllMissions(active));
        } catch (Exception e) {
            throw new ServiceDataAccessException("Could not find missions.", e);
        }
    }

    @Override
    public Mission findMissionById(Long id) throws DataAccessException {
        if (id == null){
            throw new IllegalArgumentException("Id must not be null.");
        }
        try {
            return missionDao.findMissionById(id);
        } catch (Exception e) {
            throw new ServiceDataAccessException("Could not find mission.", e);
        }
    }

    @Override
    public void updateMission(Mission mission) throws DataAccessException {
        if (mission == null){
            throw new IllegalArgumentException("Mission must not be null.");
        }
        if(mission.isActive())
            try {
                missionDao.updateMission(mission);
            } catch (Exception e) {
                throw new ServiceDataAccessException("Could not update mission.", e);
            }
        else throw new IllegalArgumentException("Cannot modify archived mission");
    }
}
