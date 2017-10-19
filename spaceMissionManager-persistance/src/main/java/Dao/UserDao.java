package Dao;

import Entity.User;

public interface UserDao {

	/**
	 * 
	 * @param user
	 */
	void addUser(User user);

	/**
	 * 
	 * @param user
	 */
	void deleteUser(User user);

	void findAllUsers();

	void findAllAstronauts();

	/**
	 * 
	 * @param id
	 */
	void findUserById(Long id);

	/**
	 * 
	 * @param user
	 */
	void updateUser(User user);

	void findAllAvailableAstronauts();

	/**
	 * 
	 * @param user
	 */
	void acceptMission(User user);

	/**
	 * 
	 * @param user
	 * @param explanation
	 */
	void rejectMission(User user, String explanation);

}