package cz.muni.fi.services;

import cz.muni.fi.entity.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserService {
	/**
	 * Persist user into database
	 *
	 * @param user instance of user
	 */
	void addUser(User user) throws DataAccessException;

	/**
	 * Update user in database
	 *
	 * @param user Instance of user
	 */
	void updateUser(User user) throws DataAccessException;

	/**
	 * Delete user from database
	 *
	 * @param user instance of user
	 */
	void deleteUser(User user) throws DataAccessException;

	/**
	 * Find all users
	 *
	 * @return List of all users, null if none
	 */
	List<User> findAllUsers() throws DataAccessException;

	/**
	 * Find all astronauts
	 *
	 * @return list of
	 */

	List<User> findAllAstronauts() throws DataAccessException;

	/**
	 * Find user by id
	 *
	 * @param id User id
	 * @return User instance of user
	 */
	User findUserById(Long id) throws DataAccessException;

	/**
	 * Find all available astronauts
	 *
	 * @return List of available astronauts
	 */
	List<User> findAllAvailableAstronauts() throws DataAccessException;
}
