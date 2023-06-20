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
        if (user == null) {
            throw new IllegalArgumentException("User object is null.");
        }
        userDAO.insert(user);
    }

    @Override
    public void updateUserInDB(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User object is null.");
        }
        userDAO.update(user);
    }

    @Override
    public User getUserInDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }
        return userDAO.getById(id);
    }

    @Override
    public void deleteUserFromDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }
        userDAO.deleteById(id);
    }
}