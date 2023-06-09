package com.solvd.online.store.service.impl;
import com.solvd.online.store.dao.IUserDAO;
import com.solvd.online.store.impl.UserDAO;
import com.solvd.online.store.model.User;
import com.solvd.online.store.service.IUserService;

public class UserService implements IUserService {

    @Override
    public void saveUserToDataBase(User user) {
        IUserDAO userDao = new UserDAO();
        userDao.insert(user);
    }

    @Override
    public void saveUserToDB(User user) {
        IUserDAO userDao = new UserDAO();
        userDao.insert(user);
    }

    @Override
    public void updateUserInDB(User user) {
        IUserDAO userDao = new UserDAO();
        userDao.update(user);
    }

    @Override
    public User getUserInDB(int id) {
        IUserDAO userDao = new UserDAO();
        return userDao.getById(id);
    }
}