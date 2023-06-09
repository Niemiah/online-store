package com.solvd.db.impl;
import com.solvd.online.store.cart.Order;
import com.solvd.online.store.cart.OrderDetail;
import com.solvd.online.store.customers.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.online.store.ConnectionPool.getConnection;

public class OrderDaoImpl extends JdbcDao<Order, Integer> {
    public OrderDaoImpl(BasicDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected Order mapRowToEntity(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt("order_id"));
        order.setUserId(resultSet.getInt("user_id"));
        return order;
    }

    @Override
    protected String getTableName() {
        return "Orders";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO Orders (user_id) VALUES (?)";
    }

    @Override
    protected String getFindQuery() {
        return "SELECT * FROM Orders WHERE order_id = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM Orders";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Orders SET user_id = ? WHERE order_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Orders WHERE order_id = ?";
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Order order) throws SQLException {
        statement.setInt(1, order.getUserId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order order) throws SQLException {
        statement.setInt(1, order.getUserId());
        statement.setInt(2, order.getOrderId());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Order order) throws SQLException {
        statement.setInt(1, order.getOrderId());
    }

    @Override
    public OrderDetail find(Integer orderId, Integer productId) {
        return null;
    }

    @Override
    public Order create(Order order) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getCreateQuery())) {
            prepareStatementForCreate(statement, order);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public User find(Integer id) {
        Order order = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getFindQuery())) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    order = mapRowToEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getFindAllQuery());
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                orders.add(mapRowToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public Order update(Order order) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(statement, order);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating order failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public void delete(Order order) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            prepareStatementForDelete(statement, order);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting order failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}