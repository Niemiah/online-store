package com.solvd.online.store.dao_interface;
import com.solvd.online.store.customers.User;
import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();
    public User getUser(int userId);
    public void updateUser(User user);
    public void deleteUser(User user);
}