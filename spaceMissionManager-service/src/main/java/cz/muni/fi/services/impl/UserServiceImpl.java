package cz.muni.fi.services.impl;

import cz.muni.fi.dao.UserDao;
import cz.muni.fi.entity.User;
import cz.muni.fi.services.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the {@link UserService}. This class is part of the
 * service module of the application that provides the implementation of the
 * business logic (main logic of the application).
 *
 * @author Vojtech Bruza
 */
@Service
public class UserServiceImpl implements UserService {
    @Inject
    UserDao userDao;

    //Confirm/reject mission - User has assigned mission and hasn't accepted it
    // yet(acceptedMission - false, explanation = "") -
    // accept - acceptedMission = true;
    // reject - explanation = assign astronauts explanation, remove astronaut from mission's list
    public void acceptMission(Long userid){
        User user = userDao.findUserById(userid);
        if(user.missionStatusPending()){
            user.setAcceptedMission(true);
        }
    }

    public void rejectMission(Long userid, String explanation) throws IllegalArgumentException{
        if(explanation.isEmpty()){
            throw new IllegalArgumentException("Explanation must not be empty");
        }
        User user = userDao.findUserById(userid);
        if(user.missionStatusPending()){
            user.setExplanation(explanation);
        }
    }

    @Override
    public void addUser(User user) throws DataAccessException {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) throws DataAccessException {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(User user) throws DataAccessException {
        userDao.deleteUser(user);
    }

    @Override
    public List<User> findAllUsers() throws DataAccessException {
        return Collections.unmodifiableList(userDao.findAllUsers());
    }

    @Override
    public List<User> findAllAstronauts() throws DataAccessException {
        return Collections.unmodifiableList(userDao.findAllAstronauts());
    }

    @Override
    public User findUserById(Long id) throws DataAccessException {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> findAllAvailableAstronauts() throws DataAccessException {
        return Collections.unmodifiableList(userDao.findAllAvailableAstronauts());
    }
}
