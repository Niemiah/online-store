package com.solvd.online.store.impl;
import com.solvd.online.store.model.OrderDetail;
import com.solvd.online.store.dao.IOrderDetailDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.online.store.util.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailDAO implements IOrderDetailDAO {
    private final static Logger LOGGER = LogManager.getLogger(OrderDetailDAO.class);
    private static final String INSERT = "INSERT INTO OrderDetail (orderId, productId, shippingId, quantity) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE OrderDetail SET productId=?, shippingId=?, quantity=? WHERE orderId=?";
    private static final String DELETE = "DELETE FROM OrderDetail WHERE orderId=?";
    private static final String GET = "SELECT * FROM OrderDetail WHERE orderId=?";

    @Override
    public void insert(OrderDetail orderDetail) {
        if(orderDetail == null){
            LOGGER.error("OrderDetail Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setInt(1, orderDetail.getOrderId());
            preparedStatement.setInt(2, orderDetail.getProductId());
            preparedStatement.setInt(3, orderDetail.getShippingId());
            preparedStatement.setInt(4, orderDetail.getQuantity());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(OrderDetail orderDetail) {
        if(orderDetail == null){
            LOGGER.error("OrderDetail Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setInt(1, orderDetail.getProductId());
            preparedStatement.setInt(2, orderDetail.getShippingId());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setInt(4, orderDetail.getOrderId());
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
    public OrderDetail getById(int id) {
        OrderDetail orderDetail = new OrderDetail();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    orderDetail.setOrderId(resultSet.getInt("orderId"));
                    orderDetail.setProductId(resultSet.getInt("productId"));
                    orderDetail.setShippingId(resultSet.getInt("shippingId"));
                    orderDetail.setQuantity(resultSet.getInt("quantity"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException(e);
        }
        return orderDetail;
    }
}