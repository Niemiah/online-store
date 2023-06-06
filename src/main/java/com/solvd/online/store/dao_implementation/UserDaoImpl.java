package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.customers.User;
import com.solvd.online.store.dao_interface.UserDao;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    //list is working as a database
    List<User> users;

    public UserDaoImpl(){
        users = new ArrayList<User>();
        User user1 = new User(1,"John","Doe","john@example.com");
        User user2 = new User(2,"Jane","Doe","jane@example.com");
        users.add(user1);
        users.add(user2);
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user.getUserId());
        System.out.println("User: User Id " + user.getUserId() + ", deleted from database");
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUser(int userId) {
        return users.get(userId);
    }

    @Override
    public void updateUser(User user) {
        users.get(user.getUserId()).setFirstName(user.getFirstName());
        System.out.println("User: User Id " + user.getUserId() + ", updated in the database");
    }
}