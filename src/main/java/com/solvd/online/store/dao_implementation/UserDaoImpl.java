package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.customers.User;
import com.solvd.online.store.locations.Address;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.solvd.online.store.ConnectionPool.getConnection;

public class UserDaoImpl extends JdbcDao<User, Integer> {
    public UserDaoImpl(BasicDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected User mapRowToEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }

    @Override
    protected String getTableName() {
        return "Users";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO Users (first_name, last_name, email) VALUES (?, ?, ?)";
    }

    @Override
    protected String getFindQuery() {
        return "SELECT * FROM Users WHERE user_id = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM Users";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Users SET first_name = ?, last_name = ?, email = ? WHERE user_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Users WHERE user_id = ?";
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setInt(4, user.getUserId());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, User user) throws SQLException {
        statement.setInt(1, user.getUserId());
    }

    @Override
    public User create(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getCreateQuery())) {
            prepareStatementForCreate(statement, user);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public Address find(Integer id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getFindQuery())) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = mapRowToEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getFindAllQuery());
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(mapRowToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User update(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(statement, user);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void delete(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            prepareStatementForDelete(statement, user);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting user failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}