package com.solvd.online.store.impl;
import com.solvd.online.store.dao.IUserDAO;
import com.solvd.online.store.model.OrderDetail;
import com.solvd.online.store.model.User;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.solvd.online.store.util.ConnectionPool.getConnection;

public class UserDAO implements IUserDAO {

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public User getById(int id) {
        return null;
    }
}