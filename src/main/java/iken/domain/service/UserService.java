package iken.domain.service;

import iken.domain.object.User;

import java.util.List;

/**
 * Created by ken on 15/8/26.
 */
public interface UserService {
    /**
     * List users
     *
     * @return List
     */
    List<User> listUser();

    /**
     * Get a User by id
     *
     * @return
     */
    User getUserByID(int ID);


    /**
     * Add a User
     */
    int addUser(User user);

    /**
     * Update a User
     */
    boolean updateUser(User user);

    /**
     * Delete a user by ID
     */
    boolean deleteUser(int ID);
}
