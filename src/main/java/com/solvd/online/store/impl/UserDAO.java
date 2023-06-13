package com.solvd.online.store.impl;
import com.solvd.online.store.dao.IUserDAO;
import com.solvd.online.store.model.User;
import com.solvd.online.store.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);
    private static final String INSERT = "INSERT INTO User (userId, firstName, lastName, email) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE User SET firstName=?, lastName=?, email=? WHERE userId=?";
    private static final String DELETE = "DELETE FROM User WHERE userId=?";
    private static final String GET = "SELECT * FROM User WHERE userId=?";

    @Override
    public void insert(User user) {
        if(user == null){
            LOGGER.error("User Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        if(user == null){
            LOGGER.error("User Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(int id) {
        User user = new User();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    user.setUserId(resultSet.getInt("userId"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setEmail(resultSet.getString("email"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException(e);
        }
        return user;
    }
}