package com.solvd.online.store;
import com.solvd.online.store.impl.UserDAO;
import com.solvd.online.store.dao.IUserDAO;
import com.solvd.online.store.model.User;

public class Main {
    public static void main(String[] args) {
        IUserDAO userDAO =  new UserDAO();
        User user = userDAO.getById(1);
    }
}