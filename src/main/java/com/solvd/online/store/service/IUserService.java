package com.solvd.online.store.service;
import com.solvd.online.store.model.User;

public interface IUserService {
    void saveUserToDataBase(User user);

    void saveUserToDB(User user);

    void updateUserInDB(User user);

    User getUserInDB(int id);
}
