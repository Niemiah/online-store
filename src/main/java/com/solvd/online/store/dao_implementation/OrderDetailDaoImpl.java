package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.cart.OrderDetail;
import com.solvd.online.store.customers.User;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.online.store.ConnectionPool.getConnection;

public class OrderDetailDaoImpl extends JdbcDao<OrderDetail, Integer> {
    public OrderDetailDaoImpl(BasicDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected OrderDetail mapRowToEntity(ResultSet resultSet) throws SQLException {
        OrderDetail orderDetails = new OrderDetail();
        orderDetails.setOrderId(resultSet.getInt("order_id"));
        orderDetails.setProductId(resultSet.getInt("product_id"));
        orderDetails.setQuantity(resultSet.getInt("quantity"));
        return orderDetails;
    }

    @Override
    protected String getTableName() {
        return "Order_Details";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO Order_Details (order_id, product_id, quantity) VALUES (?, ?, ?)";
    }

    @Override
    protected String getFindQuery() {
        return "SELECT * FROM Order_Details WHERE order_id = ? AND product_id = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM Order_Details";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Order_Details SET quantity = ? WHERE order_id = ? AND product_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Order_Details WHERE order_id = ? AND product_id = ?";
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, OrderDetail orderDetails) throws SQLException {
        statement.setInt(1, orderDetails.getOrderId());
        statement.setInt(2, orderDetails.getProductId());
        statement.setInt(3, orderDetails.getQuantity());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, OrderDetail orderDetails) throws SQLException {
        statement.setInt(1, orderDetails.getQuantity());
        statement.setInt(2, orderDetails.getOrderId());
        statement.setInt(3, orderDetails.getProductId());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, OrderDetail orderDetails) throws SQLException {
        statement.setInt(1, orderDetails.getOrderId());
        statement.setInt(2, orderDetails.getProductId());
    }

    @Override
    public OrderDetail create(OrderDetail orderDetails) {
        try (PreparedStatement statement = getConnection().prepareStatement(getCreateQuery())) {
            prepareStatementForCreate(statement, orderDetails);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order details failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetails;
    }

    @Override
    public OrderDetail find(int id) {
        return null;
    }

    @Override
    public User find(Integer integer) {
        return null;
    }


    @Override
    public OrderDetail find(Integer orderId, Integer productId) {
        OrderDetail orderDetails = null;
        try (PreparedStatement statement = getConnection().prepareStatement(getFindQuery())) {
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    orderDetails = mapRowToEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetails;
    }

    @Override
    public List<OrderDetail> findAll() {
        List<OrderDetail> orderDetailsList = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(getFindAllQuery());
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                orderDetailsList.add(mapRowToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetailsList;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetails) {
        try (PreparedStatement statement = getConnection().prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(statement, orderDetails);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating order details failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetails;
    }

    @Override
    public void delete(OrderDetail orderDetails) {
        try (PreparedStatement statement = getConnection().prepareStatement(getDeleteQuery())) {
            prepareStatementForDelete(statement, orderDetails);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting order details failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
