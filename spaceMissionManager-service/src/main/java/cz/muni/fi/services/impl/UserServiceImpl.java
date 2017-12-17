package cz.muni.fi.services.impl;

import cz.muni.fi.dao.UserDao;
import cz.muni.fi.entity.User;
import cz.muni.fi.services.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the {@link UserService}. This class is part of the
 * service module of the application that provides the implementation of the
 * business logic (main logic of the application).
 *
 * Added auth methods - jsmadis
 * @author Vojtech Bruza
 */
@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public void acceptAssignedMission(User user){
        if(user == null){
            throw new IllegalArgumentException("User must not be null.");
        }
        if(user.missionStatusPending()){
            user.setAcceptedMission(true);
        } else {
            throw new IllegalArgumentException("This user does not have pending mission status.");
        }
        try {
            userDao.updateUser(user);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Could not update user.", e);
        }
    }

    @Override
    public void rejectAssignedMission(User user, String explanation) throws IllegalArgumentException{
        if(user == null){
            throw new IllegalArgumentException("User must not be null.");
        }
        if(explanation == null){
            throw new IllegalArgumentException("Explanation must not be null.");
        }
        if(explanation.isEmpty()){
            throw new IllegalArgumentException("Explanation must not be empty.");
        }
        if(user.missionStatusPending()){
            user.setExplanation(explanation);
            user.setMission(null);
        } else {
            throw new IllegalArgumentException("This user does not have pending mission status.");
        }
        try {
            userDao.updateUser(user);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Could not update user.", e);
        }
    }

    @Override
    public User addUser(User user) throws DataAccessException {
        if(user == null){
            throw new IllegalArgumentException("User is null");
        }
        String unencryptedPassword = user.getPassword();
        try{
            user.setPassword(createHash(unencryptedPassword));
            return userDao.addUser(user);
        }catch (Throwable t) {
            throw new ServiceDataAccessException("Can not create user: " + user.getEmail(), t);
        }
    }

    @Override
    public void updateUser(User user) throws DataAccessException {
        if(user == null){
            throw new IllegalArgumentException("User must not be null.");
        }
        try {
            userDao.updateUser(user);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Could not update user.", e);
        }
    }

    @Override
    public void deleteUser(User user) throws DataAccessException {
        if(user == null){
            throw new IllegalArgumentException("User must not be null.");
        }
        try {
            userDao.deleteUser(user);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Could not delete user.", e);
        }
    }

    @Override
    public List<User> findAllUsers() throws DataAccessException {
        try {
            return Collections.unmodifiableList(userDao.findAllUsers());
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Could not find users.", e);
        }
    }

    @Override
    public List<User> findAllAstronauts() throws DataAccessException {
        try {
            return Collections.unmodifiableList(userDao.findAllAstronauts());
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Could not find astronauts.", e);
        }
    }

    @Override
    public User findUserById(Long id) throws DataAccessException {
        if(id == null){
            throw new IllegalArgumentException("Id must not be null.");
        }
        try {
            return userDao.findUserById(id);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Could not find user.", e);
        }
    }

    @Override
    public List<User> findAllAvailableAstronauts() throws DataAccessException {
        try {
            return Collections.unmodifiableList(userDao.findAllAvailableAstronauts());
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Could not find astronauts.", e);
        }
    }

    @Override
    public User findUserByEmail(String email) throws DataAccessException {
        if (email == null){
            throw new IllegalArgumentException("Email is null");
        }
        try{
            return userDao.findUserByEmail(email);
        }catch (Throwable t){
            throw new ServiceDataAccessException("Could not find user by email: "+ email, t);
        }
    }

    @Override
    public boolean isManager(User user) throws DataAccessException {
        if(user == null){
            throw new IllegalArgumentException("User is null");
        }

        try{
            return findUserById(user.getId()).isManager();
        }catch (Throwable t){
            throw new ServiceDataAccessException("Could not find user when checking if is admin");
        }
    }


    @Override
    public boolean updatePassword(User user, String oldPassword, String newPassword) throws DataAccessException {
        if(user == null){
            throw new IllegalArgumentException("User is null");
        }
        User u = userDao.findUserById(user.getId());

        try{
            if(validatePassword(oldPassword, u.getPassword())){
                user.setPassword(createHash(newPassword));
                userDao.updateUser(user);
                return true;
            }
            return false;
        }catch (Throwable t){
            throw new ServiceDataAccessException("Can not update user: " + user.getId() + "password", t);
        }
    }

    @Override
    public boolean authenticate(User user, String password) throws DataAccessException {
        if(user == null){
            throw new IllegalArgumentException("User is null");
        }

        User u = findUserById(user.getId());

        return validatePassword(password, u.getPassword());
    }



    //see  https://crackstation.net/hashing-security.htm#javasourcecode
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        if (password == null) return false;
        if (correctHash == null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }

}
