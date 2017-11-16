package cz.muni.fi.services.impl;

import cz.muni.fi.dao.MissionDao;
import cz.muni.fi.entity.Mission;
import cz.muni.fi.services.MissionService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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
    //TODO javadoc, exceptions?, other functions that are doing some logic, unmodifiable lists?

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
        missionDao.updateMission(mission);
    }
}
