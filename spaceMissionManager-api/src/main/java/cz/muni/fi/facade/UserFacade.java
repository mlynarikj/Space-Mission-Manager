package cz.muni.fi.facade;

import cz.muni.fi.dto.UserDTO;

import java.util.List;

public interface UserFacade {
	/**
	 * Persist user into database
	 *
	 * @param user instance of user
	 */
	void addUser(UserDTO user);

	/**
	 * Update user in database
	 *
	 * @param user Instance of user
	 */
	void updateUser(UserDTO user);

	/**
	 * Delete user from database
	 *
	 * @param user instance of user
	 */
	void deleteUser(UserDTO user);

	/**
	 * Find all users
	 *
	 * @return List of all users, null if none
	 */
	List<UserDTO> findAllUsers();

	/**
	 * Find all astronauts
	 *
	 * @return list of
	 */

	List<UserDTO> findAllAstronauts();

	/**
	 * Find user by id
	 *
	 * @param id User id
	 * @return User instance of user
	 */
	UserDTO findUserById(Long id);

	/**
	 * Find all available astronauts
	 *
	 * @return List of available astronauts
	 */
	List<UserDTO> findAllAvailableAstronauts();

}
