package cz.muni.fi.services.impl;

import cz.muni.fi.dao.CraftComponentDao;
import cz.muni.fi.entity.CraftComponent;
import cz.muni.fi.services.CraftComponentService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the {@link CraftComponentService}. This class is part of the
 * service module of the application that provides the implementation of the
 * business logic (main logic of the application).
 *
 * @author Vojtech Bruza
 */
@Service
public class CraftComponentServiceImpl implements CraftComponentService {
    @Inject
    private CraftComponentDao craftComponentDao;

    @Override
    public void addComponent(CraftComponent craftComponent) throws DataAccessException {
        craftComponentDao.addComponent(craftComponent);
    }

    @Override
    public List<CraftComponent> findAllComponents() throws DataAccessException {
        return Collections.unmodifiableList(craftComponentDao.findAllComponents());
    }

    @Override
    public CraftComponent findComponentById(Long id) throws DataAccessException {
        return craftComponentDao.findComponentById(id);
    }

    @Override
    public void updateComponent(CraftComponent craftComponent) throws DataAccessException {
        craftComponentDao.updateComponent(craftComponent);
    }

    @Override
    public void removeComponent(CraftComponent craftComponent) throws DataAccessException {
        craftComponentDao.removeComponent(craftComponent);
    }
}
