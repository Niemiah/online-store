package com.solvd.online.store.service.impl;
import com.solvd.online.store.dao.IUserDAO;
import com.solvd.online.store.dao.impl.UserDAO;
import com.solvd.online.store.model.User;
import com.solvd.online.store.service.IUserService;

public class UserService implements IUserService {

    private IUserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    @Override
    public void saveUserToDB(User user) {
        userDAO.insert(user);
    }

    @Override
    public void updateUserInDB(User user) {
        userDAO.update(user);
    }

    @Override
    public User getUserInDB(int id) {
        return userDAO.getById(id);
    }

    @Override
    public String getAllUsersFromDB() {
        return null;
    }
}