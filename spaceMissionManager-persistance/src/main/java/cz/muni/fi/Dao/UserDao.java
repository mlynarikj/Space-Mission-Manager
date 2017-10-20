package cz.muni.fi.Dao;

import cz.muni.fi.Entity.User;

import java.util.List;

/**
 * Created by jsmadis.
 *
 * @author jsmadis
 */

public interface UserDao {

    /**
     * Persist user into database
     *
     * @param user instance of user
     */
    void addUser(User user);

    /**
     * Update user in database
     *
     * @param user Instance of user
     */
    void updateUser(User user);

    /**
     * Delete user from database
     *
     * @param user instance of user
     */
    void deleteUser(User user);

    /**
     * Find all users
     *
     * @return List of all users, null if none
     */
    List<User> findAllUsers();

    /**
     * Find all astronauts
     *
     * @return list of
     */

    List<User> findAllAstronauts();

    /**
     * Find user by id
     *
     * @param id User id
     * @return User instance of user
     */
    User findUserById(Long id);

    /**
     * Find all available astronauts
     *
     * @return List of available astronauts
     */
    List<User> findAllAvailableAstronauts();
}
