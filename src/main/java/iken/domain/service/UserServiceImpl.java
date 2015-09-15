package iken.domain.service;

import iken.domain.object.User;
import iken.persistence.DBConnectionUtil;
import iken.persistence.entity.UserEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 15/8/26.
 */
public class UserServiceImpl implements UserService {
    private String userEntityName = "iken.persistence.entity.UserEntity";

    public String getUserEntityName() {
        return userEntityName;
    }

    public void setUserEntityName(String userEntityName) {
        this.userEntityName = userEntityName;
    }

    /**
     * List users
     *
     * @return List
     */
    @Override
    public List<User> listUser() {
        ArrayList<User> userList = new ArrayList<>();
        List<UserEntity> entityList = (List<UserEntity>) DBConnectionUtil.getQuery("from " + userEntityName);
        for (UserEntity userEntity : entityList) {
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            userList.add(user);
        }

        System.out.println("Entity List =" + userList);
        return userList;
    }

    /**
     * Get a User by id
     *
     * @param ID
     * @return User
     */
    @Override
    public User getUserByID(int ID) {
        UserEntity userEntity = (UserEntity) DBConnectionUtil.getAObject("iken.persistence.entity.UserEntity", ID);
        User user = new User();

        BeanUtils.copyProperties(userEntity, user);
        System.out.println("User =" + user);
        return user;
    }

    /**
     * Add a User
     *
     * @param user
     */
    @Override
    public int addUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        DBConnectionUtil.addData(userEntity);
        return userEntity.getId();
    }

    /**
     * Update a User
     *
     * @param user
     */
    @Override
    public boolean updateUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        return DBConnectionUtil.updateData(userEntity);
    }

    /**
     * Delete a user by ID
     *
     * @param ID
     */
    @Override
    public boolean deleteUser(int ID) {
        return DBConnectionUtil.deleteData(userEntityName, ID);
    }


}
