package cz.muni.fi.services.impl;

import cz.muni.fi.dao.SpacecraftDao;
import cz.muni.fi.entity.Spacecraft;
import cz.muni.fi.services.SpacecraftService;
import org.springframework.dao.DataAccessException;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the {@link SpacecraftService}. This class is part of the
 * service module of the application that provides the implementation of the
 * business logic (main logic of the application).
 *
 * @author Vojtech Bruza
 */
public class SpacecraftServiceImpl implements SpacecraftService {
    @Inject
    SpacecraftDao spacecraftDao;

    @Override
    public void addSpacecraft(Spacecraft spacecraft) throws DataAccessException {
        spacecraftDao.addSpacecraft(spacecraft);
    }

    @Override
    public void removeSpacecraft(Spacecraft spacecraft) throws DataAccessException {
        spacecraftDao.removeSpacecraft(spacecraft);
    }

    @Override
    public List<Spacecraft> findAllSpacecrafts() throws DataAccessException {
        return Collections.unmodifiableList(spacecraftDao.findAllSpacecrafts());
    }

    @Override
    public Spacecraft findSpacecraftById(Long id) throws DataAccessException {
        return spacecraftDao.findSpacecraftById(id);
    }

    @Override
    public void updateSpacecraft(Spacecraft spacecraft) throws DataAccessException {
        spacecraftDao.updateSpacecraft(spacecraft);
    }
}
