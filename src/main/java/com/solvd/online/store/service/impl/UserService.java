package com.solvd.online.store.service.impl;
import com.solvd.online.store.dao.IUserDAO;
import com.solvd.online.store.impl.UserDAO;
import com.solvd.online.store.model.User;
import com.solvd.online.store.service.IUserService;

public class UserService implements IUserService {
    public void saveUserToDataBase(User user) {
        IUserDAO userDAO =  new UserDAO();
        UserDAO.insert(user);
    }
}
